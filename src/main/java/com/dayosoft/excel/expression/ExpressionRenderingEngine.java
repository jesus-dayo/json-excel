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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

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
        final Deque<Parser> parserMatches = getMatchedParsers(subExpression);
        if (parserMatches.isEmpty()) {
            log.warn("didnt found parser for {}", subExpression);
            defaultRender(renderRequest, expression);
            return;
        }

        final MappedResults mappedResults = runParser(renderRequest, subExpression, parserMatches);
        runChainRendering(renderRequest, parserMatches, mappedResults);
    }

    public MappedResults runParser(RenderRequest renderRequest, String subExpression, Deque<Parser> parserMatches) throws ExpressionException {
        final Parser foundParser = parserMatches.pop();
        log.info("found Parser {} for {}", foundParser, subExpression);
        final String parsedResults = foundParser.parse(subExpression);
        final MappedResults mappedResults = jsonListMapper.map(parsedResults, renderRequest.getData());
        mappedResults.setParserChain(parserMatches);
        foundParser.renderer().render(renderRequest, mappedResults);
        return mappedResults;
    }

    public void runChainRendering(RenderRequest renderRequest, Deque<Parser> parserMatches, MappedResults mappedResults) throws ExpressionException {
        while (!parserMatches.isEmpty()) {
            final Parser foundParser = parserMatches.pop();
            log.info("chain render {} ", foundParser, renderRequest.getTemplateColumn().getValue());
            foundParser.renderer().render(renderRequest, mappedResults);
        }
    }

    public Deque<Parser> getMatchedParsers(String subExpression) {
        return registeredParsers.stream()
                .filter(parser -> parser.isRegExMatch(subExpression)).collect(Collectors.toCollection(ArrayDeque::new));
    }

    private void defaultRender(RenderRequest renderRequest, String value) {
        renderRequest.getCell().setCellValue(value);
        renderRequest.getTemplateColumn().setRendered(true);
    }

}
