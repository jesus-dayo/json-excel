package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.evaluator.FirstEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public boolean done(String expression) {
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
}
