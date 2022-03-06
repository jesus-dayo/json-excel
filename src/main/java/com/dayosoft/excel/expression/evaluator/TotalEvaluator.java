package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.TotalRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TotalEvaluator implements Evaluator<List<Object>, Object, CellRenderer>{

    private final TotalRenderer totalRenderer;

    @Override
    public Object evaluate(List<Object> list) {
        if(list.size() <= 1){
            return list.get(0);
        }
        return list.stream().reduce(0, (o1, o2)-> new BigDecimal(o1.toString()).add(new BigDecimal(o2.toString())).toString());
    }

    @Override
    public CellRenderer renderer() {
        return totalRenderer;
    }

}
