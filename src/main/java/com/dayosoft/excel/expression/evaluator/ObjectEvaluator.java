package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.JsonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ObjectEvaluator implements Evaluator<JsonObjectPath, List<Object>, CellRenderer> {

    private final ObjectRenderer objectRenderer;

    @Override
    public List<Object> evaluate(JsonObjectPath jsonObjectPath) {
        final String[] path = jsonObjectPath.getPath();
        if (path.length <= 1 || path == null) {
            return null;
        }
        Any any = JsonIterator.deserialize(jsonObjectPath.getData());
        JsonDataTraverser jsonTraverser = new JsonDataTraverser(any);
        final String groupName = path[0];
        final String key = path[1];
        try {
            return jsonTraverser.rows(groupName, key);
        } catch (JsonException jsonException){
            log.error(Arrays.stream(jsonObjectPath.getPath()).collect(Collectors.joining(",")) + " not found");
        }
        return Collections.emptyList();
    }

    @Override
    public CellRenderer renderer() {
        return objectRenderer;
    }

}
