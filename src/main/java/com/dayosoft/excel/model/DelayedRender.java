package com.dayosoft.excel.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DelayedRender {

    private String value;
    private TemplateColumn templateColumn;
    private String data;
    private boolean isComplete;

}
