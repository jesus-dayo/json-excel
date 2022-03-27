package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.renderer.CellRenderer;

public interface Parser {

    String regExpression();

    default String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, regExpression());
    }

    default boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, regExpression());
    }

    CellRenderer renderer();
}
