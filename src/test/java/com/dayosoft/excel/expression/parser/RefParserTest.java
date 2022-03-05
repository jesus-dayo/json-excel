package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefParserTest {

    RefParser refParser;

    @BeforeEach
    void init(){
        refParser = new RefParser();
    }

    @Test
    void givenRef_whenParse_shouldReturnPosition() throws InvalidExpressionException {
        String given = "ref(1,2)";

        final String actual = refParser.parse(given);

        assertEquals("1,2", actual);
    }

}