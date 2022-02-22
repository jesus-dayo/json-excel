package com.dayosoft.excel.writer;

import com.dayosoft.excel.type.ExcelReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum JsonExcelWriterFactory {

    XLS(ExcelReportType.EXCEL_2003, new JsonExcelXLSWriter()),XLSX(ExcelReportType.EXCEL_2007, new JsonExcelXLSXWriter());

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
