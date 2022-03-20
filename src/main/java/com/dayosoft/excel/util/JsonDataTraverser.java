package com.dayosoft.excel.util;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.type.ExcelJsonType;
import com.jsoniter.any.Any;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
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

    public final ExcelJsonType typeByField(String groupName, String field) throws InvalidObjectExpressionException {
        final Any columns = getJsonIfExist(json.get("body", groupName, "columns"));
        final Iterator<Any> columnsIterator = columns.iterator();
        while(columnsIterator.hasNext()){
            final Any column = columnsIterator.next();
            final String nextField = column.get("field").toString();
            if (field.equalsIgnoreCase(nextField)) {
                return ExcelJsonType.getByJsonType(column.get("type").toString());
            }
        }

        throw new InvalidObjectExpressionException("group name " + groupName + " field " + field + " is invalid.");
    }

    public final List<String> rows(String groupName, String field) {
        List<String> results = new ArrayList<>();
        final Any rows = getJsonIfExist(json.get("body", groupName, "rows"));
        final Iterator<Any> rowsIterator = rows.iterator();
        while (rowsIterator.hasNext()) {
            final Any row = rowsIterator.next();
            final Any value = row.get(field);
            results.add(value.toString().trim());
        }
        return results;
    }

    public final List<String> rows(String groupName, String field, Map<String, Object> keyVal) {
        if (keyVal == null) {
            return rows(groupName, field);
        }

        List<String> results = new ArrayList<>();
        final Any rows = getJsonIfExist(json.get("body", groupName, "rows"));
        final Iterator<Any> rowsIterator = rows.iterator();
        while (rowsIterator.hasNext()) {
            final Any row = rowsIterator.next();
            boolean match = true;
            for (Map.Entry<String, Object> entry : keyVal.entrySet()) {
                final Any key = row.get(entry.getKey());
                if(!entry.getValue().equals(key.toString())){
                    match = false;
                }
            }
            if(match) {
                final Any value = row.get(field);
                results.add(value.toString().trim());
            }
        }
        return results;
    }

    public final Any rows(String groupName){
        return getJsonIfExist(json.get("body", groupName, "rows"));
    }

    public final String type(Any obj){
        return getJsonIfExist(obj.get("type")).toString();
    }

    public final Integer row(Any position) {
        return getJsonIfExist(json.get("row")).toInt();
    }

}
