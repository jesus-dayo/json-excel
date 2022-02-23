package com.dayosoft.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TemplateRange {

    private TemplatePosition start;
    private TemplatePosition end;

}
