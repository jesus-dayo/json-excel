package com.dayosoft.excel.renderer.evaluator;

import java.util.List;

public class FirstEvaluator implements Evaluator<List<Object>, Object>{

    @Override
    public Object evaluate(List<Object> list) {
        return list.get(0);
    }
}
