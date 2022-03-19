package com.dayosoft.excel.type;

import com.dayosoft.excel.model.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExcelJsonTypeTest {

    @Test
    void givenStringValue_whenSetValue_shouldSetCorrectValueAndType() {
        Cell cell = spy(Cell.class);
        final String type = "string";
        final Object sample = "sample";
        final Value givenValue = Value.builder().value(sample).type(type).cell(cell).build();

        ExcelJsonType.getByJsonType(type).getValueSetter().accept(givenValue);

        verify(cell, times(1)).setCellValue(sample.toString());
    }

    @Test
    void givenDoubleValue_whenSetValue_shouldSetCorrectValueAndType() {
        Cell cell = spy(Cell.class);
        final String type = "double";
        final Double sample = 1.2;
        final Value givenValue = Value.builder().value(sample).type(type).cell(cell).build();

        ExcelJsonType.getByJsonType(type).getValueSetter().accept(givenValue);

        verify(cell, times(1)).setCellValue(sample);
    }

}