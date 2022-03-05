package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;

public interface Parser<T> {

    String parse(String expression) throws InvalidExpressionException;

    boolean isRegExMatch(String expression);

    boolean done(String expression);

    boolean hasEvaluation();

    Evaluator evaluator();
}
