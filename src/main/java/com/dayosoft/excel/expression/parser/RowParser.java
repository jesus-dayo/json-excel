package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RowParser implements Parser {

    private final CellRenderer colRowRenderer;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return ExpressionHelper.extractStringFromExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
    }

    @Override
    public CellRenderer renderer() {
        return colRowRenderer;
    }
}
