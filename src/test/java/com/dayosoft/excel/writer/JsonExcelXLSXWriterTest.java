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
public class JsonExcelXLSXWriterTest {

    @TempDir
    File tempDir;

    @Autowired
    JsonExcelWriter jsonExcelXLSXWriter;

    @Test
    void givenComplexJson_whenWriteExcel_thenContentIsCorrect() throws Exception {
        String complexFile = "src/test/resources/complex/Complex.json";
        String complexTemplateStr = "src/test/resources/complex/complex-template.json";
        final String templateAsStr = TestFileUtils.readJsonFileAsString(complexTemplateStr);
        final Template template = JsonIterator.deserialize(templateAsStr, Template.class);
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(complexFile))
                .template(template)
                .reportType(ExcelReportType.EXCEL_2007)
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelXLSXWriter.write(request, ExcelReportType.EXCEL_2007);

        File expected = new File(this.getClass().getResource("/complex/Complex_generated.xlsx").getFile());
        ExcelFileAssertion.isExcel2007Equal(expected, actual);
    }
}
