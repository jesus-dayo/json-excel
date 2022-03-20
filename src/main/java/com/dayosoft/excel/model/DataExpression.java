package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class DataExpression {

    private String expression;
    private String data;
    private Map<String, Object> keyValue;

}
