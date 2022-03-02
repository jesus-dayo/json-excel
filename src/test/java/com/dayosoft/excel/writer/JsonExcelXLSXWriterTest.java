package com.dayosoft.excel.writer;

import com.dayosoft.excel.TestDataHelper;
import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.expression.parser.ExpressionParser;
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

    JsonExcelXLSXWriter jsonExcelXLSXWriter;

    @BeforeEach
    void init(){
        jsonExcelXLSXWriter = new JsonExcelXLSXWriter(
                new ExpressionRenderingEngine(TestDataHelper.registeredParsers(), new ExpressionParser()));
    }

    @Test
    void givenComplexJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String file = "src/test/resources/complex/Complex.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2007)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelXLSXWriter.write(request);

        File expected = new File(this.getClass().getResource("/complex/Complex.xlsx").getFile());
        ExcelXLSXFileAssertion.isEqual(expected, actual);
    }
}
