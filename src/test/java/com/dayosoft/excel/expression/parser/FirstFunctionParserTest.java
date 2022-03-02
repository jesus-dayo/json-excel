package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.FirstEvaluator;
import com.dayosoft.excel.expression.renderer.FirstRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FirstFunctionParserTest {

    FirstFunctionParser firstFunctionParser;

    @BeforeEach
    void init(){
        firstFunctionParser = new FirstFunctionParser(new FirstEvaluator(new FirstRenderer()));
    }

    @Test
    void givenFirstFunctionExpression_whenParsed_shouldReturnList() throws InvalidExpressionException {

        final String actual = firstFunctionParser.parse("first(Client Details:clientCode1)");

        assertNotNull(actual);
        assertEquals("Client Details:clientCode1", actual);
    }

}