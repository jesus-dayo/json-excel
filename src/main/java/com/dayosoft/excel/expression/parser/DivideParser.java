package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DivideParser implements Parser{

    private final CellRenderer divRenderer;


    @Override
    public String regExpression() {
        return RegExpression.DIVIDE_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return divRenderer;
    }
}
