package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectMapParser implements Parser {

    private final CellRenderer objectMapRenderer;

    @Override
    public String regExpression() {
        return RegExpression.OBJECT_EXPRESSION;
    }

    @Override
    public CellRenderer renderer() {
        return objectMapRenderer;
    }
}
