package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.expression.mapper.JsonListMapper;
import com.dayosoft.excel.expression.renderer.ColRowRenderer;
import org.junit.jupiter.api.BeforeEach;

class RowParserTest {

    RowParser rowParser;

    @BeforeEach
    void init() {
        rowParser = new RowParser(new ColRowRenderer(new JsonListMapper()));
    }

    // todo fix
//    @Test
//    void givenExpression_whenParsed_shouldReturnKeyData() throws InvalidExpressionException, InvalidObjectExpressionException {
//        String given = "row(assetCode#Asset Inflow Details:assetName)";
//        String data = TestDataHelper.SAMPLE_ASSETS;
//
//        final KeyDataMap keyData = rowParser.parse(given);
//
//        assertNotNull(keyData);
//        assertEquals("assetCode", keyData.getKey());
//        assertEquals("クッキー", keyData.getValue());
//    }
//
//    @Test
//    void givenExpressionAndCheckKey_whenParsed_shouldReturnKeyData() throws InvalidExpressionException, InvalidObjectExpressionException {
//        String given = "row(assetCode#Asset Inflow Details:assetCode)";
//        String data = TestDataHelper.SAMPLE_ASSETS;
//
//        final KeyDataMap keyData = rowParser.parse(given, data, "assetName", "クッキー");
//
//        assertNull(keyData);
//    }

}