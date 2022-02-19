package com.dayosoft.excel.writer;

import com.dayosoft.excel.type.ExcelReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum JsonExcelWriterFactory {

    XLS(ExcelReportType.SIMPLE_REPORT, new JsonExcelXLSWriter()),XLSX(ExcelReportType.COMPLEX_REPORT, new JsonExcelXLSXWriter());

    private final ExcelReportType reportType;
    private final JsonExcelWriter writer;

    public static JsonExcelWriter getByReportType(ExcelReportType type){
        JsonExcelWriterFactory jsonExcelWriterFactory = Arrays.stream(JsonExcelWriterFactory.values())
                .filter(report -> report.getReportType().equals(type))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Unknown report type . " + type.name()));
        return jsonExcelWriterFactory.getWriter();
    }
}
