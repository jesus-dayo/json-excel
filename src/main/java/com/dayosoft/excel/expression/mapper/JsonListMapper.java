package com.dayosoft.excel.expression.mapper;

import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.KeyValue;
import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.util.JsonDataTraverser;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.JsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@Slf4j
public class JsonListMapper implements ListMapper {

    @Override
    public MappedResults map(String expression, String data, KeyValue... dependencyKeyValue) throws InvalidObjectExpressionException {
        if (expression.contains("#")) {
            final String[] keyExpression = expression.split("#");
            return buildList(keyExpression[1], data, dependencyKeyValue);
        }
        if (!expression.contains(":")) {
            return MappedResults.builder()
                    .results(Collections.singletonList(expression))
                    .build();
        }

        return buildList(expression, data, dependencyKeyValue);
    }

    private MappedResults buildList(String expression, String data, KeyValue... dependencyKeyValue) {
        final String[] path = expression.split(":");
        if (path.length <= 1) {
            return MappedResults.builder()
                    .results(Collections.emptyList())
                    .build();
        }
        Any any = JsonIterator.deserialize(data);
        JsonDataTraverser jsonTraverser = new JsonDataTraverser(any);

        final String groupName = path[0];
        final String field = path[path.length - 1];
        try {
            return MappedResults.builder()
                    .results(jsonTraverser.rows(groupName, Arrays.copyOfRange(path, 1, path.length), dependencyKeyValue))
                    .excelJsonType(jsonTraverser.typeByField(groupName, field))
                    .key(field)
                    .build();
        } catch (JsonException jsonException) {
            log.error(expression + " not found");
        } catch (InvalidObjectExpressionException e) {
            log.error(expression + " invalid");
        }

        return MappedResults.builder()
                .results(Collections.emptyList())
                .key(field)
                .build();
    }

}
