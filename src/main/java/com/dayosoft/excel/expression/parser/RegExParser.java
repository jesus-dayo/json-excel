package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegExParser implements Parser {

    private final CellRenderer regExpressionRenderer;

    @Override
    public String regExpression() {
        return RegExpression.REGEX_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return regExpressionRenderer;
    }
}
