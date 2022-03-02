package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.expression.parser.*;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRenderedLog;
import com.dayosoft.excel.model.TemplateRow;
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

    private final List<ParserEvaluator> registeredParsers;
    private final ExpressionParser expressionParser;

    public void renderCellByExpression(TemplateRenderedLog templateRenderedLog,
                                       String data,
                                       String expression,
                                       Cell cell) {
        if (!expressionParser.isRegExMatch(expression)) {
            cell.setCellValue(expression);
            return;
        }
        try {
            Stack<Evaluator> evaluators = new Stack<>();
            List<Object> results = getDataList(templateRenderedLog, data, expression, cell, evaluators);
            evaluateAndRender(templateRenderedLog, expression, cell, evaluators, results);
        } catch (InvalidExpressionException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void renderRowByExpression(List<TemplateRenderedLog> templateRenderedLogs,
                                      String data,
                                      Sheet sheet,
                                      List<Object> keyData,
                                      String key,
                                      TemplateRow templateRow,
                                      int rowIndex) throws InvalidExpressionException {
        final List<TemplateColumn> columns = templateRow.getColumns();
        final List<TemplateColumn> filteredColumn = columns.stream()
                .filter(templateColumn -> ExpressionHelper.isValidExpressions(templateColumn.getValue().toString(), RegExpression.EXPRESSION) && ExpressionHelper.isValidExpressions(templateColumn.getValue().toString(), RegExpression.ROW_FUNC_EXPRESSION))
                .collect(Collectors.toList());
        for(Object value: keyData) {
            final Row row = sheet.createRow(rowIndex);
            for (int i =0; i < filteredColumn.size() ; i++) {
                TemplateColumn templateColumn = filteredColumn.get(i);
                TemplateRenderedLog templateRenderedLog = new TemplateRenderedLog();
                templateRenderedLog.setExpression(templateColumn.getValue().toString());
                templateRenderedLog.setTemplateColumn(templateColumn);
                templateRenderedLog.setTemplateRow(templateRow);
                final Cell cell = row.createCell(templateColumn.getPosition().getCol());
                if(i == 0) {
                    CellUtil.setCellValue(cell, value);
                } else {
                    Stack<Evaluator> evaluators = new Stack<>();
//                    Any results = getDataList(templateRenderedLog, data, templateColumn.getValue().toString(), cell, evaluators);
//                    results.stream().filter(r->r.)
                }
            }
            rowIndex++;
        }

        //        if (expressionParser.isRegExMatch(expression)) {
//            cell.setCellValue(expression);
//            return;
//        }
//        try {
//            Stack<Evaluator> evaluators = new Stack<>();
//            List<Object> results = getDataList(templateRenderedLog, data, expression, cell, evaluators);
//            evaluateAndRender(templateRenderedLog, expression, cell, evaluators, results);
//        } catch (InvalidExpressionException e) {
//            log.error(e.getMessage(), e);
//        }
    }

    private void evaluateAndRender(TemplateRenderedLog templateRenderedLog, String expression, Cell cell, Stack<Evaluator> evaluators, List<Object> results) {
        Object evaluatedValue;
        while (!evaluators.isEmpty()) {
            final Evaluator evaluator = evaluators.pop();
            evaluatedValue = evaluator.evaluate(results);
            if (evaluators.isEmpty() && evaluatedValue != null) {
                final Object renderer = evaluator.renderer();
                if (renderer instanceof CellRenderer) {
                    log.info("Rendering cell " + expression + " with renderer " + renderer.getClass().getTypeName());
                    CellRenderer cellRenderer = (CellRenderer) renderer;
                    cellRenderer.render(cell, evaluatedValue, templateRenderedLog);
                    return;
                }
            }
        }
    }

    private List<Object> getDataList(TemplateRenderedLog templateRenderedLog, String data, String expression, Cell cell, Stack<Evaluator> evaluators) throws InvalidExpressionException {
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
                         renderer.render(cell, results, templateRenderedLog);
                    }
                    break;
                }
                if (parser instanceof ParserEvaluator) {
                    evaluators.add(((ParserEvaluator) parser).evaluator());
                }
            }
        }
        return results;
    }

}
