package com.dayosoft.excel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String name;
    private String description;
    private String format;
    private List<TemplateSheet> sheets;

}
