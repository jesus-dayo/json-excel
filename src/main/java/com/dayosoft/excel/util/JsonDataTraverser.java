package com.dayosoft.excel.util;

import com.dayosoft.excel.exception.JsonMappingException;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import com.jsoniter.spi.JsonException;
import lombok.AllArgsConstructor;

import java.util.Set;

public class JsonDataTraverser extends JsonTraverser {

    public JsonDataTraverser(Any data) {
        super(data);
    }

    public final Any body() {
        return getJsonIfExist(json.get("body"));
    }

    public final Set<String> bodyKeys() {
        return body().keys();
    }

    public final Any columns(String key, Any obj) {
        return getJsonIfExist(obj.get(key, "columns"));
    }

    public final Any rows(String key, Any obj) {
        return getJsonIfExist(obj.get(key, "rows"));
    }

    public final Any global() {
        return getJsonIfExist(json.get("template", "global"));
    }

    public final Any globalStyles() {
        return getJsonIfExist(json.get("template", "global", "styles"));
    }

    public final Any globalFieldLabelName(String field) {
        return getJsonIfExist(json.get("template", "global", field, "label", "name"));
    }

    public final Any globalFieldLabelPosition(String field) {
        return getJsonIfExist(json.get("template", "global", field, "label", "position"));
    }

    public final Any globalFieldValues(String field) {
        return getJsonIfExist(json.get("template", "global", field, "value"));
    }

    public final Any name(Any value) {
        return getJsonIfExist(value.get("name"));
    }

    public final Any position(Any value) {
        return getJsonIfExist(json.get("position"));
    }

    public final Integer column(Any position) {
        return getJsonIfExist(json.get("col")).toInt();
    }

    public final String field(Any obj){
        return getJsonIfExist(obj.get("field")).toString();
    }

    public final String type(Any obj){
        return getJsonIfExist(obj.get("type")).toString();
    }

    public final Integer row(Any position) {
        return getJsonIfExist(json.get("row")).toInt();
    }

}
