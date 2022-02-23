package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.function.BiConsumer;

@AllArgsConstructor
@Getter
public enum Style {

    defaultBlankColor((xssfCellStyle, value)-> {
        xssfCellStyle.setFillForegroundColor(Color.valueOf(value).getIndex());
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    });

    private BiConsumer<XSSFCellStyle, String> formatter;


}
