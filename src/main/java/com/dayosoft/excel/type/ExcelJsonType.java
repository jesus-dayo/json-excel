package com.dayosoft.excel.type;

import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor
@Getter
public enum ExcelJsonType {

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
                    final CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
                    cell.setCellStyle(cellStyle);
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
                    final CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00;(#,##0.00)"));
                    cell.setCellStyle(cellStyle);
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

    public static ExcelJsonType getByJsonType(String jsonType) {
        return Arrays.stream(ExcelJsonType.values())
                .filter(xlsJsonType -> xlsJsonType.getJsonType().equals(jsonType))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("jsonType not supported " + jsonType));
    }
}
