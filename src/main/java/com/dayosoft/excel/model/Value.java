package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;

@Builder
@Getter
public class Value {

    private Cell cell;
    private Object value;

}
