package com.dayosoft.excel.model;

import com.dayosoft.excel.type.ExcelJsonType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MappedResults {

    private String key;
    private List<String> results;
    private ExcelJsonType excelJsonType;

}
