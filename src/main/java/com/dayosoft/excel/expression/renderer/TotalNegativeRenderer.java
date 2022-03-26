package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.expression.evaluator.Evaluator;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.Value;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TotalNegativeRenderer implements CellRenderer {

    private final Evaluator totalNegativeEvaluator;

    @Override
    public MappedResults render(RenderRequest renderRequest, MappedResults mappedResults) throws InvalidObjectExpressionException {
        final Object evaluate = totalNegativeEvaluator.evaluate(mappedResults);
        log.debug("setting value " + evaluate.toString());
        mappedResults.getExcelJsonType()
                .getValueSetter().accept(Value.builder()
                        .value(evaluate.toString())
                        .cell(renderRequest.getCell())
                        .build());
        renderRequest.getTemplateColumn().setRendered(true);
        return mappedResults;
    }

}
