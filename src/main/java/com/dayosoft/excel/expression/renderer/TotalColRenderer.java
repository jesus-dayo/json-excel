package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.*;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TotalColRenderer extends CellRenderer<String>{

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        final Optional<TemplatePosition> positionOptional = CustomCellUtil.getPosition(value);
        if (positionOptional.isEmpty()) {
            log.error("invalid use of totalCol, should be comma delimited row and column 0 index");
            return;
        }
        final TemplatePosition position = positionOptional.get();
        final TemplateSheet templateSheet = templateColumn.getTemplateRow().getTemplateSheet();
        final TemplateRow refTemplateRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == position.getRow()).findFirst().get();
        final TemplateColumn refTemplateColumn = refTemplateRow.getColumns().stream().filter(c -> c.getOriginalCol() == position.getCol()).findFirst().get();

        if(!refTemplateColumn.isRendered()){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }

        final String addendFrom = CustomCellUtil.getCellAddress(refTemplateRow.getRowNum(), refTemplateColumn.getCol());
        final String addendTo = CustomCellUtil.getCellAddress(refTemplateColumn.getLastRowNum(), refTemplateColumn.getCol());
        cell.setCellFormula("SUM("+addendFrom+":"+addendTo+")");
        templateColumn.setRendered(true);
    }
}
