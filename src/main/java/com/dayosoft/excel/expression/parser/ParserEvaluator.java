package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.evaluator.Evaluator;

public interface ParserEvaluator extends Parser {

    Evaluator evaluator();

}
