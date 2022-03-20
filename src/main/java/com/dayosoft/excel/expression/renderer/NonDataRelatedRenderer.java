package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateColumn;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class NonDataRelatedRenderer implements CellRenderer {

    protected void validateResults(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException {
        final List<String> results = mappedResults.getResults();
        final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
        if (results.isEmpty()) {
            log.error("Invalid expression for " + templateColumn.getValue().toString());
            templateColumn.setRendered(true);
            throw new InvalidExpressionException("Invalid expression for " + templateColumn.getValue().toString());
        }
        render(renderRequest, mappedResults);
    }

}
