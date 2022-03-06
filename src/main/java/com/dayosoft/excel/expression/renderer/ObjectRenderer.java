package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.util.CellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ObjectRenderer extends CellRenderer<Object> {

    @Override
    public void render(Cell cell,String type, TemplateColumn templateColumn, Object value, String data,String key, List<DelayedRender> delayedRenders) {
        if(value instanceof List){
            List<Object> list = (List<Object>)value;
            if(!list.isEmpty()){
                if(list.size() == 1) {
                    final Object first = list.get(0);
                    log.debug("setting value "+first);
                    CellUtil.setCellValue(cell, first, type);
                } else {
                    final String commaDelimitedString = list.stream().map(o -> o.toString()).collect(Collectors.joining(","));
                    log.debug("setting value "+commaDelimitedString);
                    CellUtil.setCellValue(cell, commaDelimitedString);
                }

            }
        } else {
            log.error("given value is not a list");
            templateColumn.setRendered(true);
        }
    }
}
