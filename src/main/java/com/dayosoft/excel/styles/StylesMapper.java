package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;

@AllArgsConstructor
public enum StylesMapper {

    fillForegroundColorColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fillForegroundColorColor.name())) {
            cellStyle.setFillForegroundColor(Short.valueOf(styles.get(StyleProps.fillForegroundColorColor.name())));
        }
    }),
    fillPatternType((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fillPatternType.name())) {
            cellStyle.setFillPattern(FillPatternType.valueOf(styles.get(StyleProps.fillPatternType.name())));
        }
    }),
    dataFormat((cellStyle, styles)->{
            if(styles.containsKey(StyleProps.dataFormat.name())) {
                cellStyle.setDataFormat(Short.valueOf(styles.get(StyleProps.dataFormat.name())));
            }
    }),
    topBorderColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.topBorderColor.name())) {
            cellStyle.setTopBorderColor(Short.valueOf(styles.get(StyleProps.topBorderColor.name())));
        }
    }),
    bottomBorderColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.bottomBorderColor.name())) {
            cellStyle.setBottomBorderColor(Short.valueOf(styles.get(StyleProps.bottomBorderColor.name())));
        }
    }),
    bold((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.bold.name())) {
            if(cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setBold(Boolean.valueOf(styles.get(StyleProps.bold.name())));
            }
        }
    }),
    fillBackgroundColorColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fillBackgroundColorColor.name())) {
            cellStyle.setFillBackgroundColor(Short.valueOf(styles.get(StyleProps.fillBackgroundColorColor.name())));
        }
    }),
    borderTop((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.borderTop.name())) {
            cellStyle.setBorderTop(BorderStyle.valueOf(styles.get(StyleProps.borderTop.name())));
        }
    }),
    italic((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.italic.name())) {
            if(cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setItalic(Boolean.valueOf(styles.get(StyleProps.italic.name())));
            }
        }
    }),
    borderLeft((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.borderLeft.name())) {
            cellStyle.setBorderLeft(BorderStyle.valueOf(styles.get(StyleProps.borderLeft.name())));
        }
    }),
    rightBorderColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.rightBorderColor.name())) {
            cellStyle.setRightBorderColor(Short.valueOf(styles.get(StyleProps.rightBorderColor.name())));
        }
    }),
    shrinkToFit((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.shrinkToFit.name())) {
            cellStyle.setShrinkToFit(Boolean.valueOf(styles.get(StyleProps.shrinkToFit.name())));
        }
    }),
    fontFamily((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fontFamily.name())) {
            if(cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setFontName(styles.get(StyleProps.fontFamily.name()));
            }
        }
    }),
    leftBorderColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.leftBorderColor.name())) {
            cellStyle.setRightBorderColor(Short.valueOf(styles.get(StyleProps.leftBorderColor.name())));
        }
    }),
    borderRight((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.borderRight.name())) {
            cellStyle.setBorderLeft(BorderStyle.valueOf(styles.get(StyleProps.borderRight.name())));
        }
    }),
    fontHeight((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fontHeight.name())) {
            if(cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle)cellStyle).getFont().setFontHeight(Short.valueOf(styles.get(StyleProps.fontHeight.name())));
            }
        }
    }),
    alignment((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.alignment.name())) {
            cellStyle.setAlignment(HorizontalAlignment.valueOf(styles.get(StyleProps.alignment.name())));
        }
    }),
    borderBottom((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.borderBottom.name())) {
            cellStyle.setBorderBottom(BorderStyle.valueOf(styles.get(StyleProps.borderBottom.name())));
        }
    }),
    wrapText((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.wrapText.name())) {
            cellStyle.setWrapText(Boolean.valueOf(styles.get(StyleProps.wrapText.name())));
        }
    }),
    verticalAlignment((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.verticalAlignment.name())) {
            cellStyle.setVerticalAlignment(VerticalAlignment.valueOf(styles.get(StyleProps.verticalAlignment.name())));
        }
    }),
    fontColor((cellStyle, styles)->{
        if(styles.containsKey(StyleProps.fontColor.name())) {
            if(cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle)cellStyle).getFont().setColor(Short.valueOf(styles.get(StyleProps.fontColor.name())));
            }
        }
    });

    private BiConsumer<CellStyle, Map<String,String>> xssfStyleSetter;

    public static void applyStyles(CellStyle cellStyle, Map<String, String> styles){
        Arrays.stream(StylesMapper.values()).forEach(stylesMapper -> {
            stylesMapper.xssfStyleSetter.accept(cellStyle, styles);
        });
    }
}
