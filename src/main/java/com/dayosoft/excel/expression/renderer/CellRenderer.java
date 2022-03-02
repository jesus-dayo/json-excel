package com.dayosoft.excel.expression.renderer;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

@Slf4j
public abstract class CellRenderer<T> extends Renderer{

    public abstract void render(Cell cell, T value);

}
