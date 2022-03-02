package com.dayosoft.excel.test.helper;

import com.dayosoft.excel.expression.evaluator.FirstEvaluator;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.expression.parser.ExpressionParser;
import com.dayosoft.excel.expression.parser.FirstFunctionParser;
import com.dayosoft.excel.expression.parser.ObjectExpressionParser;
import com.dayosoft.excel.expression.parser.Parser;
import com.dayosoft.excel.expression.renderer.FirstRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestFileUtils {

    public static String readJsonFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
