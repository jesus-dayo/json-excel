package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.IndexedColors;

@AllArgsConstructor
@Getter
public enum Color {

    white(IndexedColors.WHITE.getIndex()),
    red(IndexedColors.RED.getIndex());

    private short index;

}
