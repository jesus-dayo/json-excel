package com.dayosoft.excel.writer.excel2007;

import com.dayosoft.excel.formatter.Formatter;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateColumn;
import com.dayosoft.excel.writer.CellWriter;
import com.dayosoft.excel.writer.RowWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class ExcelRow2007Writer implements RowWriter {

    private final CellWriter cellWriter;
    private final Formatter excel2007RowFormatter;

    @Override
    public Row write(RenderRequest renderRequest) {
        Row row = renderRequest.getSheet().createRow(renderRequest.getTemplateRow().getRowNum());
        renderRequest.setRow(row);
        final List<TemplateColumn> templateColumns = renderRequest.getTemplateRow().getColumns();
        for (TemplateColumn templateColumn : templateColumns) {
            if (templateColumn.isRendered()) {
                continue;
            }
            renderRequest.setTemplateColumn(templateColumn);
            cellWriter.write(renderRequest);
        }
        excel2007RowFormatter.format(renderRequest);
        return row;
    }

}
