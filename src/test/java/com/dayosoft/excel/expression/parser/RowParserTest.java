package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.TestDataHelper;
import com.dayosoft.excel.exception.InvalidExpressionException;
import com.dayosoft.excel.exception.InvalidObjectExpressionException;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.model.KeyDataMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowParserTest {

    RowParser rowParser;

    @BeforeEach
    void init(){
        rowParser = new RowParser(new ObjectExpressionParser(new ObjectEvaluator(new ObjectRenderer())), new ExpressionParser());
    }

    @Test
    void givenExpression_whenParsed_shouldReturnKeyData() throws InvalidExpressionException, InvalidObjectExpressionException {
        String given = "row(assetCode#Asset Inflow Details:assetName)";
        String data = TestDataHelper.SAMPLE_ASSETS;

        final KeyDataMap keyData = rowParser.parse(given, data, "assetCode", "100");

        assertNotNull(keyData);
        assertEquals("assetCode", keyData.getKey());
        assertEquals("クッキー", keyData.getValue());
    }

    @Test
    void givenExpressionAndCheckKey_whenParsed_shouldReturnKeyData() throws InvalidExpressionException, InvalidObjectExpressionException {
        String given = "row(assetCode#Asset Inflow Details:assetCode)";
        String data = TestDataHelper.SAMPLE_ASSETS;

        final KeyDataMap keyData = rowParser.parse(given, data, "assetName", "クッキー");

        assertNull(keyData);
    }

}