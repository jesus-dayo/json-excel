package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelXLSFileAssertion;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

class JsonExcelXLSWriterTest {

    @TempDir
    File tempDir;

    JsonExcelXLSWriter jsonExcelXLSWriter;

    @BeforeEach
    void init(){
        jsonExcelXLSWriter = new JsonExcelXLSWriter();
    }

    @Test
    void givenSimpleJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.SIMPLE_REPORT)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelXLSWriter.write(request);

        File expected = new File(this.getClass().getResource("/simple/Simple.xls").getFile());
        ExcelXLSFileAssertion.isEqual(expected, actual);
    }

}