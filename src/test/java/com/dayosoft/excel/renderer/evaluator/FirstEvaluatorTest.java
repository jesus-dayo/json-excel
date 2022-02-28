package com.dayosoft.excel.renderer.evaluator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirstEvaluatorTest {

    FirstEvaluator firstEvaluator;

    @BeforeEach
    void init(){
        firstEvaluator = new FirstEvaluator();
    }

    @Test
    void givenListOfObjects_whenEvaluate_shouldReturnFirstObject(){
        List<Object> given = Collections.singletonList("Sample");

        final Object actual = firstEvaluator.evaluate(given);

        assertEquals(given.get(0), actual);
    }

}