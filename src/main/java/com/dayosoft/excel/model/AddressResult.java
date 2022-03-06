package com.dayosoft.excel.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResult {

    private String address;
    private Integer lastRow;

}
