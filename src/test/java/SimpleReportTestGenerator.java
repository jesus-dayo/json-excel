import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleReportTestGenerator {

    public static void main(String[] args) throws IOException {
        JsonExcelGenerator jsonExcelGenerator = new JsonExcelGenerator();
        String file = "src/test/resources/simple/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .directory("/tmp")
                .fileName(("simple_"+System.currentTimeMillis()).substring(0,12))
                .reportType(ExcelReportType.EXCEL_2003)
                .data(new String(Files.readAllBytes(Paths.get(file))))
                .build();
        jsonExcelGenerator.generateReport(request);
    }

}
