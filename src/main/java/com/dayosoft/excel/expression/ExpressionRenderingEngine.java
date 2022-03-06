package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.parser.*;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ColRowRenderer;
import com.dayosoft.excel.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionRenderingEngine {

    //parse
    //evaluate
    //render

    private final List<Parser> registeredParsers;
    private final ExpressionParser expressionParser;
    private final ColRowRenderer colRowRenderer;
    private final ObjectExpressionParser objectExpressionParser;

    public List<DelayedRender> renderByExpression(String data,
                                   TemplateColumn templateColumn,
                                   Cell cell) {
        List<DelayedRender> delayedRenders = new ArrayList<>();
        if (templateColumn.isRendered()) {
            return delayedRenders;
        }

        String expression = templateColumn.getValue().toString();
        if (!expressionParser.isRegExMatch(expression)) {
            cell.setCellValue(expression);
            templateColumn.setRendered(true);
            return delayedRenders;
        }
        try {
            Stack<Evaluator> evaluators = new Stack<>();
            final ParsedResults parsedResults = parse(expression, evaluators);

            if (parsedResults.getParser() != null && parsedResults.getParser().shouldRender(evaluators)) {
                parsedResults.getParser().renderer().render(cell, "string",
                        templateColumn, parsedResults.getParsedValue(), data, null, delayedRenders);
                return delayedRenders;
            }

            // object mapping here
            if (parsedResults.getParsedValue().contains("#")) {
                final String[] split = parsedResults.getParsedValue().split("#");
                final String[] path = split[1].split(":");
                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(data).build();
                final ObjectExpressionResults objectExpressionResults = (ObjectExpressionResults)objectExpressionParser.evaluator().evaluate(jsonObjectPath);
                colRowRenderer.render(cell, objectExpressionResults.getType(), templateColumn, objectExpressionResults.getListOfValues(), data, split[0], delayedRenders);
            } else {
                String specExpression = expressionParser.parse(expression);
                if(!ExpressionHelper.isValidExpression(specExpression)){
                    log.error(specExpression + " is not yet supported");
                    templateColumn.setRendered(true);
                    cell.setCellValue(expression);
                    return delayedRenders;
                }
                final String[] path = parsedResults.getParsedValue().split(":");
                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(data).build();
                final ObjectExpressionResults objectExpressionResults = (ObjectExpressionResults)objectExpressionParser.evaluator().evaluate(jsonObjectPath);
                final List<Object> results = objectExpressionResults.getListOfValues();
                if(results == null || results.isEmpty()){
                    templateColumn.setRendered(true);
                    return delayedRenders;
                }
                final EvaluatedResults evaluatedResults = evaluate(expression, evaluators, results);
                if (evaluatedResults != null) {
                    evaluatedResults.getCellRenderer()
                            .render(cell,objectExpressionResults.getType(), templateColumn, evaluatedResults.getResults(), data, null, delayedRenders);
                }
            }
        } catch (Exception e) {
            templateColumn.setRendered(true);
            cell.setCellValue(expression);
            log.error(e.getMessage(), e);
        }
        return delayedRenders;
    }

    private EvaluatedResults evaluate(String expression, Stack<Evaluator> evaluators, List<Object> results) throws InvalidObjectExpressionException {
        Object evaluatedValue;
        while (!evaluators.isEmpty()) {
            final Evaluator evaluator = evaluators.pop();
            evaluatedValue = evaluator.evaluate(results);
            if (evaluators.isEmpty() && evaluatedValue != null) {
                final Object renderer = evaluator.renderer();
                if (renderer instanceof CellRenderer) {
                    log.info("Rendering cell " + expression + " with renderer " + renderer.getClass().getTypeName());
                    return EvaluatedResults.builder().results(evaluatedValue).cellRenderer((CellRenderer) renderer).build();
                }
            }
        }
        return null;
    }

    private ParsedResults parse(String expression, Stack<Evaluator> evaluators) throws InvalidExpressionException {
        String parsedValue = expressionParser.parse(expression);
        Parser last = null;
        for (Parser parser : registeredParsers) {
            if (parser.isRegExMatch(parsedValue)) {
                parsedValue = parser.parse(parsedValue);
                if (parser.shouldRender(evaluators)) {
                    return ParsedResults.builder().parsedValue(parsedValue)
                            .parser(parser).build();
                }
                if (parser.hasEvaluation()) {
                    evaluators.add(parser.evaluator());
                }
                last = parser;
            }
        }
        return ParsedResults.builder().parsedValue(parsedValue)
                .parser(last).build();
    }

}
