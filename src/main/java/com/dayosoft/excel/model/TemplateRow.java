package com.dayosoft.excel.model;

import com.jsoniter.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class TemplateRow extends CommonTemplateProp {

    private Integer rowNum;
    private Integer originalRowNum;
    private List<TemplateColumn> columns;
    @JsonIgnore
    private TemplateSheet templateSheet;

    public void shiftDown(int shiftCount){
        setRowNum(rowNum+shiftCount);
    }

}
