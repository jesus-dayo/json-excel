package com.dayosoft.excel.writer;

import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.jsoniter.JsonIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
        Template template = jsonExcelRequest.getTemplate();
        // todo add delayedrendering list that will be executed after the workbook is written
        final List<TemplateSheet> sheets = template.getSheets();
        TemplateHelper.fillDependencies(sheets);
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
        final List<DelayedRender> delayedRendering = new ArrayList<>();
        final List<TemplateRow> templateRows = sheet.getRows();
        for (TemplateRow templateRow : templateRows) {
            writeRows(jsonExcelRequest, xssfSheet, delayedRendering, templateRow);
        }
        if(!delayedRendering.isEmpty()){
            runDelayedRendering(delayedRendering, xssfSheet);
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
    }

    public void runDelayedRendering(List<DelayedRender> delayedRenders, XSSFSheet sheet){
        final long count = delayedRenders.stream().filter(delayedRender -> !delayedRender.getTemplateColumn().isRendered()).count();
        log.info("delayedRendering {}", count);
        if(count == 0){
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

    private void writeRows(JsonExcelRequest jsonExcelRequest, XSSFSheet xssfSheet, List<DelayedRender> delayedRenders, TemplateRow templateRow) {
        XSSFRow row = xssfSheet.createRow(templateRow.getRowNum());
        final List<TemplateColumn> templateColumns = templateRow.getColumns();
        for (TemplateColumn templateColumn : templateColumns) {
            if(templateColumn.isRendered()){
                continue;
            }
            writeColumns(jsonExcelRequest, delayedRenders, row, templateColumn);
        }
    }

    private void writeColumns(JsonExcelRequest jsonExcelRequest,
                              List<DelayedRender> delayedRenders,
                              XSSFRow row,
                              TemplateColumn templateColumn) {
        final XSSFSheet sheet = row.getSheet();
        final XSSFWorkbook wb = sheet.getWorkbook();
        final XSSFCell cell = row.createCell(templateColumn.getCol());
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
