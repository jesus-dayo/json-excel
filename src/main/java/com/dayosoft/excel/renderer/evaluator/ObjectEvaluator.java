package com.dayosoft.excel.renderer.evaluator;

import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.util.List;

public class ObjectEvaluator implements Evaluator<JsonObjectPath, List<Object>> {

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

}
