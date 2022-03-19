package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.Value;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.type.ExcelJsonType;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ColArrRenderer extends CellRenderer<List<Object>> {

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, List<Object> value, String data, String key, List<DelayedRender> delayedRenders) {
        if (!value.isEmpty() && value.size() > 1) {
            final Sheet sheet = cell.getSheet();
            final Workbook workbook = sheet.getWorkbook();
            CellStyle newCellStyle = workbook.createCellStyle();
            final Map<String, String> styles = templateColumn.getStyles();
            if (!styles.isEmpty()) {
                final Font font = workbook.createFont();
                newCellStyle.setFont(font);
                StylesMapper.applyStyles(workbook, newCellStyle, styles);
            }
            int rowIndex = cell.getAddress().getRow();
            for (int i = 0; i < value.size(); i++) {
                Row newRow = sheet.getRow(rowIndex);
                if (newRow == null) {
                    newRow = sheet.createRow(rowIndex);
                }
                final int column = cell.getAddress().getColumn();
                Cell newCell = newRow.getCell(column);
                if (newCell == null) {
                    newCell = newRow.createCell(column);
                }
                final Object dataValue = value.get(i);
                ExcelJsonType.getByJsonType(type).getValueSetter().accept(Value.builder()
                        .value(dataValue)
                        .cell(newCell)
                        .type(type)
                        .build());
                if (newCellStyle != null) {
                    newCell.setCellStyle(newCellStyle);
                }
                rowIndex++;
            }
        }
        templateColumn.setRendered(true);
    }
}
