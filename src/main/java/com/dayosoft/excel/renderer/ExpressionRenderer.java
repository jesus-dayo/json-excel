package com.dayosoft.excel.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.renderer.evaluator.Evaluator;
import com.dayosoft.excel.renderer.parser.*;
import com.dayosoft.excel.util.JsonDataTraverser;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Component
public class ExpressionRenderer {

    public void render(TemplateSheet templateSheet,
                       JsonDataTraverser jsonTraverser,
                       String expression,
                       Cell cell) {
        String value = expression;
        PriorityQueue<Evaluator> evaluators = new PriorityQueue<>();
        while(ExpressionHelper.isValidExpression(expression)) {
            try {
                final String expressionMatch = ExpressionHelper.findExpressionMatch(expression);
                if (RegExpression.EXPRESSION.equals(expressionMatch)) {
                    final ExpressionParser expressionParser = new ExpressionParser();
                    value = expressionParser.parse(expressionMatch);
                }
                if (RegExpression.FIRST_FUNC_EXPRESSION.equals(expressionMatch)) {
                    final FirstFunctionParser firstFunctionParser = new FirstFunctionParser();
                    value = firstFunctionParser.parse(expressionMatch);
                }
                if (RegExpression.OBJECT_EXPRESSION.equals(expressionMatch)) {
                    final String[] jsonPath = value.split(RegExpression.OBJECT_EXPRESSION);
                }
            } catch (InvalidExpressionException e) {
                e.printStackTrace();
            }
        }
    }

}
