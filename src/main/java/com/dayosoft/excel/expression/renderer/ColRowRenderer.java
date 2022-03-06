package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.parser.ExpressionHelper;
import com.dayosoft.excel.expression.parser.RegExpression;
import com.dayosoft.excel.expression.parser.RowParser;
import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.KeyDataMap;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.dayosoft.excel.util.CellUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ColRowRenderer extends CellRenderer<List<Object>> {

    private final RowParser rowParser;

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, List<Object> keyList, String data, String key, List<DelayedRender> delayedRenders) {
        if (keyList != null && !keyList.isEmpty()) {
            final Sheet sheet = cell.getSheet();
            final XSSFWorkbook workbook = (XSSFWorkbook) sheet.getWorkbook();
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
                    CellUtil.setCellValue(newRow.getCell(cell.getAddress().getColumn()), keyList.get(i), type);
                    templateColumn.setRendered(true);
                    rowIndex++;
                }
            }
            final TemplateRow templateRow = templateColumn.getTemplateRow();
            final int col = templateColumn.getCol();
            final List<TemplateColumn> otherColumns = templateRow.getColumns().stream()
                    .filter(tcol -> !tcol.isRendered() && tcol.getCol() != col)
                    .collect(Collectors.toList());
            for (TemplateColumn tcol : otherColumns) {
                if (tcol.isRendered()) {
                    continue;
                }
                final String expression = tcol.getValue() == null? "": tcol.getValue().toString();
                XSSFCellStyle newCellStyle = workbook.createCellStyle();
                final Map<String, String> styles = tcol.getStyles();
                if (!styles.isEmpty()) {
                    final XSSFFont font = workbook.createFont();
                    newCellStyle.setFont(font);
                    StylesMapper.applyStyles(newCellStyle, styles);
                }
                int index = tcol.getTemplateRow().getRowNum();
                for (Object value : keyList) {
                    final Row row = sheet.getRow(index);
                    boolean isRendered = renderCell(row, expression, value, data, key, tcol.getCol(), newCellStyle, delayedRenders);
                    tcol.setRendered(isRendered);
                    index++;
                }
                tcol.setLastRowNum(rowIndex-1);
            }
            templateColumn.setLastRowNum(rowIndex-1);
            TemplateHelper.shiftRowsDown(templateRow.getTemplateSheet().getRows(), templateColumn.getTemplateRow().getRowNum(), keyList.size() - 1);
        }
    }

    private boolean renderCell(Row row, String expression, Object value, String jsonData, String jsonKey, int colPos, XSSFCellStyle xssfCellStyle, List<DelayedRender> delayedRenders) {
        if (ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION)) {
            try {
                final KeyDataMap keyDataMap = rowParser.parse(expression, jsonData, jsonKey, value);
                Cell cell = row.getCell(colPos);
                if (cell == null) {
                    cell = row.createCell(colPos);
                }
                cell.setCellStyle(xssfCellStyle);
                if (keyDataMap != null) {
                    CellUtil.setCellValue(cell, keyDataMap.getValue(),keyDataMap.getType());
                } else {
                    CellUtil.setCellValue(cell, expression);
                }
                return true;
            } catch (InvalidExpressionException e) {
                e.printStackTrace();
                return true;
            }
        } else {
            Cell cell = row.getCell(colPos);
            if (cell == null) {
                cell = row.createCell(colPos);
            }
            cell.setCellStyle(xssfCellStyle);
            return false;
        }
    }


}