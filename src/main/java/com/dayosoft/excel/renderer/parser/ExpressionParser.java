package com.dayosoft.excel.renderer.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;

public class ExpressionParser implements Parser{

    @Override
    public String parse(String value) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(value, RegExpression.EXPRESSION);
    }
}
