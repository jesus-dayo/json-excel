package com.dayosoft.excel.model;

import com.dayosoft.excel.expression.parser.Parser;
import com.dayosoft.excel.type.ExcelJsonType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.List;

@Builder
@Getter
@Setter
public class MappedResults {

    private String key;
    private List<String> results;
    private ExcelJsonType excelJsonType;
    private String[] parameters;
    private Deque<Parser> parserChain;

}
