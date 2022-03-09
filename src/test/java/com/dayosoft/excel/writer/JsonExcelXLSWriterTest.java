package com.dayosoft.excel.writer;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelFileAssertion;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class JsonExcelXLSWriterTest {

    @TempDir
    File tempDir;


    @Autowired
    JsonExcelWriter jsonExcelWriter;

    @Test
    void givenSimpleJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String simpleDataFile = "src/test/resources/simple/Simple.json";
        String simpleTemplateFile = "src/test/resources/simple/Simple_template.json";
        final String templateAsStr = TestFileUtils.readJsonFileAsString(simpleTemplateFile);
        final Template template = JsonIterator.deserialize(templateAsStr, Template.class);
        JsonExcelRequest request = JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(simpleDataFile))
                .reportType(ExcelReportType.EXCEL_2003)
                .template(template)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelWriter.write(request, ExcelReportType.EXCEL_2003);

        File expected = new File(this.getClass().getResource("/simple/Simple.xls").getFile());
        ExcelFileAssertion.isExcel2004Equal(expected, actual);
    }

}