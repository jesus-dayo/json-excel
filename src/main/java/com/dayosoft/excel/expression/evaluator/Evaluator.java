package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;

public interface Evaluator<T,K, R> {

     K evaluate(T input) throws InvalidObjectExpressionException;

     R renderer();
}
