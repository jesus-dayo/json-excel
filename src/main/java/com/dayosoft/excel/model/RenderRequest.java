package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class RenderRequest {

    private String data;
    private Cell cell;
    private Row row;
    private Sheet sheet;
    private Workbook workbook;
    private Template template;
    private TemplateRow templateRow;
    private TemplateColumn templateColumn;
    private TemplateSheet templateSheet;
    private boolean skipExpression;
    @Builder.Default
    private List<DelayedRender> delayedRenders = new ArrayList<>();

    public void delayRendering() {
        this.delayedRenders.add(DelayedRender.builder()
                .templateColumn(templateColumn)
                .data(data)
                .build());
    }

}
