package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TotalParser implements Parser {

    private final CellRenderer totalRenderer;

    @Override
    public String regExpression() {
        return RegExpression.TOTAL_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return totalRenderer;
    }
}
