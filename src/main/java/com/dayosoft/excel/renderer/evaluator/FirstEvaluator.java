package com.dayosoft.excel.renderer.evaluator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstEvaluator implements Evaluator<List<Object>, Object>{

    @Override
    public Object evaluate(List<Object> list) {
        return list.get(0);
    }
}
