package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.*;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellReference;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class RefRenderer extends NonDataRelatedRenderer {

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) {
        final String value = mappedResults.getResults().get(0);
        final Optional<TemplatePosition> positionOpt = CustomCellUtil.getPosition(value);
        if (positionOpt.isEmpty()) {
            log.error("invalid use of ref, should be comma delimited row and column 0 index");
            return;
        }
        TemplatePosition position = positionOpt.get();
        final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
        final TemplateRow templateRow = templateColumn.getTemplateRow();
        final TemplateSheet templateSheet = templateRow.getTemplateSheet();
        final Optional<TemplateRow> foundRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == position.getRow()).findFirst();
        if(foundRow.isPresent()){
            final TemplateRow rowRef = foundRow.get();
            final Optional<TemplateColumn> foundColumn = rowRef.getColumns().stream().filter(c -> c.getOriginalCol() == position.getCol()).findFirst();
            if(foundColumn.isPresent()){
                final TemplateColumn colRef = foundColumn.get();
                if(colRef.isRendered()){
                    String address = new CellReference(rowRef.getRowNum(),colRef.getCol()).formatAsString();
                    renderRequest.getCell().setCellFormula(address);
                    templateColumn.setRendered(true);
                } else {
                    renderRequest.getDelayedRenders().add(DelayedRender.builder()
                            .value(value)
                            .data(renderRequest.getData())
                            .templateColumn(templateColumn).build());
                }
            }
        }
    }
}
