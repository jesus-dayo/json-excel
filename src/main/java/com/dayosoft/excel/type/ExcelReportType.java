package com.dayosoft.excel.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExcelReportType {

    EXCEL_2003("xls"),
    EXCEL_2007("xlsx");

    private String extension;
}
