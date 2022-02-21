package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.function.BiConsumer;

@AllArgsConstructor
@Getter
public enum Style {

    backgroundColor((xssfCellStyle, value)-> {
        xssfCellStyle.setFillForegroundColor(Color.valueOf(value).getIndex());
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }),
    textAlign((xssfCellStyle, value)-> {
        xssfCellStyle.setAlignment(Alignment.valueOf(value).getAlignment());
    }),
    border((xssfCellStyle, value)-> {
        xssfCellStyle.setBorderBottom(Border.valueOf(value).getBorderStyle());
        xssfCellStyle.setBorderLeft(Border.valueOf(value).getBorderStyle());
        xssfCellStyle.setBorderRight(Border.valueOf(value).getBorderStyle());
        xssfCellStyle.setBorderTop(Border.valueOf(value).getBorderStyle());
    }),
    fontFamily((xssfCellStyle, value)-> {
        xssfCellStyle.getFont().setFontName(value);
    });

    private BiConsumer<XSSFCellStyle, String> formatter;


}
