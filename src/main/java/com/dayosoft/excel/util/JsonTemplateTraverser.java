package com.dayosoft.excel.util;

import com.jsoniter.any.Any;

import java.util.Iterator;
import java.util.Map;

public class JsonTemplateTraverser extends JsonTraverser {

    public JsonTemplateTraverser(Any json) {
        super(json);
    }

    public final Iterator<Any> sheets() {
        return getJsonIfExist(json.get("sheets")).iterator();
    }

    public final String sheetName(Any sheet) {
        return getJsonIfExist(sheet.get("name")).toString();
    }

    public final Map<String, Any> globalSheetStyles(){
        return getJsonIfExist(json.get("global","sheet","styles")).asMap();
    }
}
