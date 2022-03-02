package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.TemplateSheet;
import org.apache.poi.ss.usermodel.Cell;

public abstract class CellFormulaRenderer {

    public abstract void render(TemplateSheet templateSheet, Cell cell, Object value);

}
