package com.dayosoft.excel.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Field<T> extends CommonTemplateProp {

        private Integer sheet;
        private T value;
        private String type;
        private Position position;

}
