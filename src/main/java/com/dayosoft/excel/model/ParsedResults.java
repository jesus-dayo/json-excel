package com.dayosoft.excel.model;

import com.dayosoft.excel.expression.parser.Parser;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ParsedResults {

    private String parsedValue;
    private Parser parser;

}
