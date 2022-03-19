package com.dayosoft.excel.expression.mapper;

public interface Mapper<T> {

    T map(String json, String expression, String type);

}
