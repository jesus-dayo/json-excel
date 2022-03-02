package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.parser.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionRenderingEngine {

    //parse
    //evaluate
    //render

    private final List<ParserEvaluator> registeredParsers;
    private final ExpressionParser expressionParser;

    public void renderByExpression(TemplateSheet templateSheet,
                                   String data,
                                   String expression,
                                   Cell cell) {
        if (!expressionParser.isRegExMatch(expression)) {
            return;
        }
        try {
            List<Object> results = null;
            Stack<Evaluator> evaluators = new Stack<>();
            String parsedValue = expressionParser.parse(expression);
            for (Parser parser : registeredParsers) {

                if (parser.isRegExMatch(parsedValue)) {
                    parsedValue = parser.parse(parsedValue);
                    if (parser instanceof ObjectExpressionParser) {
                        final Evaluator evaluator = ((ObjectExpressionParser) parser).evaluator();
                        results = (List<Object>) evaluator
                                .evaluate(JsonObjectPath.builder()
                                        .path(parsedValue.split(":")).data(data).build());
                        if(evaluators.isEmpty()){
                            final CellRenderer renderer = (CellRenderer)evaluator.renderer();
                            renderer.render(cell, results);
                            return;
                        }
                        break;
                    }
                    if (parser instanceof ParserEvaluator) {
                        evaluators.add(((ParserEvaluator) parser).evaluator());
                    }
                }
            }
            Object evaluatedValue;
            while (!evaluators.isEmpty()) {
                final Evaluator evaluator = evaluators.pop();
                evaluatedValue = evaluator.evaluate(results);
                if (evaluators.isEmpty() && evaluatedValue != null) {
                    final Object renderer = evaluator.renderer();
                    if (renderer instanceof CellRenderer) {
                        log.info("Rendering cell " + expression + " with renderer " + renderer.getClass().getTypeName());
                        CellRenderer cellRenderer = (CellRenderer) renderer;
                        cellRenderer.render(cell, evaluatedValue);
                    }
                }
            }
        } catch (InvalidExpressionException e) {
            log.error(e.getMessage(), e);
        }
    }

}
