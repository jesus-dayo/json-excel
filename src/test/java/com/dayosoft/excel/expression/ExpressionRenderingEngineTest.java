package com.dayosoft.excel.expression;

import com.dayosoft.excel.expression.parser.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ExpressionRenderingEngineTest {

    ExpressionRenderingEngine renderer;

    @BeforeEach
    void init(){
        renderer = new ExpressionRenderingEngine(new ArrayList<>(), new ExpressionParser());
    }

    @Test
    void givenTemplateAndJsonDataAndExpression_whenRender_shouldApplyCellValue(){

    }

}