package com.dayosoft.excel.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum ExcelReportType {

    EXCEL_2003("xls"),
    EXCEL_2007("xlsx");

    private String extension;

    public static ExcelReportType findByExtension(String extension){
        final Optional<ExcelReportType> excelReportTypeOptional = Arrays.stream(ExcelReportType.values()).filter(e -> e.getExtension().equalsIgnoreCase(extension))
                .findFirst();
        if(excelReportTypeOptional.isEmpty()){
            throw new UnsupportedOperationException("Unsupport format "+extension);
        }
        return excelReportTypeOptional.get();
    }
}
