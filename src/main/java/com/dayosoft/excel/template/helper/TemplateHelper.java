package com.dayosoft.excel.template.helper;

import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.model.TemplateSheet;

import java.util.List;

public class TemplateHelper {

    public static void fillDependencies(List<TemplateSheet> templateSheets){
        for(TemplateSheet templateSheet: templateSheets){
            final List<TemplateRow> rows = templateSheet.getRows();
            for(TemplateRow row: rows){
                row.setTemplateSheet(templateSheet);
                final List<TemplateColumn> columns = row.getColumns();
                for(TemplateColumn column: columns){
                    column.setTemplateRow(row);
                }
            }
        }
    }

    public static void shiftRowsDown(List<TemplateRow> templateRowList, int greaterThan, int count){
        templateRowList.stream().filter(templateRow -> templateRow.getRowNum() > greaterThan)
                .forEach(templateRow -> {
                    templateRow.shiftDown(count);
                });
    }

}
