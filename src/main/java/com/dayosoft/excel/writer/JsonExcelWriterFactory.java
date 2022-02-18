package com.dayosoft.excel.writer;

import com.dayosoft.excel.type.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum JsonExcelWriterFactory {

    XLS(ReportType.SIMPLE_REPORT, new JsonExcelXLSWriter()),XLSX(ReportType.COMPLEX_REPORT, new JsonExcelXLSXWriter());

    private final ReportType reportType;
    private final JsonExcelWriter writer;

    public static JsonExcelWriter getByReportType(ReportType type){
        JsonExcelWriterFactory jsonExcelWriterFactory = Arrays.stream(JsonExcelWriterFactory.values())
                .filter(report -> report.getReportType().equals(type))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Unknown report type . " + type.name()));
        return jsonExcelWriterFactory.getWriter();
    }
}
