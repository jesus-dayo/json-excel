package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.ObjectExpressionResults;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.JsonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ObjectEvaluator implements Evaluator<JsonObjectPath, ObjectExpressionResults, CellRenderer> {

    private final ObjectRenderer objectRenderer;

    @Override
    public ObjectExpressionResults evaluate(JsonObjectPath jsonObjectPath) throws InvalidObjectExpressionException {
        final String[] path = jsonObjectPath.getPath();
        if (path.length <= 1 || path == null) {
            return null;
        }
        Any any = JsonIterator.deserialize(jsonObjectPath.getData());
        JsonDataTraverser jsonTraverser = new JsonDataTraverser(any);
        final String groupName = path[0];
        final String key = path[1];
        String type =  jsonTraverser.typeByField(groupName, key).getJsonType();
        try {
            final Map<String, Object> keyValue = jsonObjectPath.getKeyValue();
            if(keyValue !=null && !keyValue.isEmpty()){
                return ObjectExpressionResults.builder()
                        .type(type)
                        .listOfValues(jsonTraverser.rows(groupName, key, keyValue))
                        .build();
            }
            return ObjectExpressionResults.builder()
                    .type(type)
                    .listOfValues(jsonTraverser.rows(groupName, key))
                    .build();
        } catch (JsonException jsonException){
            log.error(Arrays.stream(jsonObjectPath.getPath()).collect(Collectors.joining(",")) + " not found");
        }
        return ObjectExpressionResults.builder()
                .type(type)
                .listOfValues(Collections.emptyList())
                .build();
    }

    @Override
    public CellRenderer renderer() {
        return objectRenderer;
    }

}
