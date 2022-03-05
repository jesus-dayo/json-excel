package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class AnotherRowParser implements Parser{

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
    }

    @Override
    public boolean shouldRender(Stack stack) {
        return false;
    }

    @Override
    public boolean hasEvaluation() {
        return false;
    }

    @Override
    public Evaluator evaluator() {
        return null;
    }

    @Override
    public CellRenderer renderer() {
        return null;
    }
}
