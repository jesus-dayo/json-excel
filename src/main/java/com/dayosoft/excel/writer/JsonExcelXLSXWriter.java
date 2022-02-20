package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.type.XLSJsonType;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

public class JsonExcelXLSXWriter implements JsonExcelWriter {

    public File write(JsonExcelRequest jsonExcelRequest) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        Any any = JsonIterator.deserialize(jsonExcelRequest.getJson());
        Row headerRow = sheet.createRow(0);
        final Any columns = any.get("body", ExcelReportType.SIMPLE_REPORT.name(), "columns");
        Map<Integer, Any> columnMap = null;
        final Any rows = any.get("body", ExcelReportType.SIMPLE_REPORT.name(), "rows");
        final Iterator<Any> rowsIterator = rows.iterator();
        int rowIndex = 1;
        while (rowsIterator.hasNext()) {
            final Any row = rowsIterator.next();
            Row cellRow = sheet.createRow(rowIndex);
            for (int colIndex = 0; colIndex < columnMap.size(); colIndex++) {
                final Any value = row.get(columnMap.get(colIndex).get("field").toString());
                final String type = columnMap.get(colIndex).get("type").toString();
                final XLSJsonType xlsJsonType = XLSJsonType.getByJsonType(type);
                Cell cell = cellRow.createCell(colIndex, xlsJsonType.getCellType().apply(value));
                xlsJsonType.getValueSetter().accept(value, cell);
//                xlsJsonType.getDefaultStyleSetter().accept(wb,cell);
            }
            rowIndex++;
        }

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() +
                ".xls");
//        FileOutputStream out = new FileOutputStream(file);
//        wb.write(out);
//        out.close();
//        wb.close();
        return file;
    }

}
