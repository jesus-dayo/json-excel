package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.ColArrRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ColArrEvaluator implements Evaluator<List<Object>,List<Object>, CellRenderer>{

    private final ColArrRenderer colArrRenderer;

    @Override
    public List<Object> evaluate(List<Object> input) {
        return input;
    }

    @Override
    public CellRenderer renderer() {
        return colArrRenderer;
    }
}
