package com.dayosoft.excel.util;

import com.dayosoft.excel.exception.JsonMappingException;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import com.jsoniter.spi.JsonException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class JsonTraverser {

    protected Any json;

    protected final Any getJsonIfExist(Any any) {
        if (any.valueType() == ValueType.INVALID) {
            JsonException jsonException = (JsonException)any.get("exception").object();
            throw new JsonMappingException("Error finding property in the json request."+ jsonException.getMessage());
        }
        return any;
    }
}
