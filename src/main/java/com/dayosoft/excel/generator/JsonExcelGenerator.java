package com.dayosoft.excel.generator;

import com.dayosoft.excel.enums.ReportType;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;
import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.any.Any;

import java.io.File;
import java.io.IOException;

public class JsonExcelGenerator {

    public File generateReport(JsonExcelRequest request) throws IOException {
        Any any = JsonIterator.deserialize(request.getJson());
        Any complexReport = any.get("body", ReportType.COMPLEX_REPORT.name());
        if (complexReport.valueType() != ValueType.INVALID) {
            return JsonExcelWriterFactory.getByReportType(ReportType.COMPLEX_REPORT)
                    .write(request);
        } else {
            return JsonExcelWriterFactory.getByReportType(ReportType.SIMPLE_REPORT)
                    .write(request);
        }
    }

}
