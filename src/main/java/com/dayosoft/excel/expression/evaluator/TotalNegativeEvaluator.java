package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import com.dayosoft.excel.expression.renderer.TotalNegativeRenderer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TotalNegativeEvaluator implements Evaluator<List<Object>, Object, CellRenderer>{

    private final TotalNegativeRenderer totalNegativeRenderer;

    @Override
    public Object evaluate(List<Object> list) {
        if(list.size() <= 1){
            return addNegative(new BigDecimal(list.get(0).toString()).toString());
        }
        return addNegative(list.stream().reduce(0, (o1, o2)-> new BigDecimal(o1.toString()).add(new BigDecimal(o2.toString())).toString()).toString());
    }

    private String addNegative(String value){
        if(value.contains("-")){
            return value;
        }
        return "-"+value;
    }

    @Override
    public CellRenderer renderer() {
        return totalNegativeRenderer;
    }

}
