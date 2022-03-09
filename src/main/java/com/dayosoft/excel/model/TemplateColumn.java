package com.dayosoft.excel.model;

import com.jsoniter.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class TemplateColumn extends CommonTemplateProp {

    private Object value;
    private int col;
    private int originalCol;
    private int lastRowNum;
    private TemplateRange arrayFormulaRange;
    private String cellFormula;
    private String cellComment;
    private int columnWidth;
    @JsonIgnore
    private boolean isRendered;
    @JsonIgnore
    private TemplateRow templateRow;
}
