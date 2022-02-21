package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.styles.Style;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.dayosoft.excel.util.JsonTemplateTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;

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
