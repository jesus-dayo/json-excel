package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.Value;
import com.dayosoft.excel.type.ExcelJsonType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FirstRenderer extends CellRenderer<Object>{

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, Object value, String data, String key, List<DelayedRender> delayedRenders) {
        log.debug("setting value " + value);
        ExcelJsonType.getByJsonType(type).getValueSetter().accept(Value.builder()
                .value(value)
                .cell(cell)
                .type(type)
                .build());
        templateColumn.setRendered(true);
    }

}
