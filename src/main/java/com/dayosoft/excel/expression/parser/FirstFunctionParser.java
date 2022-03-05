package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.evaluator.FirstEvaluator;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
@RequiredArgsConstructor
public class FirstFunctionParser implements Parser {

    private final FirstEvaluator firstEvaluator;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.FIRST_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.FIRST_FUNC_EXPRESSION);
    }

    @Override
    public boolean shouldRender(Stack stack) {
        return false;
    }

    @Override
    public boolean hasEvaluation() {
        return true;
    }

    @Override
    public Evaluator evaluator() {
        return firstEvaluator;
    }

    @Override
    public CellRenderer renderer() {
        return null;
    }
}
