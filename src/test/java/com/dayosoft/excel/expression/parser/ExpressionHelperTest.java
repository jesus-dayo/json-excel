package com.dayosoft.excel.expression.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionHelperTest {

    @Test
    void givenDateExpression_whenIsValidExpression_shouldReturnFalse() {
        String given = "2022-03-08T16:00:00.000+00:00";

        assertFalse(ExpressionHelper.isValidExpression(given));
    }

    @Test
    void givenDateExpression_whenIsValidExpression_shouldReturnTrue() {
        String given = "Client Details:clientCode1";

        assertTrue(ExpressionHelper.isValidExpression(given));
    }
}