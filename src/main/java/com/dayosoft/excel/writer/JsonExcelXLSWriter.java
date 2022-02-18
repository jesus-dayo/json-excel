package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ReportType;
import com.dayosoft.excel.type.XLSJsonType;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class JsonExcelXLSWriter implements JsonExcelWriter {

    public File write(JsonExcelRequest jsonExcelRequest) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        Any any = JsonIterator.deserialize(jsonExcelRequest.getJson());
        Row headerRow = sheet.createRow(0);
        final Any columns = any.get("body", ReportType.SIMPLE_REPORT.name(), "columns");
        Map<Integer, Any> columnMap = writeHeaders(columns, headerRow);
        final Any rows = any.get("body", ReportType.SIMPLE_REPORT.name(), "rows");
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
                xlsJsonType.getDefaultStyleSetter().accept(wb,cell);
            }
            rowIndex++;
        }

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() +
                ".xls");
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        wb.close();
        return file;
    }

    private Map<Integer, Any> writeHeaders(Any columns, Row headerRow) {
        Map<Integer, Any> columnMap = new HashMap<>();
        final Iterator<Any> columnsIterator = columns.iterator();
        int cellIndex = 0;
        while (columnsIterator.hasNext()) {
            final Any column = columnsIterator.next();
            String name = column.get("name").toString();
            final Cell cell = headerRow.createCell(cellIndex, CellType.STRING);
            cell.setCellValue(name);
            columnMap.put(cellIndex, column);
            cellIndex++;
        }
        return columnMap;
    }

}
