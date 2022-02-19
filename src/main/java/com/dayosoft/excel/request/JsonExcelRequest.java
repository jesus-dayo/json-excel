package com.dayosoft.excel.request;

import com.dayosoft.excel.type.ExcelReportType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonExcelRequest {

    private String json;
    private String fileName;
    private String directory;
    private ExcelReportType reportType;

}
