package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.expression.mapper.ListMapper;
import com.dayosoft.excel.expression.parser.Parser;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateColumn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionRenderingEngine {

    private final List<Parser> registeredParsers;
    private final ListMapper jsonListMapper;

    public void renderByExpression(RenderRequest renderRequest) throws ExpressionException {
        final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
        if (templateColumn.isRendered()) {
            return;
        }
        String expression = templateColumn.getValue().toString();
        if (!ExpressionHelper.isValidExpression(expression, RegExpression.EXPRESSION)) {
            defaultRender(renderRequest, expression);
            return;
        }

        final String subExpression = ExpressionHelper.extractStringFromExpression(expression, RegExpression.EXPRESSION);
        final Optional<Parser> parserMatch = registeredParsers.stream()
                .filter(parser -> parser.isRegExMatch(subExpression)).findFirst();
        if (parserMatch.isEmpty()) {
            defaultRender(renderRequest, expression);
        }

        if (parserMatch.isPresent()) {
            final Parser foundParser = parserMatch.get();
            final String jsonPath = foundParser.parse(subExpression);
            final MappedResults mappedResults = jsonListMapper.map(jsonPath, renderRequest.getData(), null);
            foundParser.renderer().render(renderRequest, mappedResults);
        }
    }

//        String expression = templateColumn.getValue().toString();
//        if (!expressionParser.isRegExMatch(expression)) {
//            renderRequest.getCell().setCellValue(expression);
//            templateColumn.setRendered(true);
//            return;
//        }
//        try {
//            Stack<Evaluator> evaluators = new Stack<>();
//            final ParsedResults parsedResults = parse(expression, evaluators);
//
//            if (parsedResults.getParser() != null && parsedResults.getParser().shouldRender(evaluators)) {
//                parsedResults.getParser().renderer().render(renderRequest.getCell(), "string",
//                        templateColumn, parsedResults.getParsedValue(), renderRequest.getData(), null, renderRequest.getDelayedRenders());
//                return;
//            }
//
//            // object mapping here
//            if (parsedResults.getParsedValue().contains("#")) {
//                final String[] split = parsedResults.getParsedValue().split("#");
//                final String[] path = split[1].split(":");
//                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(renderRequest.getData()).build();
//                final ObjectExpressionResults objectExpressionResults = (ObjectExpressionResults) objectExpressionParser.evaluator().evaluate(jsonObjectPath);
//                colRowRenderer.render(renderRequest.getCell(), objectExpressionResults.getType(), templateColumn, objectExpressionResults.getListOfValues(), renderRequest.getData(), split[0], renderRequest.getDelayedRenders());
//            } else {
//                String specExpression = expressionParser.parse(expression);
//                if (!ExpressionHelper.isValidExpression(specExpression)) {
//                    log.error(specExpression + " is not yet supported");
//                    templateColumn.setRendered(true);
//                    renderRequest.getCell().setCellValue(expression);
//                    return;
//                }
//                templateColumn.setRendered(true);
//                return;
//                final String[] path = parsedResults.getParsedValue().split(":");
//                final JsonObjectPath jsonObjectPath = JsonObjectPath.builder().path(path).data(renderRequest.getData()).build();
//                final ObjectExpressionResults objectExpressionResults = (ObjectExpressionResults) objectExpressionParser.evaluator().evaluate(jsonObjectPath);
//                final List<Object> results = objectExpressionResults.getListOfValues();
//                if (results == null || results.isEmpty()) {
//                    templateColumn.setRendered(true);
//                    return;
//                }
//                final EvaluatedResults evaluatedResults = evaluate(expression, evaluators, results);
//                if (evaluatedResults != null) {
//                    evaluatedResults.getCellRenderer()
//                            .render(renderRequest.getCell(), objectExpressionResults.getType(), templateColumn, evaluatedResults.getResults(), renderRequest.getData(), null, renderRequest.getDelayedRenders());
//                } else {
//                    new ObjectRenderer().render(renderRequest.getCell(), objectExpressionResults.getType(), templateColumn, results, renderRequest.getData(), null, renderRequest.getDelayedRenders());
//                }
//            }
//        } catch (Exception e) {
//            templateColumn.setRendered(true);
//            renderRequest.getCell().setCellValue(expression);
//            log.error(e.getMessage(), e);
//        }
//        return;
//    }
//
//    private EvaluatedResults evaluate(String expression, Stack<Evaluator> evaluators, List<Object> results) throws InvalidObjectExpressionException {
//        Object evaluatedValue;
//        while (!evaluators.isEmpty()) {
//            final Evaluator evaluator = evaluators.pop();
//            evaluatedValue = evaluator.evaluate(results);
//            if (evaluators.isEmpty() && evaluatedValue != null) {
//                final Object renderer = evaluator.renderer();
//                if (renderer instanceof CellRenderer) {
//                    return EvaluatedResults.builder().results(evaluatedValue).cellRenderer((CellRenderer) renderer).build();
//                }
//            }
//        }
//        return null;
//    }
//
//    private ParsedResults parse(String expression, Stack<Evaluator> evaluators) throws InvalidExpressionException {
//        String parsedValue = expressionParser.parse(expression);
//        Parser last = null;
//        for (Parser parser : registeredParsers) {
//            if (parser.isRegExMatch(parsedValue)) {
//                parsedValue = parser.parse(parsedValue);
//                if (parser.shouldRender(evaluators)) {
//                    return ParsedResults.builder().parsedValue(parsedValue)
//                            .parser(parser).build();
//                }
//                if (parser.hasEvaluation()) {
//                    evaluators.add(parser.evaluator());
//                }
//                last = parser;
//            }
//        }
//        return ParsedResults.builder().parsedValue(parsedValue)
//                .parser(last).build();
//    }

    private void defaultRender(RenderRequest renderRequest, String value) {
        renderRequest.getCell().setCellValue(value);
        renderRequest.getTemplateColumn().setRendered(true);
    }

}
