package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;

public interface CellRenderer {

    void render(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException;
}
