package com.dayosoft.excel.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public abstract class CommonTemplateProp {

    protected Map<String, String> styles;

}
