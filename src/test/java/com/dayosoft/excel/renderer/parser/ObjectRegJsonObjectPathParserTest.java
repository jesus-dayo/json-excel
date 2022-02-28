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
    void givenObjectExpression_whenParsed_shouldReturnArray()  {
        final String actual = objectExpressionMapper.parse("Client Details:clientCode1");

        assertEquals("Client Details:clientCode1", actual);
    }

}