package com.dayosoft.excel.model;

import com.jsoniter.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data
public class TemplateRow extends CommonTemplateProp {

    private Integer rowNum;
    private List<TemplateColumn> columns;
    @JsonIgnore
    private TemplateSheet templateSheet;

}
