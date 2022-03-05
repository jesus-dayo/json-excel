package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.KeyDataMap;

public interface KeyExpressionParser {

    KeyDataMap parse(String expression, String data, String checkKey, Object value) throws InvalidExpressionException;

}
