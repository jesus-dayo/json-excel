package com.dayosoft.excel.model;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Sum implements Formula{

    private String addend1;
    private String addend2;


    @Override
    public String getFormula() {
        return addend1+"+"+addend2;
    }
}
