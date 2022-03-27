package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RegExpressionRenderer implements CellRenderer {

    @Override
    public MappedResults render(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException {
        final List<String> results = mappedResults.getResults();
        if (results == null || results.isEmpty()) {
            renderRequest.getTemplateColumn().setRendered(true);
            return mappedResults;
        }
        final String[] parameters = mappedResults.getParameters();
        if (parameters == null) {
            log.error("parameters are required for regExp function");
            renderRequest.getTemplateColumn().setRendered(true);
            return mappedResults;
        }

        if (mappedResults.getParserChain() != null && !mappedResults.getParserChain().isEmpty()) {
            try {
                final List<String> newResults = new ArrayList<>();
                for (String result : results) {
                    String expression = ExpressionHelper.extractStringFromExpression(result, parameters[0]);
                    newResults.add(expression);
                }
                mappedResults.setResults(newResults);
                return mappedResults;
            } catch (InvalidExpressionException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            final String concatStr = results.stream().collect(Collectors.joining(","));
            renderRequest.getCell().setCellValue(ExpressionHelper.extractStringFromExpression(concatStr, parameters[0]));
            renderRequest.getTemplateColumn().setRendered(true);
        }

        return mappedResults;
    }

}
