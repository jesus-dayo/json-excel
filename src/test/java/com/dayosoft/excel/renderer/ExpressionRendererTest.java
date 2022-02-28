package com.dayosoft.excel.renderer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionRendererTest {

    ExpressionRenderer interpreter;

    @BeforeEach
    void init(){
        interpreter = new ExpressionRenderer();
    }

    @Test
    void givenTemplateAndJsonDataAndExpression_whenInterpret_shouldApplyCellValue(){

    }

}