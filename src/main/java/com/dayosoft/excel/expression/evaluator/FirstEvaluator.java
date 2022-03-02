package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.FirstRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FirstEvaluator implements Evaluator<List<Object>, Object, CellRenderer>{

    private final FirstRenderer firstRenderer;

    @Override
    public Object evaluate(List<Object> list) {
        return list.get(0);
    }

    @Override
    public CellRenderer renderer() {
        return firstRenderer;
    }

}
