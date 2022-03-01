package com.dayosoft.excel.renderer.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.renderer.evaluator.Evaluator;
import com.dayosoft.excel.renderer.evaluator.FirstEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstFunctionParser implements ParserEvaluator {

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
    public boolean done(String expression) {
        return false;
    }

    @Override
    public Evaluator evaluator() {
        return firstEvaluator;
    }
}
