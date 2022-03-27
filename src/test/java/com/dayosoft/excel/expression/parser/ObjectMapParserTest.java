package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.renderer.ObjectMapRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectMapParserTest {

    ObjectMapParser objectMapParser;

    @BeforeEach
    void init() {
        objectMapParser = new ObjectMapParser(new ObjectMapRenderer());
    }

    @Test
    void givenString_whenIsRegExMatch_shouldReturnTrue() {
        assertTrue(objectMapParser.isRegExMatch("Client Details:clientName"));
        assertTrue(objectMapParser.isRegExMatch("Client Details:clientName:firstName"));
    }

    @Test
    void givenString_whenIsRegExMatch_shouldReturnFalse() {
        assertFalse(objectMapParser.isRegExMatch("assetCode#Client Details:clientName"));
        assertFalse(objectMapParser.isRegExMatch("row(Client Details:clientName)"));
    }


}