package com.dayosoft.excel.writer;

import com.dayosoft.excel.type.ExcelReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonExcelWriterFactory {

    private final JsonExcelXLSWriter jsonExcelXLSWriter;
    private final JsonExcelXLSXWriter jsonExcelXLSXWriter;

    public JsonExcelWriter getByReportType(ExcelReportType type){
        if(ExcelReportType.EXCEL_2003 == type){
            return jsonExcelXLSWriter;
        }
        return jsonExcelXLSXWriter;
    }
}
