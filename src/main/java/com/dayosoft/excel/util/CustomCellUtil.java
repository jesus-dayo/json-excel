package com.dayosoft.excel.util;

import com.dayosoft.excel.model.AddressResult;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.model.TemplateSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

import java.util.Optional;

@Slf4j
public class CustomCellUtil {

    public static Object getCellValueAsObject(Cell cell){
        switch (cell.getCellType()){
            case STRING:{
                return cell.getStringCellValue();
            }
            case NUMERIC:{
                return cell.getNumericCellValue();
            }
            case BOOLEAN:{
                return cell.getBooleanCellValue();
            }
            default:{
                return "";
            }
        }
    }

    public static void setCellValue(Cell cell, Object value, String type){
        switch (type){
            case "string":{
                cell.setCellValue(value.toString());
                break;
            }
            case "decimal":{
                cell.setCellValue(Double.parseDouble(value.toString()));
                break;
            }
            default:{
                log.error("Unable to identify type " + type);
            }
        }
    }

    public static void setCellValue(Cell cell, Object value){
        if (value instanceof String) {
            cell.setCellValue(value.toString());
        } else if (value instanceof Integer) {
            cell.setCellValue(Integer.parseInt(value.toString()));
        } else if (value instanceof Double) {
            cell.setCellValue(Double.parseDouble(value.toString()));
        } else {
            log.error("Unable to identify type " + value);
        }
    }

    public static String getCellAddress(Integer rowNum, Integer col){
        return new CellReference(rowNum,col).formatAsString();
    }

    public static AddressResult getAddressResults(Integer row,
                                                  Integer col,
                                                  TemplateSheet templateSheet) {
        final Optional<TemplateRow> foundRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == row).findFirst();
        if(foundRow.isPresent()){
            final TemplateRow rowRef = foundRow.get();
            final Optional<TemplateColumn> foundColumn = rowRef.getColumns().stream().filter(c -> c.getOriginalCol() == col).findFirst();
            if(foundColumn.isPresent()){
                final TemplateColumn colRef = foundColumn.get();
                if(colRef.isRendered()){
                    String address = new CellReference(rowRef.getRowNum(),colRef.getCol()).formatAsString();
                    return AddressResult.builder()
                            .lastRow(colRef.getLastRowNum())
                            .address(address)
                            .build();
                }
            }
        }
        return null;
    }

}
