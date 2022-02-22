package com.dayosoft.excel.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Sheet extends CommonTemplateProp {

    private Integer index;
    private String name;

}
