package com.dayosoft.excel.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TemplateRenderedLog {

    private String expression;
    private TemplateRow templateRow;
    private TemplateColumn templateColumn;
    private Object value;
    private int renderedStartRow;
    private int renderedStartCol;
    private int renderedLastRow;
    private int renderedLastCol;

}
