package com.dayosoft.excel.styles;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.TriConsumer;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;

@AllArgsConstructor
public enum StylesMapper {

    fillForegroundColorColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fillForegroundColorColor.name())) {
            cellStyle.setFillForegroundColor(Short.valueOf(styles.get(StyleProps.fillForegroundColorColor.name())));
        }
    }),
    fillPatternType((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fillPatternType.name())) {
            cellStyle.setFillPattern(FillPatternType.valueOf(styles.get(StyleProps.fillPatternType.name())));
        }
    }),
    dataFormatString((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.dataFormatString.name())) {
            final CreationHelper creationHelper = workbook.getCreationHelper();
            final short format = creationHelper.createDataFormat().getFormat(styles.get(StyleProps.dataFormatString.name()));
            cellStyle.setDataFormat(format);
        }
    }),
    bold((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.bold.name())) {
            if (cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setBold(Boolean.valueOf(styles.get(StyleProps.bold.name())));
            } else {
                ((HSSFCellStyle) cellStyle).getFont(workbook).setBold(Boolean.valueOf(styles.get(StyleProps.bold.name())));
            }
        }
    }),
    fillBackgroundColorColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fillBackgroundColorColor.name())) {
            cellStyle.setFillBackgroundColor(Short.valueOf(styles.get(StyleProps.fillBackgroundColorColor.name())));
        }
    }),
    italic((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.italic.name())) {
            if (cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setItalic(Boolean.valueOf(styles.get(StyleProps.italic.name())));
            } else {
                ((HSSFCellStyle) cellStyle).getFont(workbook).setItalic(Boolean.valueOf(styles.get(StyleProps.italic.name())));
            }
        }
    }),
    borderTop((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.borderTop.name())) {
            cellStyle.setBorderTop(BorderStyle.valueOf(styles.get(StyleProps.borderTop.name())));
        }
    }),
    borderLeft((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.borderLeft.name())) {
            cellStyle.setBorderLeft(BorderStyle.valueOf(styles.get(StyleProps.borderLeft.name())));
        }
    }),
    borderRight((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.borderRight.name())) {
            cellStyle.setBorderRight(BorderStyle.valueOf(styles.get(StyleProps.borderRight.name())));
        }
    }),
    borderBottom((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.borderBottom.name())) {
            cellStyle.setBorderBottom(BorderStyle.valueOf(styles.get(StyleProps.borderBottom.name())));
        }
    }),
    leftBorderColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.leftBorderColor.name())) {
            cellStyle.setLeftBorderColor(Short.valueOf(styles.get(StyleProps.leftBorderColor.name())));
        }
    }),
    rightBorderColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.rightBorderColor.name())) {
            cellStyle.setRightBorderColor(Short.valueOf(styles.get(StyleProps.rightBorderColor.name())));
        }
    }),
    topBorderColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.topBorderColor.name())) {
            cellStyle.setTopBorderColor(Short.valueOf(styles.get(StyleProps.topBorderColor.name())));
        }
    }),
    bottomBorderColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.bottomBorderColor.name())) {
            cellStyle.setBottomBorderColor(Short.valueOf(styles.get(StyleProps.bottomBorderColor.name())));
        }
    }),
    shrinkToFit((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.shrinkToFit.name())) {
            cellStyle.setShrinkToFit(Boolean.valueOf(styles.get(StyleProps.shrinkToFit.name())));
        }
    }),
    fontFamily((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fontFamily.name())) {
            if (cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setFontName(styles.get(StyleProps.fontFamily.name()));
            } else {
                ((HSSFCellStyle) cellStyle).getFont(workbook).setFontName(styles.get(StyleProps.fontFamily.name()));
            }
        }
    }),
    fontHeight((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fontHeight.name())) {
            if (cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setFontHeight(Short.valueOf(styles.get(StyleProps.fontHeight.name())));
            } else {
                ((HSSFCellStyle) cellStyle).getFont(workbook).setFontHeight(Short.valueOf(styles.get(StyleProps.fontHeight.name())));
            }
        }
    }),
    alignment((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.alignment.name())) {
            cellStyle.setAlignment(HorizontalAlignment.valueOf(styles.get(StyleProps.alignment.name())));
        }
    }),
    wrapText((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.wrapText.name())) {
            cellStyle.setWrapText(Boolean.valueOf(styles.get(StyleProps.wrapText.name())));
        }
    }),
    verticalAlignment((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.verticalAlignment.name())) {
            cellStyle.setVerticalAlignment(VerticalAlignment.valueOf(styles.get(StyleProps.verticalAlignment.name())));
        }
    }),
    fontColor((workbook, cellStyle, styles) -> {
        if (styles.containsKey(StyleProps.fontColor.name())) {
            if (cellStyle instanceof XSSFCellStyle) {
                ((XSSFCellStyle) cellStyle).getFont().setColor(Short.valueOf(styles.get(StyleProps.fontColor.name())));
            } else {
                ((HSSFCellStyle) cellStyle).getFont(workbook).setColor(Short.valueOf(styles.get(StyleProps.fontColor.name())));
            }
        }
    });

    private TriConsumer<Workbook, CellStyle, Map<String, String>> xssfStyleSetter;

    public static void applyStyles(Workbook workbook, CellStyle cellStyle, Map<String, String> styles) {
        Arrays.stream(StylesMapper.values()).forEach(stylesMapper -> {
            stylesMapper.xssfStyleSetter.accept(workbook, cellStyle, styles);
        });
    }
}
