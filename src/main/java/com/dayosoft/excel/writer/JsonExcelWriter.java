package com.dayosoft.excel.writer;

import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.dayosoft.excel.type.ExcelReportType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonExcelWriter {

    private final ExpressionRenderingEngine renderingEngine;

    public File write(JsonExcelRequest jsonExcelRequest, ExcelReportType excelReportType) throws IOException {
        Workbook wb;
        if (excelReportType == ExcelReportType.EXCEL_2003) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        Template template = jsonExcelRequest.getTemplate();
        final List<TemplateSheet> sheets = template.getSheets();
        TemplateHelper.fillDependencies(sheets);
        sheets.forEach(sheet -> {
            writeSheets(jsonExcelRequest, wb, sheet);
        });

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() + "." + excelReportType.getExtension());
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        return file;
    }

    private void writeSheets(JsonExcelRequest jsonExcelRequest, Workbook wb, TemplateSheet sheet) {
        Sheet wbSheet = wb.createSheet(sheet.getName());
        wbSheet.setDisplayGridlines(sheet.isDisplayGridlines());
        wbSheet.setPrintGridlines(sheet.isPrintGridlines());
        wbSheet.setFitToPage(sheet.isFitToPage());
        wbSheet.setDisplayGuts(sheet.isDisplayGuts());
        final List<DelayedRender> delayedRendering = new ArrayList<>();
        final List<TemplateRow> templateRows = sheet.getRows();
        for (TemplateRow templateRow : templateRows) {
            writeRows(jsonExcelRequest, wbSheet, delayedRendering, templateRow);
        }
        if (!delayedRendering.isEmpty()) {
            runDelayedRendering(delayedRendering, wbSheet);
        }
        final List<TemplateMerge> templateMerges = sheet.getMergeRegions();
        templateMerges.stream().forEach(templateMerge -> {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(
                    templateMerge.getStart().getRow(),
                    templateMerge.getEnd().getRow(),
                    templateMerge.getStart().getCol(),
                    templateMerge.getEnd().getCol());
            wbSheet.addMergedRegion(cellRangeAddress);
        });
    }

    public void runDelayedRendering(List<DelayedRender> delayedRenders, Sheet sheet) {
        final long count = delayedRenders.stream().filter(delayedRender -> !delayedRender.getTemplateColumn().isRendered()).count();
        log.info("delayedRendering {}", count);
        if (count == 0) {
            return;
        }
        List<DelayedRender> newDelayedRenders = new ArrayList<>();
        for (DelayedRender delayedRender : delayedRenders) {
            final TemplateRow templateRow = delayedRender.getTemplateColumn().getTemplateRow();
            final Row row = sheet.getRow(templateRow.getRowNum());
            final Cell cell = row.getCell(delayedRender.getTemplateColumn().getCol());
            newDelayedRenders.addAll(renderingEngine.renderByExpression(delayedRender.getData(), delayedRender.getTemplateColumn(), cell));
        }
        runDelayedRendering(newDelayedRenders, sheet);
    }

    private void writeRows(JsonExcelRequest jsonExcelRequest, Sheet wbSheet, List<DelayedRender> delayedRenders, TemplateRow templateRow) {
        Row row = wbSheet.createRow(templateRow.getRowNum());
        final List<TemplateColumn> templateColumns = templateRow.getColumns();
        for (TemplateColumn templateColumn : templateColumns) {
            if (templateColumn.isRendered()) {
                continue;
            }
            writeColumns(jsonExcelRequest, delayedRenders, row, templateColumn);
        }
    }

    private void writeColumns(JsonExcelRequest jsonExcelRequest,
                              List<DelayedRender> delayedRenders,
                              Row row,
                              TemplateColumn templateColumn) {
        final Sheet sheet = row.getSheet();
        final Workbook wb = sheet.getWorkbook();
        final Cell cell = row.createCell(templateColumn.getCol());
        final Map<String, String> styles = templateColumn.getStyles();
        if (!styles.isEmpty()) {
            CellStyle newCellStyle = wb.createCellStyle();
            final Font font = wb.createFont();
            newCellStyle.setFont(font);
            StylesMapper.applyStyles(wb, newCellStyle, styles);
            cell.setCellStyle(newCellStyle);
        }
        if (templateColumn.getValue() instanceof String) {
            if (ExpressionHelper.isValidExpression(templateColumn.getValue().toString())) {
                delayedRenders.addAll(renderingEngine.renderByExpression(jsonExcelRequest.getData(),
                        templateColumn,
                        cell));
            } else {
                cell.setCellValue(templateColumn.getValue().toString());
                templateColumn.setRendered(true);
            }
        }
        if (templateColumn.getValue() instanceof Double) {
            cell.setCellValue((Double) templateColumn.getValue());
            templateColumn.setRendered(true);
        }
        if (templateColumn.getValue() instanceof Integer) {
            cell.setCellValue((Integer) templateColumn.getValue());
            templateColumn.setRendered(true);
        }
        sheet.setColumnWidth(cell.getColumnIndex(), templateColumn.getColumnWidth());
    }

}
