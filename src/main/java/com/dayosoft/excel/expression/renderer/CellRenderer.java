package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.styles.StylesMapper;
import org.apache.poi.ss.usermodel.*;

import java.util.Map;

public interface CellRenderer {

    void render(RenderRequest renderRequest, MappedResults mappedResults) throws ExpressionException;

    default CellStyle applyTemplateCellStyle(RenderRequest renderRequest) {
        Workbook workbook = renderRequest.getWorkbook();
        CellStyle newCellStyle = workbook.createCellStyle();
        final Map<String, String> styles = renderRequest.getTemplateColumn().getStyles();
        if (!styles.isEmpty()) {
            final Font font = workbook.createFont();
            newCellStyle.setFont(font);
            StylesMapper.applyStyles(workbook, newCellStyle, styles);
        }
        return newCellStyle;
    }

    default Row getOrCreateRow(Sheet sheet, int rowIndex) {
        Row newRow = sheet.getRow(rowIndex);
        if (newRow == null) {
            newRow = sheet.createRow(rowIndex);
        }
        return newRow;
    }

    default Cell getOrCreateCell(Row row, int col) {
        Cell newCell = row.getCell(col);
        if (newCell == null) {
            newCell = row.createCell(col);
        }
        return newCell;
    }
}
