package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRenderedLog;
import com.dayosoft.excel.util.CellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstRenderer extends CellRenderer<Object>{

    @Override
    public void render(Cell cell, TemplateColumn templateColumn, Object value, String data,String key,TemplateRenderedLog templateRenderedLog) {
        log.debug("setting value "+value);
        CellUtil.setCellValue(cell, value);
        templateRenderedLog.setRenderedLastCol(cell.getColumnIndex());
        templateRenderedLog.setRenderedStartCol(cell.getColumnIndex());
        templateRenderedLog.setRenderedStartRow(cell.getRowIndex());
        templateRenderedLog.setRenderedLastRow(cell.getRowIndex());
    }

}
