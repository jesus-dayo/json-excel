package com.dayosoft.excel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TemplateMerge {

    private TemplatePosition start;
    private TemplatePosition end;

}
