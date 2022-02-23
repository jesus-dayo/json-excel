package com.dayosoft.excel.writer;

import com.dayosoft.excel.model.TemplatePosition;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.Style;
import com.dayosoft.excel.type.ExcelJsonType;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.dayosoft.excel.util.JsonTemplateTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonExcelXLSXWriter implements JsonExcelWriter {

    public File write(JsonExcelRequest jsonExcelRequest) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        Any template = JsonIterator.deserialize(jsonExcelRequest.getTemplate());
        JsonTemplateTraverser jsonTemplateTraverser = new JsonTemplateTraverser(template);

        Map<Integer, XSSFSheet> sheetMap = new HashMap<>();
        final Iterator<Any> sheets = jsonTemplateTraverser.sheets();
        final Map<String, Any> globalSheetStyles = jsonTemplateTraverser.globalSheetStyles();
        final XSSFCellStyle defaultCellStyle = wb.createCellStyle();
        defaultCellStyle.setFont(wb.createFont());
        globalSheetStyles.entrySet().forEach(style->{
            Style.valueOf(style.getKey()).getFormatter().accept(defaultCellStyle, style.getValue().toString());
        });

        int sheetIndex = 0;
        while (sheets.hasNext()) {
            final Any sheetJson = sheets.next();
            XSSFSheet sheet = wb.createSheet(jsonTemplateTraverser.sheetName(sheetJson));
            applyGlobalStyles(sheet, defaultCellStyle);
            sheetMap.put(sheetIndex, sheet);
            sheetIndex++;
        }

        final Any staticDataJson = jsonTemplateTraverser.globalStaticData();
        final Iterator<Any> staticDataJsonIterator = staticDataJson.iterator();
        while(staticDataJsonIterator.hasNext()){
            final Any staticData = staticDataJsonIterator.next();
            final XSSFSheet sheet = sheetMap.get(jsonTemplateTraverser.sheetIndex(staticData));
            final Any type = jsonTemplateTraverser.type(staticData);
            final Any value = jsonTemplateTraverser.value(staticData);
            final ExcelJsonType excelJsonType = ExcelJsonType.getByJsonType(type.toString());
            if(jsonTemplateTraverser.isPositionMerger(staticData)){
                final TemplatePosition start = jsonTemplateTraverser.positionStart(staticData);
                final TemplatePosition end = jsonTemplateTraverser.positionEnd(staticData);
                final XSSFRow row = sheet.getRow(start.getRow());
                final XSSFCell cell = row.createCell(start.getCol(), excelJsonType.getCellType().apply(value));
                excelJsonType.getValueSetter().accept(value, cell);
                final CellRangeAddress cellAddresses = new CellRangeAddress(start.getRow(), end.getRow(), start.getCol(), end.getCol());
                sheet.addMergedRegion(cellAddresses);

                final XSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.cloneStyleFrom(defaultCellStyle);
                final XSSFFont font = wb.createFont();
                font.setFontName(defaultCellStyle.getFont().getFontName());
                cellStyle.setFont(font);
                final Map<String, Any> styles = jsonTemplateTraverser.styles(staticData);
                styles.entrySet().forEach(style->{
                    Style.valueOf(style.getKey()).getFormatter().accept(cellStyle, style.getValue().toString());
                });
                cell.setCellStyle(cellStyle);
            } else {
                final TemplatePosition position = jsonTemplateTraverser.position(staticData);
                final XSSFRow row = sheet.getRow(position.getRow());
                final XSSFCell cell = row.createCell(position.getCol(), excelJsonType.getCellType().apply(value));
                excelJsonType.getValueSetter().accept(value, cell);

                final XSSFCellStyle cellStyle = wb.createCellStyle();
                final Map<String, Any> styles = jsonTemplateTraverser.styles(staticData);
                cellStyle.cloneStyleFrom(defaultCellStyle);
                final XSSFFont font = wb.createFont();
                font.setFontName(defaultCellStyle.getFont().getFontName());
                cellStyle.setFont(font);

                styles.entrySet().forEach(style->{
                    Style.valueOf(style.getKey()).getFormatter().accept(cellStyle, style.getValue().toString());
                });
                cell.setCellStyle(cellStyle);

                sheet.autoSizeColumn(position.getCol());
            }
        }

        Any data = JsonIterator.deserialize(jsonExcelRequest.getData());
        JsonDataTraverser jsonTraverser = new JsonDataTraverser(data);


        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() +
                ".xlsx");
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        return file;
    }

    private void applyGlobalStyles(XSSFSheet sheet, CellStyle defaultStyle) {
        for(int x = 0; x < SpreadsheetVersion.EXCEL2007.getMaxRows();x++){
            final XSSFRow row = sheet.createRow(x);
            row.setRowStyle(defaultStyle);
        }
    }

}
