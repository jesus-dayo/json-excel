package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.model.JsonObjectPath;
import com.dayosoft.excel.model.KeyDataMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class RowParser implements KeyExpressionParser{

    private final ObjectExpressionParser objectExpressionParser;
    private final ExpressionParser expressionParser;

    @Override
    public KeyDataMap parse(String expression, String data, String checkKey, Object value) throws InvalidExpressionException {
        if(ExpressionHelper.isValidExpression(expression, RegExpression.EXPRESSION)){
            expression = expressionParser.parse(expression);
        }
        final String rowExpression = ExpressionHelper.extractStringFromExpression(expression, RegExpression.ROW_FUNC_EXPRESSION);
        final String[] split = rowExpression.split("#");
        final String key = split[0];

        if(StringUtils.isNotEmpty(checkKey)){
            if(!checkKey.equals(key)){
                return null;
            }
        }

        final String subExpression = split[1];
        final String path = objectExpressionParser.parse(subExpression);
        Map<String, Object> keyValue = new HashMap<>();
        keyValue.put(key, value);
        final List<Object> list = ((ObjectEvaluator) objectExpressionParser.evaluator()).evaluate(JsonObjectPath.builder()
                .path(path.split(":")).data(data).keyValue(keyValue).build());

        if(list == null || list.isEmpty()){
            log.error("unsupported expression {}", expression);
            return null;
        }

        return KeyDataMap.builder().key(key).value(list.get(0)).build();
    }
}
