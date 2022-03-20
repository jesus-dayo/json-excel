package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapRenderer implements CellRenderer {

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException {
        final List<String> results = mappedResults.getResults();
        if (results == null || results.isEmpty()) {
            renderRequest.getTemplateColumn().setRendered(true);
            return;
        }
        renderRequest.getCell().setCellValue(results.stream().collect(Collectors.joining(",")));
        renderRequest.getTemplateColumn().setRendered(true);
    }
}
