package com.dayosoft.excel.model;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "templates", schemaVersion= "1.0")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id

    private String name;
    private String description;
    private String format;
    private List<TemplateSheet> sheets;

}
