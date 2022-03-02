package com.dayosoft.excel.expression.evaluator;

import com.dayosoft.excel.expression.renderer.FirstRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirstEvaluatorTest {

    FirstEvaluator firstEvaluator;

    @BeforeEach
    void init(){
        firstEvaluator = new FirstEvaluator(new FirstRenderer());
    }

    @Test
    void givenListOfObjects_whenEvaluate_shouldReturnFirstObject(){
        List<Object> given = Collections.singletonList("Sample");

        final Object actual = firstEvaluator.evaluate(given);

        assertEquals(given.get(0), actual);
    }

}