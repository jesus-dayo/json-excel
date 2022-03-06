package com.dayosoft.excel.model;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Divide implements Formula{

    private String dividend;
    private String divisor;


    @Override
    public String getFormula() {
        return dividend+"/"+divisor;
    }
}
