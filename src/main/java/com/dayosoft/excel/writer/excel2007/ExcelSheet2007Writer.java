package com.dayosoft.excel.writer.excel2007;

import com.dayosoft.excel.formatter.Formatter;
import com.dayosoft.excel.model.DelayedRender;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateRow;
import com.dayosoft.excel.writer.CellWriter;
import com.dayosoft.excel.writer.RowWriter;
import com.dayosoft.excel.writer.SheetWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
class ExcelSheet2007Writer implements SheetWriter {

    private final RowWriter rowWriter;
    private final Formatter excel2007SheetFormatter;
    private final CellWriter excelCell2007Writer;

    @Override
    public Sheet write(RenderRequest renderRequest) {
        Sheet wbSheet = renderRequest.getWorkbook().createSheet(renderRequest.getTemplateSheet().getName());
        renderRequest.setSheet(wbSheet);
        final List<TemplateRow> templateRows = renderRequest.getTemplateSheet().getRows();
        for (TemplateRow templateRow : templateRows) {
            renderRequest.setTemplateRow(templateRow);
            rowWriter.write(renderRequest);
        }
        if (!renderRequest.getDelayedRenders().isEmpty() && !renderRequest.isSkipExpression()) {
            runDelayedRendering(renderRequest.getDelayedRenders(), wbSheet);
        }
        excel2007SheetFormatter.format(renderRequest);
        return wbSheet;
    }

    public void runDelayedRendering(List<DelayedRender> delayedRenders, Sheet sheet) {
        final List<DelayedRender> delayedRenderList = delayedRenders.stream().filter(delayedRender -> !delayedRender.getTemplateColumn().isRendered()).collect(Collectors.toList());
        final long count = delayedRenderList.size();
        if (count == 0) {
            return;
        }
        List<DelayedRender> newDelayedRenders = new ArrayList<>();
        for (DelayedRender delayedRender : delayedRenders) {
            final TemplateRow templateRow = delayedRender.getTemplateColumn().getTemplateRow();
            final Row row = sheet.getRow(templateRow.getRowNum());
            final Cell cell = row.getCell(delayedRender.getTemplateColumn().getCol());
            excelCell2007Writer.write(
                    RenderRequest.builder()
                            .delayedRenders(newDelayedRenders)
                            .data(delayedRender.getData())
                            .templateColumn(delayedRender.getTemplateColumn())
                            .sheet(sheet)
                            .row(row)
                            .cell(cell)
                            .build());
        }
        if (newDelayedRenders.size() == delayedRenders.size()) {
            log.error("Delayed rendering will end up in infinite loop , stopping it.");
            printDelayedRenders(delayedRenderList);
            return;
        }
        runDelayedRendering(newDelayedRenders, sheet);
    }

    private void printDelayedRenders(List<DelayedRender> delayedRenderList) {
        delayedRenderList.forEach(delayedRender -> log.error("delayedRendering col {} value {}",
                delayedRender.getTemplateColumn().getOriginalCol(),
                delayedRender.getTemplateColumn().getValue()));
    }
}
