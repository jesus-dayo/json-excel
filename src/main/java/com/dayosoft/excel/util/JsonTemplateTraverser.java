package com.dayosoft.excel.util;

import com.dayosoft.excel.model.Position;
import com.jsoniter.ValueType;
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

    public final Any globalStaticData(){
        return getJsonIfExist(json.get("global","static"));
    }

    public final int sheetIndex(Any obj){
        return getJsonIfExist(obj.get("sheet")).toInt();
    }

    public final Any value(Any obj){
        return getJsonIfExist(obj.get("value"));
    }

    public final Map<String, Any> styles(Any obj){
        return getJsonIfExist(obj.get("styles")).asMap();
    }

    public final Any type(Any obj){
        return getJsonIfExist(obj.get("type"));
    }

    public final Position position(Any obj){
        final Any positionJson = getJsonIfExist(obj.get("position"));
        return Position.builder().col(positionJson.get("col").toInt()).row(positionJson.get("row").toInt()).build();
    }

    public final boolean isPositionMerger(Any obj){
        final Any merge = obj.get("position", "merge");
        if(merge.valueType() != ValueType.INVALID){
            return true;
        }
        return false;
    }

    public final Position positionStart(Any obj){
        return position(getJsonIfExist(obj.get("position", "merge","start")));
    }

    public final Position positionEnd(Any obj){
        return position(getJsonIfExist(obj.get("position", "merge","end")));
    }

    public final Any positionCol(Any obj){
        return getJsonIfExist(obj.get("position", "col"));
    }

    public final Any positionRow(Any obj){
        return getJsonIfExist(obj.get("position", "row"));
    }
}
