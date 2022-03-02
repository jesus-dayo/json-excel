package com.dayosoft.excel.expression.evaluator;

public interface Evaluator<T,K, R> {

     K evaluate(T input);

     R renderer();
}
