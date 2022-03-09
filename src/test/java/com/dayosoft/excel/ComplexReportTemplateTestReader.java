package com.dayosoft.excel;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComplexReportTemplateTestReader {

    @Autowired
    ExcelTemplateReader excelTemplateReader;

    @Test
    void givenExcel2007Template_whenRead_shouldReturnTemplate() throws IOException {
        File sampleExcel = new File("src/test/resources/complex/Complex_template.xlsx");
        final FileInputStream excelFileStream = new FileInputStream(sampleExcel);

        final Template template = excelTemplateReader.excelToJsonTemplate("name","desc",excelFileStream, ExcelReportType.EXCEL_2007);

        assertEquals(ExcelReportType.EXCEL_2007.getExtension(), template.getFormat());
        assertEquals("name", template.getName());
        assertEquals("desc", template.getDescription());
    }

}
