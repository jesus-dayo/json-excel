package com.dayosoft.excel.model;

import lombok.Builder;

@Builder
public class TemplateRange {

    private TemplatePosition start;
    private TemplatePosition end;

}
