package com.dayosoft.excel.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.renderer.evaluator.Evaluator;
import com.dayosoft.excel.renderer.evaluator.FirstEvaluator;
import com.dayosoft.excel.renderer.evaluator.ObjectEvaluator;
import com.dayosoft.excel.renderer.parser.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionRenderer {

    //parse
    //evaluate
    //render

    private final List<Parser> registeredParsers;

    public void render(TemplateSheet templateSheet,
                       String data,
                       String expression,
                       Cell cell) {
        List<Object> results = null;
        Stack<Evaluator> evaluators = new Stack<>();
        String parsedValue = expression;
        for (Parser parser : registeredParsers) {
            try {
                if (parser.isRegExMatch(parsedValue)) {
                    parsedValue = parser.parse(parsedValue);
                    if (parser instanceof ObjectExpressionParser) {
                        results = (List<Object>) ((ObjectExpressionParser) parser).evaluator()
                                .evaluate(JsonObjectPath.builder()
                                        .path(parsedValue.split(":")).data(data).build());
                        break;
                    }
                    if (parser instanceof ParserEvaluator) {
                        evaluators.add(((ParserEvaluator) parser).evaluator());
                    }
                }
            } catch (InvalidExpressionException e) {
                log.error(e.getMessage(), e);
            }
        }
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
