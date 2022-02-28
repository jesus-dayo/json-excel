package com.dayosoft.excel.renderer.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ObjectRegJsonObjectPathParserTest {

    ObjectExpressionParser objectExpressionMapper;

    @BeforeEach
    void init(){
        objectExpressionMapper = new ObjectExpressionParser();
    }

    @Test
    void givenObjectExpression_whenParsed_shouldReturnArray() throws InvalidExpressionException {
        final String[] actual = objectExpressionMapper.parse("Client Details:clientCode1");

        assertEquals(2, actual.length);
        assertEquals("Client Details", actual[0]);
        assertEquals("clientCode1", actual[1]);
    }

}