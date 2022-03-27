package com.dayosoft.excel.util;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.KeyValue;
import com.dayosoft.excel.type.ExcelJsonType;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

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
        for (Any column : columns) {
            final String nextField = column.get("field").toString();
            if (field.equalsIgnoreCase(nextField)) {
                return ExcelJsonType.getByJsonType(column.get("type").toString());
            }
        }

        throw new InvalidObjectExpressionException("group name " + groupName + " field " + field + " is invalid.");
    }

    public final List<String> rows(String groupName, String... fields) {
        List<String> results = new ArrayList<>();
        final Any rows = getJsonIfExist(json.get("body", groupName, "rows"));
        for (Any row : rows) {
            Any field = traverseField(row, fields);
            results.add(field.toString().trim());
        }
        return results;
    }

    private Any traverseField(Any row, String... fields) {
        if (fields.length == 1 && row.valueType() != ValueType.ARRAY) {
            return row.get(fields[0]);
        }
        if (ValueType.ARRAY == row.valueType()) {
            List<Object> results = new ArrayList<>();
            for (Any newRow : row) {
                final Any result = traverseField(newRow, fields);
                if (result.valueType() != ValueType.NULL
                        && result.valueType() != ValueType.INVALID) {
                    results.add(result.toString());
                }
            }
            return Any.wrap(results.stream().map(Objects::toString).collect(Collectors.joining(",")));
        }
        return traverseField(row.get(fields[0]), Arrays.copyOfRange(fields, 1, fields.length));
    }

    public final List<String> rows(String groupName, String[] fields, KeyValue... keyValList) {
        if (keyValList == null || keyValList.length == 0) {
            return rows(groupName, fields);
        }

        List<String> results = new ArrayList<>();
        final Any rows = getJsonIfExist(json.get("body", groupName, "rows"));
        for (Any row : rows) {
            boolean match = true;
            for (KeyValue keyValue : keyValList) {
                final Any key = row.get(keyValue.getKey());
                if (!keyValue.getValue().equals(key.toString())) {
                    match = false;
                }
            }
            if (match) {
                final Any value = row.get(fields[fields.length - 1]);
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
