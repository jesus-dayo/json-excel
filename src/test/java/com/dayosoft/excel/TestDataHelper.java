package com.dayosoft.excel;

import com.dayosoft.excel.expression.evaluator.FirstEvaluator;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.expression.parser.*;
import com.dayosoft.excel.expression.renderer.FirstRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;

import java.util.ArrayList;
import java.util.List;

public final class TestDataHelper {

    public static List<ParserEvaluator> registeredParsers(){
        List<ParserEvaluator> parsers = new ArrayList<>();
        parsers.add(new ObjectExpressionParser(new ObjectEvaluator(new ObjectRenderer())));
        parsers.add(new FirstFunctionParser(new FirstEvaluator(new FirstRenderer())));
        return parsers;
    }

    public static final String SAMPLE_CLIENT_DETAILS = "{\"body\": {\"Client Details\": {\n" +
            "      \"columns\": [\n" +
            "        {\n" +
            "          \"name\": \"clientName\",\n" +
            "          \"type\": \"string\",\n" +
            "          \"field\": \"clientName\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"clientCode1\",\n" +
            "          \"type\": \"string\",\n" +
            "          \"field\": \"clientCode1\"\n" +
            "        },\n" +
            "      ],\n" +
            "      \"rows\": [\n" +
            "        {\n" +
            "          \"clientCode1\": \"1010\",\n" +
            "          \"clientName\": \"ABC株式会社\",\n" +
            "          \"assetManagerCode1\": \"1011\",\n" +
            "          \"TerminationDate\": \"\",\n" +
            "          \"reportingMonth\": \"09/30/2021\",\n" +
            "          \"assetManagerCode2\": \"1\",\n" +
            "          \"DateYear\": \"2021\",\n" +
            "          \"DateMonth\": \"9\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n"+
            "   }\n"+
            "}";

}
