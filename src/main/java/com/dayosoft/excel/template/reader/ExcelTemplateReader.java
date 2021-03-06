package com.dayosoft.excel.template.reader;

import com.dayosoft.excel.model.*;
import com.dayosoft.excel.type.ExcelReportType;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class ExcelTemplateReader {

    public Template excelToJsonTemplate(String name, String description, InputStream file, ExcelReportType reportType) throws IOException {
        Workbook workbook = getWorkbookByType(file, reportType);
        List<TemplateSheet> sheets = getTemplateSheets(reportType, workbook);
        if (reportType == ExcelReportType.EXCEL_2003) {
            return Template.builder().name(getValueOrBlank(name)).description(getValueOrBlank(description)).format(ExcelReportType.EXCEL_2003.getExtension()).sheets(sheets).build();
        } else {
            return Template.builder().name(getValueOrBlank(name)).description(getValueOrBlank(description)).format(ExcelReportType.EXCEL_2007.getExtension()).sheets(sheets).build();
        }
    }

    private List<TemplateSheet> getTemplateSheets(ExcelReportType reportType, Workbook workbook) {
        List<TemplateSheet> sheets = new ArrayList<>();
        int sheetIndex = 0;
        final Iterator<Sheet> sheetIterator = workbook.iterator();
        while (sheetIterator.hasNext()) {
            final Sheet sheet = sheetIterator.next();
            final TemplateSheet templateSheet = new TemplateSheet();
            fillSheetMapping(workbook, sheet, templateSheet, reportType);
            templateSheet.setIndex(sheetIndex);
            templateSheet.setName(sheet.getSheetName());
            templateSheet.setPrintGridlines(sheet.isPrintGridlines());
            templateSheet.setFitToPage(sheet.getFitToPage());
            templateSheet.setDisplayGuts(sheet.getDisplayGuts());
            templateSheet.setDisplayGridlines(sheet.isDisplayGridlines());
            templateSheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());

            List<TemplateMerge> merges = new ArrayList<>();
            sheet.getMergedRegions().stream().forEach(cellAddresses -> {
                merges.add(new TemplateMerge(TemplatePosition.builder().col(cellAddresses.getFirstColumn()).row(cellAddresses.getFirstRow()).build(), TemplatePosition.builder().col(cellAddresses.getLastColumn()).row(cellAddresses.getLastRow()).build()));
            });
            templateSheet.setMergeRegions(merges);
            sheets.add(templateSheet);
            sheetIndex++;
        }
        return sheets;
    }

    private Workbook getWorkbookByType(InputStream file, ExcelReportType reportType) throws IOException {
        Workbook workbook;
        if (ExcelReportType.EXCEL_2003 == reportType) {
            workbook = new HSSFWorkbook(file);
        } else {
            workbook = new XSSFWorkbook(file);
        }
        return workbook;
    }

    private String getValueOrBlank(String name) {
        return StringUtils.isNotEmpty(name) ? name : StringUtils.EMPTY;
    }

    private void fillSheetMapping(Workbook workbook, Sheet sheet, TemplateSheet templateSheet, ExcelReportType reportType) {
        List<TemplateRow> rows = new ArrayList<>();

        final Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            Map<String, String> copyOfRowStyles = cloneRowStyles(workbook, reportType, row);
            List<TemplateColumn> columns = getTemplateColumns(workbook, sheet, reportType, row);
            rows.add(TemplateRow.builder()
                    .rowNum(row.getRowNum())
                    .styles(copyOfRowStyles)
                    .originalRowNum(row.getRowNum())
                    .columns(columns).build());
        }

        templateSheet.setRows(rows);
    }

    private List<TemplateColumn> getTemplateColumns(Workbook workbook, Sheet sheet, ExcelReportType reportType, Row row) {
        List<TemplateColumn> columns = new ArrayList<>();
        final Iterator<Cell> cellIterator = row.iterator();
        while (cellIterator.hasNext()) {
            final Cell cell = cellIterator.next();
            final CellType cellType = cell.getCellType();
            TemplateColumn templateColumn = TemplateColumn.builder().build();
            templateColumn.setColumnWidth(sheet.getColumnWidth(cell.getColumnIndex()));
            deriveValueByCellType(cell, cellType, templateColumn);

            final CellAddress address = cell.getAddress();

            Map<String, String> copyOfCellStyles = new HashMap<>();
            final CellStyle cellStyle = cell.getCellStyle();
            if (cellStyle != null) {
                cloneStyles(workbook, reportType, copyOfCellStyles, cellStyle);
                templateColumn.setStyles(copyOfCellStyles);
                cloneArrayFormulaRange(cell, templateColumn);
                cloneCellComments(cell, templateColumn);
                cloneCellPosition(templateColumn, address);
                columns.add(templateColumn);
            }

        }
        return columns;
    }

    private void cloneCellPosition(TemplateColumn templateColumn, CellAddress address) {
        templateColumn.setCol(address.getColumn());
        templateColumn.setOriginalCol(address.getColumn());
    }

    private void cloneCellComments(Cell cell, TemplateColumn templateColumn) {
        if (cell.getCellComment() != null) {
            templateColumn.setCellComment(cell.getCellComment().getString().getString());
        }
    }

    private void cloneArrayFormulaRange(Cell cell, TemplateColumn templateColumn) {
        if (cell.isPartOfArrayFormulaGroup()) {
            final CellRangeAddress arrayFormulaRange = cell.getArrayFormulaRange();
            if (arrayFormulaRange != null) {
                templateColumn.setArrayFormulaRange(TemplateRange.builder().start(TemplatePosition.builder().col(arrayFormulaRange.getFirstColumn()).row(arrayFormulaRange.getFirstRow()).build()).end(TemplatePosition.builder().col(arrayFormulaRange.getLastColumn()).row(arrayFormulaRange.getLastRow()).build()).build());
            }
        }
    }

    private Map<String, String> cloneRowStyles(Workbook workbook, ExcelReportType reportType, Row row) {
        Map<String, String> copyOfRowStyles = new HashMap<>();
        final CellStyle rowStyle = row.getRowStyle();
        if (rowStyle != null) {
            cloneStyles(workbook, reportType, copyOfRowStyles, rowStyle);
        }
        return copyOfRowStyles;
    }

    private void deriveValueByCellType(Cell cell, CellType cellType, TemplateColumn templateColumn) {
        if (cellType != CellType.BLANK) {
            switch (cellType) {
                case STRING: {
                    templateColumn.setValue(cell.getStringCellValue());
                    break;
                }
                case NUMERIC: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        templateColumn.setValue(cell.getDateCellValue());
                    } else {
                        templateColumn.setValue(cell.getNumericCellValue());
                    }
                    break;
                }
                case FORMULA: {
                    final String cellFormula = cell.getCellFormula();
                    if (StringUtils.isNotEmpty(cellFormula)) {
                        templateColumn.setCellFormula(cellFormula);
                    }
                    break;
                }
            }
        }
    }

    private void cloneStyles(Workbook workbook, ExcelReportType reportType, Map<String, String> cellStyles, CellStyle cellStyle) {
        final short fillForegroundColor = cellStyle.getFillForegroundColor();
        final FillPatternType fillPatternType = cellStyle.getFillPattern();
        final short fillBackgroundColor = cellStyle.getFillBackgroundColor();
        final String dataFormatString = cellStyle.getDataFormatString();
        final HorizontalAlignment alignment = cellStyle.getAlignment();
        final VerticalAlignment verticalAlignment = cellStyle.getVerticalAlignment();
        final BorderStyle borderBottom = cellStyle.getBorderBottom();
        final BorderStyle borderLeft = cellStyle.getBorderLeft();
        final BorderStyle borderRight = cellStyle.getBorderRight();
        final BorderStyle borderTop = cellStyle.getBorderTop();
        final short bottomBorderColor = cellStyle.getBottomBorderColor();
        final short leftBorderColor = cellStyle.getLeftBorderColor();
        final short rightBorderColor = cellStyle.getRightBorderColor();
        final short topBorderColor = cellStyle.getTopBorderColor();
        final boolean shrinkToFit = cellStyle.getShrinkToFit();
        final boolean wrapText = cellStyle.getWrapText();

        final Font font = reportType == ExcelReportType.EXCEL_2007 ? ((XSSFWorkbook) workbook).getFontAt(cellStyle.getFontIndex()) : ((HSSFWorkbook) workbook).getFontAt(cellStyle.getFontIndex());

        addToStyles(cellStyles, "fillForegroundColorColor", fillForegroundColor);
        addToStyles(cellStyles, "fillPatternType", fillPatternType);
        addToStyles(cellStyles, "fillBackgroundColorColor", fillBackgroundColor);
        addToStyles(cellStyles, "alignment", alignment);
        addToStyles(cellStyles, "verticalAlignment", verticalAlignment);
        addToStyles(cellStyles, "borderBottom", borderBottom);
        addToStyles(cellStyles, "borderLeft", borderLeft);
        addToStyles(cellStyles, "borderRight", borderRight);
        addToStyles(cellStyles, "borderTop", borderTop);
        addToStyles(cellStyles, "bottomBorderColor", bottomBorderColor);
        addToStyles(cellStyles, "leftBorderColor", leftBorderColor);
        addToStyles(cellStyles, "rightBorderColor", rightBorderColor);
        addToStyles(cellStyles, "topBorderColor", topBorderColor);
        addToStyles(cellStyles, "shrinkToFit", shrinkToFit);
        addToStyles(cellStyles, "wrapText", wrapText);
        addToStyles(cellStyles, "fontFamily", font.getFontName());
        addToStyles(cellStyles, "bold", font.getBold());
        addToStyles(cellStyles, "fontColor", font.getColor());
        addToStyles(cellStyles, "italic", font.getItalic());
        addToStyles(cellStyles, "fontHeight", font.getFontHeight());
        addToStyles(cellStyles, "dataFormatString", dataFormatString);
    }

    private void addToStyles(Map<String, String> cellStyles, String key, Object value) {
        if (value != null) {
            cellStyles.put(key, String.valueOf(value));
        }
    }
}
