package com.dayosoft.excel.util;

import org.apache.poi.ss.usermodel.Cell;

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

}
