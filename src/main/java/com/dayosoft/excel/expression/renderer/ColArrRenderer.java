package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.Value;
import com.dayosoft.excel.styles.StylesMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ColArrRenderer implements CellRenderer {

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) {
        final List<String> results = mappedResults.getResults();
        if (!results.isEmpty() && results.size() > 1) {
            final Cell cell = renderRequest.getCell();
            final Sheet sheet = cell.getSheet();
            final Workbook workbook = sheet.getWorkbook();
            CellStyle newCellStyle = workbook.createCellStyle();
            final Map<String, String> styles = renderRequest.getTemplateColumn().getStyles();
            if (!styles.isEmpty()) {
                final Font font = workbook.createFont();
                newCellStyle.setFont(font);
                StylesMapper.applyStyles(workbook, newCellStyle, styles);
            }
            int rowIndex = cell.getAddress().getRow();
            for (int i = 0; i < results.size(); i++) {
                Row newRow = sheet.getRow(rowIndex);
                if (newRow == null) {
                    newRow = sheet.createRow(rowIndex);
                }
                final int column = cell.getAddress().getColumn();
                Cell newCell = newRow.getCell(column);
                if (newCell == null) {
                    newCell = newRow.createCell(column);
                }
                final Object dataValue = results.get(i);
                mappedResults.getExcelJsonType().getValueSetter().accept(Value.builder()
                        .value(dataValue)
                        .cell(newCell)
                        .build());
                if (newCellStyle != null) {
                    newCell.setCellStyle(newCellStyle);
                }
                rowIndex++;
            }
        }
        renderRequest.getTemplateColumn().setRendered(true);
    }
}
