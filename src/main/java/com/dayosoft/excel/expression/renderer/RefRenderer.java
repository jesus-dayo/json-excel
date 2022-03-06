package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.model.TemplateSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RefRenderer extends CellRenderer<String> {

    @Override
    public void render(Cell cell, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        if (!value.contains(",")) {
            log.error("invalid use of ref, should be comma delimited row and column 0 index");
            return;
        }
        final String[] split = value.split(",");
        final Integer row = Integer.parseInt(split[0]);
        final Integer col = Integer.parseInt(split[1]);

        final TemplateRow templateRow = templateColumn.getTemplateRow();
        final TemplateSheet templateSheet = templateRow.getTemplateSheet();
        final Optional<TemplateRow> foundRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == row).findFirst();
        if(foundRow.isPresent()){
            final TemplateRow rowRef = foundRow.get();
            final Optional<TemplateColumn> foundColumn = rowRef.getColumns().stream().filter(c -> c.getOriginalCol() == col).findFirst();
            if(foundColumn.isPresent()){
                final TemplateColumn colRef = foundColumn.get();
                if(colRef.isRendered()){
                    String address = new CellReference(rowRef.getRowNum(),colRef.getCol()).formatAsString();
                    cell.setCellFormula(address);
                    templateColumn.setRendered(true);
                } else{
                    delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
                }
            }
        }
    }
}
