package com.dayosoft.excel.expression.renderer;

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
public class DivRenderer extends CellRenderer<String> {

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        final Optional<TemplateRange> positionRangeOptional = CustomCellUtil.getPositionRange(value);
        if (positionRangeOptional.isEmpty()) {
            log.error("invalid use of divide, should be comma delimited range");
            return;
        }
        final TemplateRange templateRange = positionRangeOptional.get();
        final TemplateRow templateRow = templateColumn.getTemplateRow();
        final TemplateSheet templateSheet = templateRow.getTemplateSheet();
        AddressResult dividend = CustomCellUtil.getAddressResults(templateRange.getStart().getRow(),
                templateRange.getStart().getCol(), templateSheet);
        if(dividend == null){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }

        AddressResult divisor = CustomCellUtil.getAddressResults(templateRange.getEnd().getRow(),
                templateRange.getEnd().getCol(), templateSheet);
        if(divisor == null){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }
        if(dividend.getLastRow() == null && dividend.getLastRow() == 0 && divisor.getLastRow() == null && divisor.getLastRow() == 0) {
            cell.setCellFormula(Divide.builder().dividend(dividend.getAddress()).divisor(divisor.getAddress()).build().getFormula());
            templateColumn.setRendered(true);
        } else {
            if(dividend.getLastRow() != null && dividend.getLastRow() > 0) {
                for (int i = cell.getAddress().getRow(); i < dividend.getLastRow()+1;i++) {
                    final Row row = CellUtil.getRow(i, cell.getSheet());
                    String dividendAddress = CustomCellUtil
                            .getCellAddress(row.getRowNum(),templateRange.getStart().getCol());
                    final List<TemplateRow> templateRowList = templateSheet.getRows();
                    final TemplateRow foundDivisorRow
                            = templateRowList.stream().filter(t -> t.getOriginalRowNum() == templateRange.getEnd().getRow()).findFirst().get();
                    final TemplateColumn foundDivisorColumn = foundDivisorRow.getColumns().stream().filter(t -> t.getOriginalCol() == templateRange.getEnd().getCol()).findFirst().get();
                    String divisorAddress = CustomCellUtil
                            .getCellAddress(foundDivisorRow.getRowNum(),foundDivisorColumn.getCol());
                    Cell resultCell = row.getCell(templateColumn.getCol());
                    resultCell.setCellFormula(dividendAddress+"/"+divisorAddress);
                    templateColumn.setRendered(true);
                }
            } else {
                templateColumn.setRendered(true);
            }
        }
    }
}
