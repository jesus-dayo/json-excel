package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class JsonObjectPath {

    private String[] path;
    private Map<String,Object> keyValue;
    private String data;

}
