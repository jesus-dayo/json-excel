package com.dayosoft.excel.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ObjectExpressionResults {

    private List<Object> listOfValues;
    private String type;

}
