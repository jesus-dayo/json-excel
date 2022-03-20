package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefParser implements Parser{

    private final CellRenderer refRenderer;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.REF_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.REF_FUNC_EXPRESSION);
    }

    @Override
    public CellRenderer renderer() {
        return refRenderer;
    }
}
