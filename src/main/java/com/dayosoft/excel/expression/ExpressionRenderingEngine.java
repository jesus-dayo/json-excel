package com.dayosoft.excel.expression;

import com.dayosoft.excel.exception.ExpressionException;
import com.dayosoft.excel.expression.mapper.ListMapper;
import com.dayosoft.excel.expression.parser.Parser;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateColumn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionRenderingEngine {

    private final List<Parser> registeredParsers;
    private final ListMapper jsonListMapper;

    public void renderByExpression(RenderRequest renderRequest) throws ExpressionException {
        final TemplateColumn templateColumn = renderRequest.getTemplateColumn();
        if (templateColumn.isRendered()) {
            return;
        }
        String expression = templateColumn.getValue().toString();
        if (!ExpressionHelper.isValidExpression(expression, RegExpression.EXPRESSION)) {
            defaultRender(renderRequest, expression);
            return;
        }

        final String subExpression = ExpressionHelper.extractStringFromExpression(expression, RegExpression.EXPRESSION);
        final Optional<Parser> parserMatch = registeredParsers.stream()
                .filter(parser -> parser.isRegExMatch(subExpression)).findFirst();
        if (parserMatch.isEmpty()) {
            log.warn("didnt found parser for {}", subExpression);
            defaultRender(renderRequest, expression);
        }

        if (parserMatch.isPresent()) {
            final Parser foundParser = parserMatch.get();
            log.info("found Parser {} for {}", foundParser, subExpression);
            final String jsonPath = foundParser.parse(subExpression);
            final MappedResults mappedResults = jsonListMapper.map(jsonPath, renderRequest.getData(), null);
            foundParser.renderer().render(renderRequest, mappedResults);
        }
    }

    private void defaultRender(RenderRequest renderRequest, String value) {
        renderRequest.getCell().setCellValue(value);
        renderRequest.getTemplateColumn().setRendered(true);
    }

}
