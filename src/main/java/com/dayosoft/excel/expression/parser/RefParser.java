package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefParser implements Parser{

    private final CellRenderer refRenderer;

    @Override
    public String regExpression() {
        return RegExpression.REF_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return refRenderer;
    }
}
