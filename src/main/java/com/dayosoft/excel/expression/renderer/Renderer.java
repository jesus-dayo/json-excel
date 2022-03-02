package com.dayosoft.excel.expression.renderer;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

@Slf4j
public abstract class Renderer {

     protected void setValue(Cell cell, Object value){
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
