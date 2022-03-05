package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.expression.parser.RegExpression;
import com.dayosoft.excel.expression.parser.RowParser;
import com.dayosoft.excel.model.KeyDataMap;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRenderedLog;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.dayosoft.excel.util.CellUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ColRowRenderer extends CellRenderer<List<Object>> {

    private final RowParser rowParser;

    @Override
    public void render(Cell cell, TemplateColumn templateColumn, List<Object> keyList, String data, String key, TemplateRenderedLog templateRenderedLog) {
        if (keyList != null && !keyList.isEmpty()) {
            final Sheet sheet = cell.getSheet();
            final Workbook workbook = sheet.getWorkbook();
            templateRenderedLog.setRenderedStartRow(cell.getRowIndex());
            templateRenderedLog.setRenderedStartCol(cell.getColumnIndex());
            templateRenderedLog.setRenderedLastCol(cell.getColumnIndex());
            CellUtil.setCellValue(cell, keyList.get(0));
            int rowIndex = cell.getAddress().getRow() + 1;
            if (keyList.size() > 1) {
                final Row row = cell.getRow();
                for (int i = 1; i < keyList.size(); i++) {
                    Row newRow = sheet.getRow(rowIndex);
                    if (newRow == null) {
                        newRow = sheet.createRow(rowIndex);
                    }
                    for (int col = 0; col < row.getLastCellNum(); col++) {
                        final Cell rootCell = row.getCell(col);
                        if (rootCell == null) {
                            continue;
                        }
                        Cell newCell = newRow.getCell(col);
                        if (newCell == null) {
                            newCell = newRow.createCell(col);
                        }
                        if (rootCell.getCellStyle() != null) {
                            final CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.cloneStyleFrom(rootCell.getCellStyle());
                            newCell.setCellStyle(cellStyle);
                        }
                    }
                    CellUtil.setCellValue(newRow.getCell(cell.getAddress().getColumn()), keyList.get(i));
                    templateColumn.setRendered(true);
                    rowIndex++;
                }
                templateRenderedLog.setRenderedLastRow(rowIndex - 1);
            }
            final TemplateRow templateRow = templateColumn.getTemplateRow();
            final int col = templateColumn.getCol();
            final List<TemplateColumn> otherColumns = templateRow.getColumns().stream()
                    .filter(tcol -> !tcol.isRendered() && tcol.getCol() != col)
                    .collect(Collectors.toList());
            for (TemplateColumn tcol : otherColumns) {
                if(tcol.getValue() == null || tcol.isRendered()){
                    continue;
                }
                final String expression = tcol.getValue().toString();
                if (ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION)) {
                    int index = tcol.getTemplateRow().getRowNum();
                    for(Object value: keyList) {
                        final Row row = sheet.getRow(index);
                        renderCell(row, expression, value, data, key,tcol.getCol());
                        tcol.setRendered(true);
                        index++;
                    }
                }
            }
            TemplateHelper.shiftRowsDown(templateRow.getTemplateSheet().getRows(), templateColumn.getTemplateRow().getRowNum(), keyList.size()-1);
        }
    }

    private void renderCell(Row row, String expression, Object value, String jsonData, String jsonKey, int colPos) {
        if (ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION)) {
            try {
                final KeyDataMap keyDataMap = rowParser.parse(expression, jsonData, jsonKey, value);
                final Cell cell = row.createCell(colPos);
                if(keyDataMap != null) {
                    CellUtil.setCellValue(cell, keyDataMap.getValue());
                } else{
                    CellUtil.setCellValue(cell, expression);
                }
            } catch (InvalidExpressionException e) {
                e.printStackTrace();
            }
        }
    }

}