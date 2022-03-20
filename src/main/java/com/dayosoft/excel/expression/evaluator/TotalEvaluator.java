package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.expression.mapper.ListMapper;
import com.dayosoft.excel.model.MappedResults;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TotalEvaluator implements DataEvaluator<BigDecimal> {

    private final ListMapper jsonListMapper;

    @Override
    public BigDecimal evaluate(MappedResults mappedResults) throws InvalidObjectExpressionException {
        final List<String> results = mappedResults.getResults();
        if (results == null || results.isEmpty()) {
            return BigDecimal.ZERO;
        }
        if (results.size() <= 1) {
            return new BigDecimal(results.get(0));
        }
        return new BigDecimal(results.stream().reduce((o1, o2) -> new BigDecimal(o1)
                .add(new BigDecimal(o2)).toString()).get());
    }
}
