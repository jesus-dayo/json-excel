package com.dayosoft.excel.writer;

import com.dayosoft.excel.expression.ExpressionRenderingEngine;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelXLSFileAssertion;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

@ExtendWith(MockitoExtension.class)
class JsonExcelXLSWriterTest {

    @TempDir
    File tempDir;
    @Mock
    ExpressionRenderingEngine expressionRenderingEngine;

    JsonExcelWriter jsonExcelWriter;

    @BeforeEach
    void init() {
        jsonExcelWriter = new JsonExcelWriter(expressionRenderingEngine);
    }

    @Test
    void givenSimpleJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request = JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2003)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelWriter.write(request, ExcelReportType.EXCEL_2003);

        File expected = new File(this.getClass().getResource("/simple/Simple.xls").getFile());
        ExcelXLSFileAssertion.isEqual(expected, actual);
    }

}