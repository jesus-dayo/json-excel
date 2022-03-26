package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TotalColParser implements Parser {

    private final CellRenderer totalColRenderer;

    @Override
    public String regExpression() {
        return RegExpression.TOTAL_COL_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return totalColRenderer;
    }
}
