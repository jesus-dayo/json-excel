package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ColArrParser implements Parser{

    private final CellRenderer colArrRenderer;

    @Override
    public String regExpression() {
        return RegExpression.COL_ARR_FUNC_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return colArrRenderer;
    }
}
