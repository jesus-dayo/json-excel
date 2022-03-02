package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return jsonTraverser.rows(groupName, key);
    }

    @Override
    public CellRenderer renderer() {
        return objectRenderer;
    }

}
