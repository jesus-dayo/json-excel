import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ComplexReportTestGenerator {

    public static void main(String[] args) throws IOException {
        JsonExcelGenerator jsonExcelGenerator = new JsonExcelGenerator();
        String sampleData = "src/test/resources/complex/Complex.json";
        String sampleTemplate = "src/test/resources/complex/complex-template.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .directory("/tmp")
                .fileName(("complex_"+System.currentTimeMillis()).substring(0,12))
                .reportType(ExcelReportType.EXCEL_2007)
                .data(new String(Files.readAllBytes(Paths.get(sampleData))))
                .template(new String(Files.readAllBytes(Paths.get(sampleTemplate))))
                .build();
        jsonExcelGenerator.generateReport(request);
    }

}
