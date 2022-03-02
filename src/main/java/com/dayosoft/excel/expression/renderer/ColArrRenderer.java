package com.dayosoft.excel.expression.renderer;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColArrRenderer extends CellRenderer<List<Object>> {

    @Override
    public void render(Cell cell, List<Object> value) {
        if (!value.isEmpty()) {
            final Sheet sheet = cell.getSheet();
            final Workbook workbook = sheet.getWorkbook();
            setValue(cell, value.get(0));
            if (value.size() > 1) {
                int rowIndex = cell.getAddress().getRow() + 1;
                final Row row = cell.getRow();
                for(int i = 1; i < value.size(); i++) {
                    Row newRow = sheet.getRow(rowIndex);
                    if(newRow == null){
                        newRow = sheet.createRow(rowIndex);
                    }
                    for(int col = 0; col < row.getLastCellNum(); col++) {
                        final Cell rootCell = row.getCell(col);
                        if(rootCell == null){
                            continue;
                        }
                        Cell newCell = newRow.getCell(col);
                        if(newCell == null){
                            newCell = newRow.createCell(col);
                        }
                        if(rootCell.getCellStyle() != null) {
                            final CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.cloneStyleFrom(rootCell.getCellStyle());
                            newCell.setCellStyle(cellStyle);
                        }
                    }
                    setValue(newRow.getCell(cell.getAddress().getColumn()), value.get(i));
                    rowIndex++;
                }
            }
        }
    }
}
