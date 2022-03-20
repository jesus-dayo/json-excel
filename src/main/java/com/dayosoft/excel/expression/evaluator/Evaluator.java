package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;

public interface Evaluator<T, R> {

     R evaluate(T input) throws InvalidObjectExpressionException;
}
