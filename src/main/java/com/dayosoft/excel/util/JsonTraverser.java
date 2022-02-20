package com.dayosoft.excel.util;

import com.jsoniter.any.Any;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonTraverser {

    private Any json;

    public final Any body() {
        return json.get("body");
    }

    public final Any global() {
        return json.get("template", "global");
    }

    public final Any globalStyles() {
        return json.get("template", "global", "styles");
    }

    public final Any globalFieldLabelName(String field) {
        return json.get("template", "global", field, "label", "name");
    }

    public final Any globalFieldLabelPosition(String field) {
        return json.get("template", "global", field, "label", "position");
    }

    public final Any globalFieldValues(String field) {
        return json.get("template", "global", field, "value");
    }

    public final Any name(Any value) {
        return value.get( "name");
    }

    public final Any position(Any value) {
        return json.get("position");
    }

    public final Integer column(Any position) {
        return json.get("col").toInt();
    }

    public final Integer row(Any position) {
        return json.get("row").toInt();
    }
}
