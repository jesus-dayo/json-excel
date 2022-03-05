package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.parser.*;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ColRowRenderer;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.util.CellUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

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

    public void renderByExpression(TemplateRenderedLog templateRenderedLog,
                                   String data,
                                   TemplateColumn templateColumn,
                                   Cell cell) {
        if(templateColumn.isRendered()){
            return;
        }

        String expression = templateColumn.getValue().toString();
        if (!expressionParser.isRegExMatch(expression)) {
            cell.setCellValue(expression);
            return;
        }
        try {
            Stack<Evaluator> evaluators = new Stack<>();
            final String parsedValue = parse(expression, evaluators);

            // object mapping here
            if(parsedValue.contains("#")){
                final String[] split = parsedValue.split("#");
                final String[] path = split[1].split(":");
                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(data).build();
                List<Object> results = (List<Object>)objectExpressionParser.evaluator().evaluate(jsonObjectPath);
                colRowRenderer.render(cell, templateColumn, results, data, split[0], templateRenderedLog );
            } else {
                final String[] path = parsedValue.split(":");
                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(data).build();
                List<Object> results = (List<Object>)objectExpressionParser.evaluator().evaluate(jsonObjectPath);
                final EvaluatedResults evaluatedResults = evaluate(expression, evaluators, results);
                if(evaluatedResults != null) {
                    evaluatedResults.getCellRenderer()
                            .render(cell, templateColumn, evaluatedResults.getResults(), data, null, templateRenderedLog);
                }
            }
        } catch (InvalidExpressionException e) {
            log.error(e.getMessage(), e);
        }
    }

    private EvaluatedResults evaluate(String expression, Stack<Evaluator> evaluators, List<Object> results) {
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

    private String parse( String expression, Stack<Evaluator> evaluators) throws InvalidExpressionException {
        String parsedValue = expressionParser.parse(expression);
        for (Parser parser : registeredParsers) {
            if (parser.isRegExMatch(parsedValue)) {
                parsedValue = parser.parse(parsedValue);
                if (parser.hasEvaluation()) {
                    evaluators.add(parser.evaluator());
                }
            }
        }
        return parsedValue;
    }

    private List<Object> getDataList(TemplateRenderedLog templateRenderedLog, String data, String expression, Cell cell, Stack<Evaluator> evaluators, TemplateColumn templateColumn) throws InvalidExpressionException {
        String parsedValue = expressionParser.parse(expression);
        List<Object> results = null;
        for (Parser parser : registeredParsers) {
            if (parser.isRegExMatch(parsedValue)) {
                parsedValue = parser.parse(parsedValue);
                if (parser instanceof ObjectExpressionParser) {
                    final Evaluator evaluator = ((ObjectExpressionParser) parser).evaluator();
                    results = (List<Object>) evaluator
                                .evaluate(JsonObjectPath.builder()
                                        .path(parsedValue.split(":")).data(data).build());
                    if (evaluators.isEmpty()) {
                        final CellRenderer renderer = (CellRenderer) evaluator.renderer();
                         renderer.render(cell,templateColumn, results,data,null, templateRenderedLog);
                    }
                    break;
                }
                if (parser.hasEvaluation()) {
                    evaluators.add(parser.evaluator());
                }
            }
        }
        return results;
    }

}
