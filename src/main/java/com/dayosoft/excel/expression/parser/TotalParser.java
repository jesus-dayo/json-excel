package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TotalParser implements Parser {

    private final CellRenderer totalRenderer;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.TOTAL_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.TOTAL_FUNC_EXPRESSION);
    }

    @Override
    public CellRenderer renderer() {
        return totalRenderer;
    }
}
