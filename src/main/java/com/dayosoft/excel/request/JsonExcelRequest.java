package com.dayosoft.excel.request;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.type.ExcelReportType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonExcelRequest {

    private String data;
    private Template template;
    private String fileName;
    private String directory;
    private ExcelReportType reportType;
    private boolean skipRendering;

}
