package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public boolean done(String expression) {
        return true;
    }

    @Override
    public boolean hasEvaluation() {
        return true;
    }

    @Override
    public Evaluator evaluator() {
        return objectEvaluator;
    }
}
