package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;

import java.io.File;
import java.io.IOException;

public class JsonExcelGenerator {

    public File generateReport(JsonExcelRequest request) throws IOException {
        if (ExcelReportType.SIMPLE_REPORT == request.getReportType()) {
            return JsonExcelWriterFactory.getByReportType(ExcelReportType.SIMPLE_REPORT)
                    .write(request);
        }
        return JsonExcelWriterFactory.getByReportType(ExcelReportType.COMPLEX_REPORT)
                .write(request);
    }

}
