package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.ExcelFileAssertion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                .json(readJsonFileAsString(file))
                .directory(tempDir.getAbsolutePath()).fileName("sample").build();

        File actual = jsonExcelXLSWriter.write(request);

        File expected = new File(this.getClass().getResource("/simple/Simple.xls").getFile());
        ExcelFileAssertion.isEqual(expected, actual);
    }

    private String readJsonFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}