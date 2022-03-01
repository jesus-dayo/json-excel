package com.dayosoft.excel.renderer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ExpressionRendererTest {

    ExpressionRenderer renderer;

    @BeforeEach
    void init(){
        renderer = new ExpressionRenderer(new ArrayList<>());
    }

    @Test
    void givenTemplateAndJsonDataAndExpression_whenRender_shouldApplyCellValue(){

    }

}