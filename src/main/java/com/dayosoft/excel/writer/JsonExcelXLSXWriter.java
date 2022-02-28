package com.dayosoft.excel.writer;

import com.dayosoft.excel.renderer.ExpressionRenderer;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.renderer.parser.ExpressionHelper;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JsonExcelXLSXWriter implements JsonExcelWriter {

    private final ExpressionRenderer interpreter;

    public File write(JsonExcelRequest jsonExcelRequest) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        Template template = JsonIterator.deserialize(jsonExcelRequest.getTemplate(), Template.class);
//        Any data = JsonIterator.deserialize(jsonExcelRequest.getData());
//        JsonDataTraverser jsonTraverser = new JsonDataTraverser(data);

        final List<TemplateSheet> sheets = template.getSheets();
        sheets.stream().forEach(sheet -> {
            XSSFSheet xssfSheet = wb.createSheet(sheet.getName());
            xssfSheet.setDisplayGridlines(sheet.isDisplayGridlines());
            xssfSheet.setPrintGridlines(sheet.isPrintGridlines());
            xssfSheet.setFitToPage(sheet.isFitToPage());
            xssfSheet.setDisplayGuts(sheet.isDisplayGuts());
            final List<TemplateRow> templateRows = sheet.getRows();
            templateRows.stream().forEach(templateRow -> {
                final XSSFRow row = xssfSheet.createRow(templateRow.getRowNum());
                final List<TemplateColumn> templateColumns = templateRow.getColumns();
                templateColumns.stream().forEach(templateColumn -> {
                    final XSSFCell cell = row.createCell(templateColumn.getPosition().getCol());
                    if (templateColumn.getValue() instanceof String) {
                        if (ExpressionHelper.isValidExpression(templateColumn.getValue().toString())) {
                            interpreter.render(sheet, jsonExcelRequest.getData(), templateColumn.getValue().toString(), cell);
                        } else {
                            cell.setCellValue(templateColumn.getValue().toString());
                        }
                    }
                    if (templateColumn.getValue() instanceof Double) {
                        cell.setCellValue((Double) templateColumn.getValue());
                    }
                    if (templateColumn.getValue() instanceof Integer) {
                        cell.setCellValue((Integer) templateColumn.getValue());
                    }
                    xssfSheet.setColumnWidth(cell.getColumnIndex(), templateColumn.getColumnWidth());
                    final Map<String, String> styles = templateColumn.getStyles();
                    if (!styles.isEmpty()) {
                        XSSFCellStyle newCellStyle = wb.createCellStyle();
                        final XSSFFont font = wb.createFont();
                        newCellStyle.setFont(font);
                        StylesMapper.applyStyles(newCellStyle, styles);
                        cell.setCellStyle(newCellStyle);
                    }
                });
            });
            final List<TemplateMerge> templateMerges = sheet.getMergeRegions();
            templateMerges.stream().forEach(templateMerge -> {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(
                        templateMerge.getStart().getRow(),
                        templateMerge.getEnd().getRow(),
                        templateMerge.getStart().getCol(),
                        templateMerge.getEnd().getCol());
                xssfSheet.addMergedRegion(cellRangeAddress);
            });

        });


        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() +
                ".xlsx");
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        return file;
    }

}
