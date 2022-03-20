package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SumRenderer extends NonDataRelatedRenderer {

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) throws InvalidExpressionException {
        final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
        final String value = mappedResults.getResults().get(0);
        final Optional<TemplateRange> positionRangeOptional = CustomCellUtil.getPositionRange(value);
        if (positionRangeOptional.isEmpty()) {
            log.error("invalid use of sum, should be comma delimited range");
            return;
        }
        final TemplateRange templateRange = positionRangeOptional.get();
        final TemplateRow templateRow = templateColumn.getTemplateRow();
        final TemplateSheet templateSheet = templateRow.getTemplateSheet();
        AddressResult addend1 = CustomCellUtil.getAddressResults(templateRange.getStart().getRow(),
                templateRange.getStart().getCol(), templateSheet);
        if(addend1 == null) {
            renderRequest.delayRendering();
            return;
        }

        AddressResult addend2 = CustomCellUtil.getAddressResults(templateRange.getEnd().getRow(),
                templateRange.getEnd().getCol(), templateSheet);
        if(addend2 == null) {
            renderRequest.delayRendering();
            return;
        }
        final Cell cell = renderRequest.getCell();
        if((addend1.getLastRow() == null || addend1.getLastRow() == 0) && (addend2.getLastRow() == null || addend2.getLastRow() == 0)) {
            cell.setCellFormula(Sum.builder().addend1(addend1.getAddress()).addend2(addend2.getAddress()).build().getFormula());
            templateColumn.setRendered(true);
        } else {
            if(addend1.getLastRow() != null && addend1.getLastRow() > 0) {
                for (int i = cell.getAddress().getRow(); i < addend1.getLastRow() + 1; i++) {
                    final Row row = CellUtil.getRow(i, cell.getSheet());
                    String addendAddress1 = CustomCellUtil
                            .getCellAddress(row.getRowNum(), templateRange.getStart().getCol());
                    final List<TemplateRow> templateRowList = templateSheet.getRows();
                    final TemplateRow foundAddendRow
                            = templateRowList.stream().filter(t -> t.getOriginalRowNum() == templateRange.getStart().getRow()).findFirst().get();
                    final TemplateColumn foundAddendColumn = foundAddendRow.getColumns().stream().filter(t -> t.getOriginalCol() == templateRange.getEnd().getCol()).findFirst().get();
                    String addendAddress2 = CustomCellUtil
                            .getCellAddress(foundAddendRow.getRowNum(), foundAddendColumn.getCol());
                    Cell resultCell = row.getCell(templateColumn.getCol());
                    resultCell.setCellFormula(addendAddress1+"+"+addendAddress2);
                    templateColumn.setRendered(true);
                }
            } else {
                templateColumn.setRendered(true);
            }
        }
    }
}
