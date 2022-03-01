package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;
import com.dayosoft.excel.writer.JsonExcelXLSWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JsonExcelGeneratorTest {

    @Mock
    JsonExcelWriterFactory jsonExcelWriterFactory;

    JsonExcelGenerator jsonExcelGenerator;

    @BeforeEach
    void init(){
        jsonExcelGenerator = new JsonExcelGenerator(jsonExcelWriterFactory);
    }

    @Test
    void givenJsonRequestSimpleReport_whenGenerateReport_shouldCallXLSWriter() throws Exception {
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2003)
                .build();
        JsonExcelXLSWriter jsonExcelXLSWriter = Mockito.mock(JsonExcelXLSWriter.class);
        when(jsonExcelWriterFactory.getByReportType(ExcelReportType.EXCEL_2003)).thenReturn(jsonExcelXLSWriter);

        jsonExcelGenerator.generateReport(request);

        verify(jsonExcelXLSWriter,times(1)).write(request);
    }


}