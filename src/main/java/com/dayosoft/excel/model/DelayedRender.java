package com.dayosoft.excel.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DelayedRender {

    private TemplateColumn templateColumn;
    private String data;

}
