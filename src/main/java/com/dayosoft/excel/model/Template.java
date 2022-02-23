package com.dayosoft.excel.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Template {

    private String name;
    private String description;
    private String format;
    private List<TemplateSheet> sheets;

}
