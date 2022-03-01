package com.dayosoft.excel.test.helper;

import com.dayosoft.excel.renderer.evaluator.FirstEvaluator;
import com.dayosoft.excel.renderer.evaluator.ObjectEvaluator;
import com.dayosoft.excel.renderer.parser.ExpressionParser;
import com.dayosoft.excel.renderer.parser.FirstFunctionParser;
import com.dayosoft.excel.renderer.parser.ObjectExpressionParser;
import com.dayosoft.excel.renderer.parser.Parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestFileUtils {

    public static String readJsonFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static List<Parser> registeredParsers(){
        List<Parser> parsers = new ArrayList<>();
        parsers.add(new ExpressionParser());
        parsers.add(new ObjectExpressionParser(new ObjectEvaluator()));
        parsers.add(new FirstFunctionParser(new FirstEvaluator()));
        return parsers;
    }

}
