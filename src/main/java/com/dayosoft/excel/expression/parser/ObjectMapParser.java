package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectMapParser implements Parser {

    private final CellRenderer objectMapRenderer;

    @Override
    public String parse(String expression) throws InvalidExpressionException {
        return expression;
    }

    @Override
    public boolean isRegExMatch(String expression) {
        return !ExpressionHelper.isValidExpression(expression, RegExpression.FUNC_EXPRESSION)
                && StringUtils.countMatches(expression, ":") == 1
                && !expression.contains("#");
    }

    @Override
    public CellRenderer renderer() {
        return objectMapRenderer;
    }
}
