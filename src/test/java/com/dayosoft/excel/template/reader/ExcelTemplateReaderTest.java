package com.dayosoft.excel.template.reader;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelXLSXFileAssertion;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelXLSXWriter;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ExcelTemplateReaderTest {

    ExcelTemplateReader excelTemplateReader;

    @BeforeEach
    void init(){
        excelTemplateReader = new ExcelTemplateReader();
    }

    @Test
    void givenComplexTemplateXLSX_whenReadTemplate_thenReturnCorrectJSON() throws Exception {
        String file = "src/test/resources/complex/Complex_template.xlsx";
        InputStream inputStream = new FileInputStream(new File(file));

        String actual = excelTemplateReader.excelToJsonTemplate(inputStream, ExcelReportType.EXCEL_2007);

        final String expected = TestFileUtils.readJsonFileAsString("src/test/resources/complex/complex-template.json");
        assertEquals(expected, new JSONObject(actual).toString(4));
    }

}