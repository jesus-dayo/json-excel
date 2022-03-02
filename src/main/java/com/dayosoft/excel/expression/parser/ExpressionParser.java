package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import org.springframework.stereotype.Component;

@Component
public class ExpressionParser implements Parser{

    @Override
    public String parse(String value) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(value, RegExpression.EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.EXPRESSION);
    }

    @Override
    public boolean done(String expression) {
        return false;
    }
}
