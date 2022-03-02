package com.dayosoft.excel.model;

import com.jsoniter.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TemplateColumn extends CommonTemplateProp {

    private Object value;
    private TemplatePosition position;
    private TemplateRange arrayFormulaRange;
    private String cellFormula;
    private String cellComment;
    private int columnWidth;
    @JsonIgnore
    private boolean isRendered;
}
