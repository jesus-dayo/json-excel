package com.dayosoft.excel.expression.mapper;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.KeyValue;
import com.dayosoft.excel.model.MappedResults;

public interface ListMapper extends Mapper<MappedResults> {
    MappedResults map(String expression, String data, KeyValue... dependencyKeyValue) throws InvalidObjectExpressionException;
}
