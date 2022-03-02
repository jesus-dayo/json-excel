package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;

public interface Parser {

    String parse(String expression) throws InvalidExpressionException;

    boolean isRegExMatch(String expression);

    boolean done(String expression);
}
