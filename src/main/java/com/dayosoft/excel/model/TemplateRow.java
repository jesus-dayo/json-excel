package com.dayosoft.excel.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TemplateRow extends CommonTemplateProp {

    private Integer rowNum;
    private List<TemplateColumn> columns;

}
