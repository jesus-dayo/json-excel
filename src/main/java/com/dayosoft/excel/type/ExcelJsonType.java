package com.dayosoft.excel.type;

import com.dayosoft.excel.model.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public enum ExcelJsonType {

    STR("string", value -> {
        final Object objectVal = value.getValue();
        if (objectVal == null || objectVal.equals("null")) {
            value.getCell().setCellValue(StringUtils.EMPTY);
        } else {
            value.getCell().setCellValue(objectVal.toString());
        }
    }),
    DOUBLE("double", value -> {
        final Object objectVal = value.getValue();
        if (objectVal == null) {
            value.getCell().setCellValue(0.0);
        } else {
            value.getCell().setCellValue(Double.parseDouble(objectVal.toString()));
        }
    }),
    DECIMAL("decimal", value -> {
        DOUBLE.getValueSetter().accept(value);
    });

    private final String jsonType;
    private final Consumer<Value> valueSetter;

    public static ExcelJsonType getByJsonType(String jsonType) {
        return Arrays.stream(ExcelJsonType.values())
                .filter(xlsJsonType -> xlsJsonType.getJsonType().equals(jsonType))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("jsonType not supported " + jsonType));
    }
}
