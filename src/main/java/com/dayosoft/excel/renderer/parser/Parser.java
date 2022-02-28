package com.dayosoft.excel.renderer.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;

public interface Parser {

    public String parse(String expression) throws InvalidExpressionException;

}
