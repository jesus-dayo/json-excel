package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColArrRenderer implements CellRenderer {

    @Override
    public MappedResults render(RenderRequest renderRequest, MappedResults mappedResults) {
        final List<String> results = mappedResults.getResults();
        if (!results.isEmpty() && results.size() > 1) {
            renderRows(renderRequest, mappedResults, results);
        }
        renderRequest.getTemplateColumn().setRendered(true);
        return mappedResults;
    }

    private void renderRows(RenderRequest renderRequest, MappedResults mappedResults, List<String> results) {
        final Cell cell = renderRequest.getCell();
        final Sheet sheet = cell.getSheet();
        CellStyle newCellStyle = applyTemplateCellStyle(renderRequest);
        int rowIndex = cell.getAddress().getRow();
        for (String result : results) {
            Row newRow = getOrCreateRow(sheet, rowIndex);
            final int column = cell.getAddress().getColumn();
            Cell newCell = newRow.getCell(column);
            if (newCell == null) {
                newCell = newRow.createCell(column);
            }
            mappedResults.getExcelJsonType()
                    .getValueSetter()
                    .accept(Value.builder()
                            .value(result)
                            .cell(newCell)
                            .build());
            if (newCellStyle != null) {
                newCell.setCellStyle(newCellStyle);
            }
            rowIndex++;
        }
    }


}
