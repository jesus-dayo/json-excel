package com.dayosoft.excel.generator;

import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;
import com.dayosoft.excel.writer.JsonExcelXLSWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JsonExcelGeneratorTest {

    JsonExcelGenerator jsonExcelGenerator;

    @BeforeEach
    void init(){
        jsonExcelGenerator = new JsonExcelGenerator();
    }

    @Test
    void givenJsonRequestSimpleReport_whenGenerateReport_shouldCallXLSWriter() throws Exception {
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .json(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.SIMPLE_REPORT)
                .build();
        JsonExcelXLSWriter jsonExcelXLSWriter = Mockito.mock(JsonExcelXLSWriter.class);
        MockedStatic<JsonExcelWriterFactory> jsonExcelWriterFactoryMockedStatic = Mockito.mockStatic(JsonExcelWriterFactory.class);
        jsonExcelWriterFactoryMockedStatic.when(()->JsonExcelWriterFactory.getByReportType(ExcelReportType.SIMPLE_REPORT)).thenReturn(jsonExcelXLSWriter);

        jsonExcelGenerator.generateReport(request);

        verify(jsonExcelXLSWriter,times(1)).write(request);
    }


}