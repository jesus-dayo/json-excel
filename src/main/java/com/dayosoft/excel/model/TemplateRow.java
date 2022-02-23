package com.dayosoft.excel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TemplateRow extends CommonTemplateProp {

    private Integer rowNum;
    private List<TemplateColumn> columns;

}
