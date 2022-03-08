package com.dayosoft.excel.writer;

import com.dayosoft.excel.TestDataHelper;
import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.expression.evaluator.ObjectEvaluator;
import com.dayosoft.excel.expression.parser.ExpressionParser;
import com.dayosoft.excel.expression.parser.ObjectExpressionParser;
import com.dayosoft.excel.expression.parser.RowParser;
import com.dayosoft.excel.expression.renderer.ColRowRenderer;
import com.dayosoft.excel.expression.renderer.ObjectRenderer;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelXLSXFileAssertion;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

public class JsonExcelXLSXWriterTest {

    @TempDir
    File tempDir;

    JsonExcelWriter jsonExcelXLSXWriter;

    @BeforeEach
    void init(){
        jsonExcelXLSXWriter = new JsonExcelWriter(
                new ExpressionRenderingEngine(TestDataHelper.registeredParsers(),
                        new ExpressionParser(),
                        new ColRowRenderer(new RowParser(new ObjectExpressionParser(new ObjectEvaluator(new ObjectRenderer())), new ExpressionParser())),
                new ObjectExpressionParser(new ObjectEvaluator(new ObjectRenderer()))));
    }

    @Test
    void givenComplexJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String file = "src/test/resources/complex/Complex.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2007)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelXLSXWriter.write(request, ExcelReportType.EXCEL_2007);

        File expected = new File(this.getClass().getResource("/complex/Complex.xlsx").getFile());
        ExcelXLSXFileAssertion.isEqual(expected, actual);
    }
}
