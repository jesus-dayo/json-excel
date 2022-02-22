package com.dayosoft.excel.generator;

import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.writer.JsonExcelWriterFactory;

import java.io.File;
import java.io.IOException;

public class JsonExcelGenerator {

    public File generateReport(JsonExcelRequest request) throws IOException {
        if (ExcelReportType.EXCEL_2003 == request.getReportType()) {
            return JsonExcelWriterFactory.getByReportType(ExcelReportType.EXCEL_2003)
                    .write(request);
        }
        return JsonExcelWriterFactory.getByReportType(ExcelReportType.EXCEL_2007)
                .write(request);
    }

}
