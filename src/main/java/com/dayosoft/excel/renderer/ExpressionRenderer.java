package com.dayosoft.excel.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.renderer.evaluator.Evaluator;
import com.dayosoft.excel.renderer.evaluator.FirstEvaluator;
import com.dayosoft.excel.renderer.evaluator.ObjectEvaluator;
import com.dayosoft.excel.renderer.parser.*;
import com.dayosoft.excel.util.JsonDataTraverser;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

@Component
public class ExpressionRenderer {

    public void render(TemplateSheet templateSheet,
                       String data,
                       String expression,
                       Cell cell) {
        List<Object> results = null;
        String value = expression;
        Stack<Evaluator> evaluators = new Stack<>();
        while (value != null) {
            try {
                final String expressionMatch = ExpressionHelper.findExpressionMatch(value);
                if (expressionMatch == null) {
                    break;
                }
                if (RegExpression.EXPRESSION.equals(expressionMatch)) {
                    final ExpressionParser expressionParser = new ExpressionParser();
                    value = expressionParser.parse(value);
                    continue;
                }
                if (RegExpression.FIRST_FUNC_EXPRESSION.equals(expressionMatch)) {
                    final FirstFunctionParser firstFunctionParser = new FirstFunctionParser();
                    value = firstFunctionParser.parse(value);
                    evaluators.add(new FirstEvaluator());
                    continue;
                }
                if (RegExpression.OBJECT_EXPRESSION.equals(expressionMatch)) {
                    final String[] jsonPath = value.split(RegExpression.OBJECT_EXPRESSION);
                    final ObjectEvaluator objectEvaluator = new ObjectEvaluator();
                    results = objectEvaluator.evaluate(JsonObjectPath.builder().path(jsonPath)
                            .data(data)
                            .build());
                    value = null;
                    continue;
                }
            } catch (InvalidExpressionException e) {
                e.printStackTrace();
            }
        }
        if (results != null) {
            Object evaluatedValue = null;
            while (!evaluators.isEmpty()) {
                final Evaluator evaluator = evaluators.pop();
                evaluatedValue = evaluator.evaluate(results);
            }
            if (evaluatedValue != null) {
                if (evaluatedValue instanceof String) {
                    cell.setCellValue(evaluatedValue.toString());
                }
            }
        }
    }

}
