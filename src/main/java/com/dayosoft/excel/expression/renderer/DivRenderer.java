package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DivRenderer extends CellRenderer<String> {

    @Override
    public void render(Cell cell, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        if (!value.contains(",")) {
            log.error("invalid use of divide, should be comma delimited row and column 0 index");
            return;
        }
        final String[] split = value.replace(" ","").split(",");
        final Integer row1 = Integer.parseInt(split[0]);
        final Integer col1 = Integer.parseInt(split[1]);
        final Integer row2 = Integer.parseInt(split[2]);
        final Integer col2 = Integer.parseInt(split[3]);

        final TemplateRow templateRow = templateColumn.getTemplateRow();
        final TemplateSheet templateSheet = templateRow.getTemplateSheet();
        DivideAddressResult dividend = getDivideAddresses(row1, col1, templateSheet);
        if(dividend == null){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }

        DivideAddressResult divisor = getDivideAddresses(row2, col2, templateSheet);
        if(divisor == null){
            delayedRenders.add(DelayedRender.builder().value(value).data(data).templateColumn(templateColumn).build());
            return;
        }
        if(dividend.getLastRow() == null && dividend.getLastRow() == 0 && divisor.getLastRow() == null && divisor.getLastRow() == 0) {
            cell.setCellFormula(Divide.builder().dividend(dividend.getAddress()).divisor(divisor.getAddress()).build().getFormula());
            templateColumn.setRendered(true);
        } else {
            if(dividend.getLastRow() != null && dividend.getLastRow() > 0) {
                for (int i = cell.getAddress().getRow(); i < dividend.getLastRow();i++) {
                    final Row row = CellUtil.getRow(i, cell.getSheet());
                    String dividendAddress = com.dayosoft.excel.util.CellUtil
                            .getCellAddress(row.getRowNum(),col1);
                    final List<TemplateRow> templateRowList = templateSheet.getRows();
                    final TemplateRow foundDivisorRow
                            = templateRowList.stream().filter(t -> t.getOriginalRowNum() == row2).findFirst().get();
                    final TemplateColumn foundDivisorColumn = foundDivisorRow.getColumns().stream().filter(t -> t.getOriginalCol() == col2).findFirst().get();
                    String divisorAddress = com.dayosoft.excel.util.CellUtil
                            .getCellAddress(foundDivisorRow.getRowNum(),foundDivisorColumn.getCol());
                    Cell resultCell = row.getCell(templateColumn.getCol());
                    resultCell.setCellFormula(dividendAddress+"/"+divisorAddress);
                }
            } else {

            }
        }
    }

    private DivideAddressResult getDivideAddresses(Integer row,
                                      Integer col,
                                      TemplateSheet templateSheet) {
        final Optional<TemplateRow> foundRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == row).findFirst();
        if(foundRow.isPresent()){
            final TemplateRow rowRef = foundRow.get();
            final Optional<TemplateColumn> foundColumn = rowRef.getColumns().stream().filter(c -> c.getOriginalCol() == col).findFirst();
            if(foundColumn.isPresent()){
                final TemplateColumn colRef = foundColumn.get();
                if(colRef.isRendered()){
                    String address = new CellReference(rowRef.getRowNum(),colRef.getCol()).formatAsString();
                    return DivideAddressResult.builder()
                            .lastRow(colRef.getLastRowNum())
                            .address(address)
                            .build();
                }
            }
        }
        return null;
    }
}
