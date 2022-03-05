package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.ColArrEvaluator;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ColArrParser implements Parser{

    private final ColArrEvaluator colArrEvaluator;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.COL_ARR_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.COL_ARR_FUNC_EXPRESSION);
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
        return colArrEvaluator;
    }
}
