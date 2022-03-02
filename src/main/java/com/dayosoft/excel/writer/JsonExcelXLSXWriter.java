package com.dayosoft.excel.writer;

import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.StylesMapper;
import com.jsoniter.JsonIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class JsonExcelXLSXWriter implements JsonExcelWriter {

    private final ExpressionRenderingEngine renderingEngine;

    public File write(JsonExcelRequest jsonExcelRequest) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        Template template = JsonIterator.deserialize(jsonExcelRequest.getTemplate(), Template.class);
//        Any data = JsonIterator.deserialize(jsonExcelRequest.getData());
//        JsonDataTraverser jsonTraverser = new JsonDataTraverser(data);
        // todo add delayedrendering list that will be executed after the workbook is written
        final List<TemplateSheet> sheets = template.getSheets();
        sheets.forEach(sheet -> {
            writeSheets(jsonExcelRequest, wb, sheet);
        });

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() +
                ".xlsx");
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        return file;
    }

    private void writeSheets(JsonExcelRequest jsonExcelRequest, XSSFWorkbook wb, TemplateSheet sheet) {
        XSSFSheet xssfSheet = wb.createSheet(sheet.getName());
        xssfSheet.setDisplayGridlines(sheet.isDisplayGridlines());
        xssfSheet.setPrintGridlines(sheet.isPrintGridlines());
        xssfSheet.setFitToPage(sheet.isFitToPage());
        xssfSheet.setDisplayGuts(sheet.isDisplayGuts());
        final List<TemplateRenderedLog> templateRenderedLogs = new ArrayList<>();
        final List<TemplateRow> templateRows = sheet.getRows();
        for (TemplateRow templateRow : templateRows) {
            writeRows(jsonExcelRequest, xssfSheet, templateRenderedLogs, templateRow);
        }
        final List<TemplateMerge> templateMerges = sheet.getMergeRegions();
        templateMerges.stream().forEach(templateMerge -> {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(
                    templateMerge.getStart().getRow(),
                    templateMerge.getEnd().getRow(),
                    templateMerge.getStart().getCol(),
                    templateMerge.getEnd().getCol());
            xssfSheet.addMergedRegion(cellRangeAddress);
        });
        log.info("templateRenderedLogs {}", templateRenderedLogs);
    }

    private void writeRows(JsonExcelRequest jsonExcelRequest, XSSFSheet xssfSheet, List<TemplateRenderedLog> templateRenderedLogs, TemplateRow templateRow) {
        final TemplateRenderedLog templateRenderedLog = new TemplateRenderedLog();
        int rowNum = templateRow.getRowNum();
        final Optional<TemplateRenderedLog> max = templateRenderedLogs.stream().max(Comparator.comparing(TemplateRenderedLog::getRenderedLastRow));
        if (max.isPresent() && max.get().getRenderedLastRow() > rowNum) {
            rowNum = max.get().getRenderedLastRow() + (rowNum - max.get().getTemplateRow().getRowNum());
        }
        XSSFRow row = xssfSheet.createRow(rowNum);
        final List<TemplateColumn> templateColumns = templateRow.getColumns();
        for (TemplateColumn templateColumn : templateColumns) {
            templateRenderedLog.setTemplateRow(templateRow);
            templateRenderedLog.setTemplateColumn(templateColumn);
            writeColumns(jsonExcelRequest, templateRenderedLog, row, templateColumn);
        }
        templateRenderedLogs.add(templateRenderedLog);
    }

    private void writeColumns(JsonExcelRequest jsonExcelRequest,
                              TemplateRenderedLog templateRenderedLog,
                              XSSFRow row,
                              TemplateColumn templateColumn) {
        final XSSFSheet sheet = row.getSheet();
        final XSSFWorkbook wb = sheet.getWorkbook();
        final XSSFCell cell = row.createCell(templateColumn.getPosition().getCol());
        templateRenderedLog.setRenderedStartRow(cell.getRowIndex());
        templateRenderedLog.setRenderedLastRow(cell.getRowIndex());
        templateRenderedLog.setRenderedLastCol(cell.getColumnIndex());
        templateRenderedLog.setRenderedStartCol(cell.getColumnIndex());
        final Map<String, String> styles = templateColumn.getStyles();
        if (!styles.isEmpty()) {
            XSSFCellStyle newCellStyle = wb.createCellStyle();
            final XSSFFont font = wb.createFont();
            newCellStyle.setFont(font);
            StylesMapper.applyStyles(newCellStyle, styles);
            cell.setCellStyle(newCellStyle);
        }
        if (templateColumn.getValue() instanceof String) {
            if (ExpressionHelper.isValidExpression(templateColumn.getValue().toString())) {
                templateRenderedLog.setExpression(templateColumn.getValue().toString());
                renderingEngine.renderCellByExpression(templateRenderedLog, jsonExcelRequest.getData(),
                        templateColumn.getValue().toString(),
                        cell);
            } else {
                cell.setCellValue(templateColumn.getValue().toString());
                templateRenderedLog.setValue(templateColumn.getValue().toString());
            }
        }
        if (templateColumn.getValue() instanceof Double) {
            cell.setCellValue((Double) templateColumn.getValue());
        }
        if (templateColumn.getValue() instanceof Integer) {
            cell.setCellValue((Integer) templateColumn.getValue());
        }
        sheet.setColumnWidth(cell.getColumnIndex(), templateColumn.getColumnWidth());
    }

}
