package com.dayosoft.excel.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DivideAddressResult {

    private String address;
    private Integer lastRow;

}
