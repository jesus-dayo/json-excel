package com.dayosoft.excel.renderer.excel2007;

import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.renderer.CellRenderer;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Excel2007CellRenderer implements CellRenderer {

    private final ExpressionRenderingEngine expressionRenderingEngine;

    @Override
    public void render(RenderRequest cellRenderRequest) {
        final TemplateColumn templateColumn = cellRenderRequest.getTemplateColumn();
        if (ExpressionHelper.isValidExpression(templateColumn.getValue()) && !cellRenderRequest.isSkipExpression()) {
            expressionRenderingEngine.renderByExpression(cellRenderRequest);
        } else {
            CustomCellUtil.setCellValue(cellRenderRequest.getCell(), templateColumn.getValue());
            templateColumn.setRendered(true);
        }
    }

}
