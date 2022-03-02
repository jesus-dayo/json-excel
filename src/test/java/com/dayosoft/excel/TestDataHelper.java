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

    public static final String SAMPLE_ASSETS = "{\"body\": {\n" +
            "    \"Asset Inflow Details\": {\n" +
            "      \"columns\": [\n" +
            "        {\n" +
            "          \"name\": \"assetCode\",\n" +
            "          \"type\": \"string\",\n" +
            "          \"field\": \"assetCode\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"assetName\",\n" +
            "          \"type\": \"string\",\n" +
            "          \"field\": \"assetName\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"daysInflow\",\n" +
            "          \"type\": \"decimal\",\n" +
            "          \"field\": \"daysInflow\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"daysInflowCalc\",\n" +
            "          \"type\": \"decimal\",\n" +
            "          \"field\": \"daysInflowCalc\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"groupAccount\",\n" +
            "          \"type\": \"string\",\n" +
            "          \"field\": \"groupAccount\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"inflow\",\n" +
            "          \"type\": \"decimal\",\n" +
            "          \"field\": \"inflow\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"reportDate_Day\",\n" +
            "          \"type\": \"decimal\",\n" +
            "          \"field\": \"reportDate_Day\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"rows\": [\n" +
            "        {\n" +
            "          \"groupAccount\": \"132132\",\n" +
            "          \"daysInflow\": 24,\n" +
            "          \"assetCode\": \"100\",\n" +
            "          \"reportDate_Day\": 30,\n" +
            "          \"assetName\": \"クッキー\",\n" +
            "          \"inflow\": 16540500,\n" +
            "          \"daysInflowCalc\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"groupAccount\": \"132132\",\n" +
            "          \"daysInflow\": 15,\n" +
            "          \"assetCode\": \"200\",\n" +
            "          \"reportDate_Day\": 30,\n" +
            "          \"assetName\": \"MONDAY(MON)\",\n" +
            "          \"inflow\": 16020444,\n" +
            "          \"daysInflowCalc\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"groupAccount\": \"132132\",\n" +
            "          \"daysInflow\": 0,\n" +
            "          \"assetCode\": \"300\",\n" +
            "          \"reportDate_Day\": 30,\n" +
            "          \"assetName\": \"チョコレート(CHOC)\",\n" +
            "          \"inflow\": 0,\n" +
            "          \"daysInflowCalc\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"groupAccount\": \"132132\",\n" +
            "          \"daysInflow\": 0,\n" +
            "          \"assetCode\": \"400\",\n" +
            "          \"reportDate_Day\": 30,\n" +
            "          \"assetName\": \"コーヒー(COF)\",\n" +
            "          \"inflow\": 0,\n" +
            "          \"daysInflowCalc\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"groupAccount\": \"132132\",\n" +
            "          \"daysInflow\": 0,\n" +
            "          \"assetCode\": \"500\",\n" +
            "          \"reportDate_Day\": 30,\n" +
            "          \"assetName\": \"PA(パイナップル)\",\n" +
            "          \"inflow\": 0,\n" +
            "          \"daysInflowCalc\": 0\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "   }\n" +
            "  }";

}
