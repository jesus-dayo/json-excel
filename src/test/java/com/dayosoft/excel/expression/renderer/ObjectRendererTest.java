package com.dayosoft.excel.expression.renderer;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObjectRendererTest {

    ObjectRenderer objectRenderer;

    @BeforeEach
    void init(){
        objectRenderer = new ObjectRenderer();
    }

    @Test
    void givenListOfObjects_whenRender_shouldReturnFirstIfSizeIs1(){
        Cell cell = mock(Cell.class);
        String value = "test";
        List<Object> list = new ArrayList<>();
        list.add(value);

        objectRenderer.render(cell, list);

        verify(cell, times(1)).setCellValue(value);
    }

    @Test
    void givenListOfObjects_whenRender_shouldReturnCommaDelimitedString(){
        Cell cell = mock(Cell.class);
        String hello = "hello";
        String world = "world";

        List<Object> list = new ArrayList<>();
        list.add(hello);
        list.add(world);

        objectRenderer.render(cell, list);

        verify(cell, times(1)).setCellValue(hello+","+world);
    }
}