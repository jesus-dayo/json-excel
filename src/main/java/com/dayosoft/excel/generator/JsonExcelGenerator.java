package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.writer.JsonExcelWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonExcelGenerator {

    private final JsonExcelWriter jsonExcelWriter;

    public File generateReport(JsonExcelRequest request) throws IOException {
        return jsonExcelWriter.write(request, request.getReportType());
    }

}
