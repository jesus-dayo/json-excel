package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.MappedResults;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TotalNegativeEvaluator implements DataEvaluator<String> {

    @Override
    public String evaluate(MappedResults mappedResults) throws InvalidObjectExpressionException {
        final List<String> results = mappedResults.getResults();
        if (results == null || results.isEmpty()) {
            return BigDecimal.ZERO.toString();
        }
        if (results.size() <= 1) {
            return addNegative(new BigDecimal(results.get(0)).toString());
        }
        return addNegative(results.stream().reduce((o1, o2) -> new BigDecimal(o1).add(new BigDecimal(o2)).toString()).get());
    }

    private String addNegative(String value){
        if(value.contains("-")){
            return value;
        }
        return "-"+value;
    }


}
