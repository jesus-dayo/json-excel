package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.BorderStyle;

@AllArgsConstructor
@Getter
public enum Border {

    none(BorderStyle.NONE);

    private BorderStyle borderStyle;
}
