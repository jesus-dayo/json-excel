package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.test.helper.TestFileUtils;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JsonExcelGeneratorTest {

    @Mock
    JsonExcelWriter jsonExcelWriter;

    JsonExcelGenerator jsonExcelGenerator;

    @BeforeEach
    void init(){
        jsonExcelGenerator = new JsonExcelGenerator(jsonExcelWriter);
    }

    @Test
    void givenJsonRequestSimpleReport_whenGenerateReport_shouldCallWriteWithExcel2003() throws Exception {
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2003)
                .build();

        jsonExcelGenerator.generateReport(request);

        verify(jsonExcelWriter,times(1)).write(request,ExcelReportType.EXCEL_2003);
    }

    @Test
    void givenJsonRequestComplexReport_whenGenerateReport_shouldCallWriteWithExcel2007() throws Exception {
        String file = "src/test/resources/complex/Complex.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .data(TestFileUtils.readJsonFileAsString(file))
                .reportType(ExcelReportType.EXCEL_2007)
                .build();

        jsonExcelGenerator.generateReport(request);

        verify(jsonExcelWriter,times(1)).write(request,ExcelReportType.EXCEL_2007);
    }


}