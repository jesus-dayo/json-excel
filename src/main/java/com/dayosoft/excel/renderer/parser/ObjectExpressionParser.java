package com.dayosoft.excel.renderer.parser;

import com.dayosoft.excel.renderer.evaluator.Evaluator;
import com.dayosoft.excel.renderer.evaluator.ObjectEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectExpressionParser implements ParserEvaluator {

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
    public Evaluator evaluator() {
        return objectEvaluator;
    }
}
