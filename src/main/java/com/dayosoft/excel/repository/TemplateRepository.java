package com.dayosoft.excel.repository;

import com.dayosoft.excel.model.Template;
import io.jsondb.JsonDBTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TemplateRepository {

    private final JsonDBTemplate jsonDBTemplate;

    public List<Template> list(){
        return jsonDBTemplate.findAll(Template.class);
    }

    public void add(Template template){
        jsonDBTemplate.createCollection(Template.class);
        jsonDBTemplate.insert(template);
    }
}
