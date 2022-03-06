package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.TodayRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Stack;

@RequiredArgsConstructor
@Component
public class TodayParser implements Parser{

    private final TodayRenderer todayRenderer;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.TODAY_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.TODAY_FUNC_EXPRESSION);
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
        return todayRenderer;
    }

    @Override
    public boolean shouldRender(Stack stack) {
        return true;
    }
}
