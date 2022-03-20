package com.dayosoft.excel.expression.mapper;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;

import java.util.Map;

public interface Mapper<T> {

    T map(String expression, String data, Map<String, Object> dependencyKeyValue) throws InvalidObjectExpressionException;

}
