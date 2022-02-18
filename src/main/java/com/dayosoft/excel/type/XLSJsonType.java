package com.dayosoft.excel.type;

import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor
@Getter
public enum XLSJsonType {

    STR("string",
            value -> {
                if (validValue(value)) {
                    return CellType.STRING;
                }
                return CellType.BLANK;
            },
            (any, cell) -> {
                if (validValue(any) && isNotBlank(cell)) {
                    cell.setCellValue(any.toString());
                }
            },
            (wb, cell) -> {
            }),
    DOUBLE("double",
            value -> CellType.NUMERIC,
            (any, cell) -> {
                if (validValue(any) && isNotBlank(cell)) {
                    cell.setCellValue(any.toDouble());
                }
            },
            (wb, cell) -> {
                if (isNotBlank(cell)) {
                    final HSSFCellStyle hssfCellStyle = wb.createCellStyle();
                    hssfCellStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(hssfCellStyle);
                }
            }),
    DECIMAL("decimal",
            value -> CellType.NUMERIC,
            (any, cell) -> {
                if (validValue(any) && isNotBlank(cell)) {
                    cell.setCellValue(any.toDouble());
                }
            },
            (wb, cell) -> {
                if (isNotBlank(cell)) {
                    final HSSFCellStyle hssfCellStyle = wb.createCellStyle();
                    hssfCellStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00;(#,##0.00)"));
                    cell.setCellStyle(hssfCellStyle);
                }
            });

    private static boolean validValue(Any any) {
        return any.valueType() != ValueType.INVALID && any.valueType() != ValueType.NULL;
    }

    private static boolean isNotBlank(Cell cell) {
        return cell.getCellType() != CellType.BLANK;
    }

    private final String jsonType;
    private final Function<Any, CellType> cellType;
    private final BiConsumer<Any, Cell> valueSetter;
    private final BiConsumer<HSSFWorkbook, Cell> defaultStyleSetter;

    public static XLSJsonType getByJsonType(String jsonType) {
        return Arrays.stream(XLSJsonType.values())
                .filter(xlsJsonType -> xlsJsonType.getJsonType().equals(jsonType))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("jsonType not supported " + jsonType));
    }
}
