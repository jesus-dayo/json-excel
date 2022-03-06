package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRenderedLog;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FirstRendererTest {

    FirstRenderer firstRenderer;

    @BeforeEach
    void init(){
        firstRenderer = new FirstRenderer();
    }

    @Test
    void givenCellAndListValue_whenRender_shouldSetCellValue(){
        Cell cell = mock(Cell.class);
        String value = "test";

        firstRenderer.render(cell, "string", TemplateColumn.builder().build(), value, null, null, Collections.emptyList());

        verify(cell, times(1)).setCellValue(value);
    }

}