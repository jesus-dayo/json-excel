package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.expression.ExpressionHelper;
import com.dayosoft.excel.expression.RegExpression;
import com.dayosoft.excel.expression.mapper.ListMapper;
import com.dayosoft.excel.model.*;
import com.dayosoft.excel.styles.StylesMapper;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ColRowRenderer implements CellRenderer {

    private final ListMapper jsonListMapper;

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) {
        final List<String> results = mappedResults.getResults();
        if (results != null && !results.isEmpty()) {
            final Sheet sheet = renderRequest.getSheet();
            final Workbook workbook = sheet.getWorkbook();
            final Cell cell = renderRequest.getCell();
            CustomCellUtil.setCellValue(cell, results.get(0));
            int rowIndex = cell.getAddress().getRow() + 1;
            final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
            if (results.size() > 1) {
                final Row row = cell.getRow();
                for (int i = 1; i < results.size(); i++) {
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
                    mappedResults.getExcelJsonType().getValueSetter().accept(
                            Value.builder()
                                    .value(results.get(i))
                                    .cell(newRow.getCell(cell.getAddress().getColumn()))
                                    .build());
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
                final String expression = tcol.getValue() == null ? "" : tcol.getValue().toString();
                CellStyle newCellStyle = workbook.createCellStyle();
                final Map<String, String> styles = tcol.getStyles();
                if (!styles.isEmpty()) {
                    final Font font = workbook.createFont();
                    newCellStyle.setFont(font);
                    StylesMapper.applyStyles(workbook, newCellStyle, styles);
                }
                int index = tcol.getTemplateRow().getRowNum();
                for (Object value : results) {
                    final Row row = sheet.getRow(index);
                    boolean isRendered;
                    try {
                        isRendered = renderCell(row, expression, value, renderRequest.getData(), mappedResults.getKey(), tcol.getCol(), newCellStyle);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        tcol.setRendered(true);
                        continue;
                    }
                    tcol.setRendered(isRendered);
                    index++;
                }
                tcol.setLastRowNum(rowIndex - 1);
            }
            templateColumn.setLastRowNum(rowIndex - 1);
            TemplateHelper.shiftRowsDown(templateRow.getTemplateSheet().getRows(), templateColumn.getTemplateRow().getRowNum(), results.size() - 1);
            TemplateHelper.shiftMergedRegionsDown(templateRow.getTemplateSheet(), templateColumn.getTemplateRow().getRowNum(), results.size() - 1);
        }
    }

    private boolean renderCell(Row row, String expression, Object value, String jsonData, String jsonKey, int colPos, CellStyle cellStyle) {
        if (ExpressionHelper.isValidExpression(expression, RegExpression.ROW_FUNC_EXPRESSION)) {
            String rowExpression;
            try {
                rowExpression = ExpressionHelper.extractStringFromExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
                final String[] split = rowExpression.split("#");
                final String key = split[0];
                if (StringUtils.isNotEmpty(jsonKey)) {
                    if (!jsonKey.equals(key)) {
                        return false;
                    }
                }

                Map<String, Object> keyValueDependency = new HashMap<>();
                keyValueDependency.put(key, value);
                final MappedResults mappedResults = jsonListMapper.map(split[1], jsonData, keyValueDependency);
                Cell cell = row.getCell(colPos);
                if (cell == null) {
                    cell = row.createCell(colPos);
                }
                cell.setCellStyle(cellStyle);
                if (mappedResults != null) {
                    mappedResults.getExcelJsonType().getValueSetter().accept(Value.builder()
                            .value(mappedResults.getResults().get(0))
                            .cell(cell)
                            .build());
                } else {
                    CustomCellUtil.setCellValue(cell, expression);
                }
                return true;
            } catch (ExpressionException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            Cell cell = row.getCell(colPos);
            if (cell == null) {
                cell = row.createCell(colPos);
            }
            cell.setCellStyle(cellStyle);
            return false;
        }
        return false;
    }


}