package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.parser.*;
import com.dayosoft.excel.util.CellUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.*;

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
                        if (parsedValue.contains("#")) {
                            final String[] splitExpression = parsedValue.split("#");
                            final String keyDest = splitExpression[0];
                            final String[] keyValue = keyDest.split("-");
                            Map<String, Object> keyValueMap = new HashMap<>();
                            int i = 0;
                            while (i < keyValue.length) {
                                final String cellNumber = keyValue[i + 1];
                                final Row row = cell.getRow();
                                final Object cellValueAsObject = CellUtil.getCellValueAsObject(row.getCell(Integer.parseInt(cellNumber)));
                                keyValueMap.put(keyValue[i], cellValueAsObject);
                                i = i + 2;
                            }
                            String path = splitExpression[1];
                            if (path.contains(RegExpression.OBJECT_EXPRESSION)) {
                                results = (List<Object>) evaluator
                                        .evaluate(JsonObjectPath.builder()
                                                .path(path.split(":"))
                                                .data(data)
                                                .build());
                            }
                        } else {
                            results = (List<Object>) evaluator
                                    .evaluate(JsonObjectPath.builder()
                                            .path(parsedValue.split(":")).data(data).build());
                        }
                        if (evaluators.isEmpty()) {
                            final CellRenderer renderer = (CellRenderer) evaluator.renderer();
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
