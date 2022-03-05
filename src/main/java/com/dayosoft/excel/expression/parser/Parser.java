package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.renderer.CellRenderer;

import java.util.Stack;

public interface Parser<T> {

    String parse(String expression) throws InvalidExpressionException;

    boolean isRegExMatch(String expression);

    boolean shouldRender(Stack<Evaluator> evaluators);

    boolean hasEvaluation();

    Evaluator evaluator();

    CellRenderer renderer();
}
