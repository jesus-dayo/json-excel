package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonExcelGenerator {

    private final JsonExcelWriterFactory jsonExcelWriterFactory;

    public File generateReport(JsonExcelRequest request) throws IOException {
        if (ExcelReportType.EXCEL_2003 == request.getReportType()) {
            return jsonExcelWriterFactory.getByReportType(ExcelReportType.EXCEL_2003)
                    .write(request);
        }
        return jsonExcelWriterFactory.getByReportType(ExcelReportType.EXCEL_2007)
                .write(request);
    }

}
