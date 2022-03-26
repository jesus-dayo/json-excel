package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SumParser implements Parser{

    private final CellRenderer sumRenderer;

    @Override
    public String regExpression() {
        return RegExpression.SUM_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return sumRenderer;
    }
}
