package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjectMapRenderer implements CellRenderer {

    @Override
    public MappedResults render(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException {
        final List<String> results = mappedResults.getResults();
        if (results == null || results.isEmpty()) {
            renderRequest.getTemplateColumn().setRendered(true);
            return mappedResults;
        }
        renderRequest.getCell().setCellValue(String.join(",", results));
        renderRequest.getTemplateColumn().setRendered(true);
        return mappedResults;
    }
}
