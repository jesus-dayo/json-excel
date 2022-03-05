package com.dayosoft.excel.model;

import com.jsoniter.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TemplateColumn extends CommonTemplateProp {

    private Object value;
    private int col;
    private TemplateRange arrayFormulaRange;
    private String cellFormula;
    private String cellComment;
    private int columnWidth;
    @JsonIgnore
    private boolean isRendered;
    @JsonIgnore
    private TemplateRow templateRow;
}
