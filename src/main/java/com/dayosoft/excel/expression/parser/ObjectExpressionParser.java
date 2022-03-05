package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
@RequiredArgsConstructor
public class ObjectExpressionParser implements Parser {

    private final ObjectEvaluator objectEvaluator;

    @Override
    public String parse(String expression) {
        return expression;
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return !ExpressionHelper.isValidExpressions(expression, RegExpression.FUNC_EXPRESSION, RegExpression.EXPRESSION);
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
        return objectEvaluator;
    }

    @Override
    public CellRenderer renderer() {
        return null;
    }
}
