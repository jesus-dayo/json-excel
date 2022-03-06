package com.dayosoft.excel.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TemplateResponse {

    private boolean success;
    private String name;
    private String format;

}
