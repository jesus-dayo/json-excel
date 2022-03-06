package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TotalColRenderer extends CellRenderer<String>{

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        if (!value.contains(",")) {
            log.error("invalid use of totalCol, should be comma delimited row and column 0 index");
            return;
        }
        final String[] split = value.replace(" ","").split(",");
        final Integer row = Integer.parseInt(split[0]);
        final Integer col = Integer.parseInt(split[1]);

        final TemplateSheet templateSheet = templateColumn.getTemplateRow().getTemplateSheet();
        final TemplateRow refTemplateRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == row).findFirst().get();
        final TemplateColumn refTemplateColumn = refTemplateRow.getColumns().stream().filter(c -> c.getOriginalCol() == col).findFirst().get();

        if(!refTemplateColumn.isRendered()){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }

        // SUM(J9:J15)
        final String addendFrom = CustomCellUtil.getCellAddress(refTemplateRow.getRowNum(), refTemplateColumn.getCol());
        final String addendTo = CustomCellUtil.getCellAddress(refTemplateColumn.getLastRowNum(), refTemplateColumn.getCol());
        cell.setCellFormula("SUM("+addendFrom+":"+addendTo+")");
        templateColumn.setRendered(true);
    }
}
