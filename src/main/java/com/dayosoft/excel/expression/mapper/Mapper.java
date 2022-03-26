package com.dayosoft.excel.expression.mapper;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.KeyValue;

public interface Mapper<T> {

    T map(String expression, String data, KeyValue... dependencyKeyValue) throws InvalidObjectExpressionException;

}
