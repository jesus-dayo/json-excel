package com.dayosoft.excel.renderer.parser;

import org.springframework.stereotype.Component;

@Component
public class ObjectExpressionParser implements Parser {

    private final static String DELIMITER = ":";

    @Override
    public String parse(String expression) {
        return expression;
    }
}
