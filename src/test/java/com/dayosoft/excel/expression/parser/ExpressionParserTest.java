package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    ExpressionParser expressionParser;

    @BeforeEach
    void init(){
        expressionParser = new ExpressionParser();
    }

    @Test
    void givenExpressionString_whenParse_shouldReturnInnerText() throws InvalidExpressionException {
        String expression = "${key:value}";

        final String actual = expressionParser.parse(expression);

        assertEquals("key:value",actual);
    }

}