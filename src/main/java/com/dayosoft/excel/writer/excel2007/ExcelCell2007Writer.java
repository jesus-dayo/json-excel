package com.dayosoft.excel.writer.excel2007;

import com.dayosoft.excel.formatter.Formatter;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.renderer.CellRenderer;
import com.dayosoft.excel.writer.CellWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExcelCell2007Writer implements CellWriter {

    private final Formatter excel2007CellFormatter;
    private final CellRenderer excel2007CellRenderer;

    @Override
    public Cell write(RenderRequest renderRequest) {
        final Row row = renderRequest.getRow();
        final Cell cell = row.createCell(renderRequest.getTemplateColumn().getCol());
        renderRequest.setCell(cell);
        excel2007CellFormatter.format(renderRequest);
        excel2007CellRenderer.render(renderRequest);
        return renderRequest.getCell();
    }

}
