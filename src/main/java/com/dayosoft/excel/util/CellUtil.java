package com.dayosoft.excel.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

@Slf4j
public class CellUtil {

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

}
