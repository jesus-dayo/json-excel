package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TotalRenderer extends CellRenderer<Object>{

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, Object value, String data, String key, List<DelayedRender> delayedRenders) {
        log.debug("setting value "+value);
        CustomCellUtil.setCellValue(cell, value, type);
        templateColumn.setRendered(true);
    }

}
