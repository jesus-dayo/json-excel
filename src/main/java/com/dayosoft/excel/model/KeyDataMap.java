package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KeyDataMap {

    private String key;
    private Object value;
    private String type;
}
