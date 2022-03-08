package com.dayosoft.excel;

import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class SimpleReportTestGenerator {

    @Autowired
    JsonExcelGenerator jsonExcelGenerator;

    @DisplayName("Test Simple JSON report generation")
    @Test
    void testSimpleReport() throws IOException {
        String file = "src/test/resources/simple/Simple.json";
        String sampleTemplate = "src/test/resources/simple/Simple_template.json";
        final String templateStr = new String(Files.readAllBytes(Paths.get(sampleTemplate)));
        Template template = JsonIterator.deserialize(templateStr, Template.class);
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .directory("/tmp")
                .fileName(("simple1_"+System.currentTimeMillis()).substring(0,12))
                .reportType(ExcelReportType.EXCEL_2003)
                .template(template)
                .data(new String(Files.readAllBytes(Paths.get(file))))
                .build();
        jsonExcelGenerator.generateReport(request);
    }

}
