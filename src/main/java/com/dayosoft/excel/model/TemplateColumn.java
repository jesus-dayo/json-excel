package com.dayosoft.excel.model;

import lombok.Data;

@Data
public class TemplateColumn extends CommonTemplateProp {

    private Object value;
    private TemplatePosition position;
    private TemplateRange arrayFormulaRange;
    private String cellFormula;
    private String cellComment;
    private int columnWidth;
}
