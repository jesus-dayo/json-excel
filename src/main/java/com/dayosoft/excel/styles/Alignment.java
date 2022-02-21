package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

@AllArgsConstructor
@Getter
public enum Alignment {

    center(HorizontalAlignment.CENTER),
    right(HorizontalAlignment.RIGHT);

    private HorizontalAlignment alignment;

}
