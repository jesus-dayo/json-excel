package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonObjectPath {

    private String[] path;
    private String data;

}
