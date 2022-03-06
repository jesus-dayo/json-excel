package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.TestDataHelper;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.model.JsonObjectPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ObjectEvaluatorTest {

    ObjectEvaluator objectEvaluator;

    @BeforeEach
    void init(){
        objectEvaluator = new ObjectEvaluator(new ObjectRenderer());
    }

    @Test
    void givenJsonPathAndData_whenEvaluate_shouldReturnListObjects(){
        String[] jsonPath = new String[]{"Client Details", "clientCode1"};
        String data = TestDataHelper.SAMPLE_CLIENT_DETAILS;

        final List<Object> actual = objectEvaluator.evaluate(JsonObjectPath.builder().path(jsonPath).data(data).build()).getListOfValues();

        assertFalse(actual.isEmpty());
        assertEquals("1010", actual.get(0));
    }

    @Test
    void givenJsonPathAndKeyValueAndData_whenEvaluate_shouldReturnListObjects(){
        String[] jsonPath = new String[]{"Asset Inflow Details", "assetName"};
        String data = TestDataHelper.SAMPLE_ASSETS;
        Map<String, Object> keyValue = new HashMap<>();
        keyValue.put("assetCode","200");

        final List<Object> actual = objectEvaluator.evaluate(JsonObjectPath.builder().path(jsonPath).data(data).keyValue(keyValue).build()).getListOfValues();

        assertFalse(actual.isEmpty());
        assertEquals("MONDAY(MON)", actual.get(0));
    }

}