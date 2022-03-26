package com.dayosoft.excel.expression.mapper;

import com.dayosoft.excel.TestDataHelper;
import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.model.MappedResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonListMapperTest {

    JsonListMapper jsonListMapper;

    @BeforeEach
    void init() {
        jsonListMapper = new JsonListMapper();
    }

    @Test
    void givenExpressionAndData_whenMap_shouldReturnListOfObjects() throws InvalidObjectExpressionException {
        String givenExpression = "Client Details:assetCode";
        String givenData = TestDataHelper.SAMPLE_CLIENT;

        final MappedResults mappedResults = jsonListMapper.map(givenExpression, givenData);

        assertEquals("132132", mappedResults.getResults().get(0));
    }

    @Test
    void givenNestedExpressionAndData_whenMap_shouldReturnListOfObjects() throws InvalidObjectExpressionException {
        String givenExpression = "Client Details:address:zip";
        String givenData = TestDataHelper.SAMPLE_CLIENT;

        final MappedResults mappedResults = jsonListMapper.map(givenExpression, givenData);

        assertEquals("069069", mappedResults.getResults().get(0));
    }

    @Test
    void givenNestedArrExpressionAndData_whenMap_shouldReturnListOfObjects() throws InvalidObjectExpressionException {
        String givenExpression = "Client Details:options:option";
        String givenData = TestDataHelper.SAMPLE_CLIENT;

        final MappedResults mappedResults = jsonListMapper.map(givenExpression, givenData);

        assertEquals("nested", mappedResults.getResults().get(0));
    }

    @Test
    void givenInvalidNestedArrExpressionAndData_whenMap_shouldNotCauseInfiniteLoop() {
        String givenExpression = "Client Details:options:notexist";
        String givenData = TestDataHelper.SAMPLE_CLIENT;

        assertDoesNotThrow(() -> jsonListMapper.map(givenExpression, givenData));
    }
}