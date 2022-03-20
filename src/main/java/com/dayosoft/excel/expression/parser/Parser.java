package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.renderer.CellRenderer;

public interface Parser {

    String parse(String expression) throws InvalidExpressionException;

    boolean isRegExMatch(String expression);

    CellRenderer renderer();
}
