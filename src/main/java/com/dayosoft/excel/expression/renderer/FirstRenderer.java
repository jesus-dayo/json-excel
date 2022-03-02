package com.dayosoft.excel.expression.renderer;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstRenderer extends CellRenderer<Object>{

    @Override
    public void render(Cell cell, Object value) {
        log.debug("setting value "+value);
        setValue(cell, value);
    }

}
