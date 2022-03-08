package com.dayosoft.excel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.*;

import java.util.List;

@Document(collection = "templates", schemaVersion= "1.0")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Template {

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    private String format;
    private List<TemplateSheet> sheets;

}
