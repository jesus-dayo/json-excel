package com.dayosoft.excel;

import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.request.JsonExcelRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public static void main(String[] args) throws IOException {
        JsonExcelGenerator jsonExcelGenerator = new JsonExcelGenerator();
        String file = "src/main/resources/Simple.json";
        JsonExcelRequest request =  JsonExcelRequest.builder()
                .directory("/tmp")
                .fileName(("simple_"+System.currentTimeMillis()).substring(0,12))
                .json(new String(Files.readAllBytes(Paths.get(file))))
                .build();
        jsonExcelGenerator.generateReport(request);
    }

}
