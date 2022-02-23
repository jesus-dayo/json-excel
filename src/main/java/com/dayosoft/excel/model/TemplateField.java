package com.dayosoft.excel.model;

import lombok.Getter;

@Getter
public class TemplateField<T> extends CommonTemplateProp {

        private T value;
        private String type;
        private TemplatePosition position;

}
