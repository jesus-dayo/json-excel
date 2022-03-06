package com.dayosoft.excel.repository;

import com.dayosoft.excel.model.Template;
import io.jsondb.JsonDBTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TemplateRepository {

    private final JsonDBTemplate jsonDBTemplate;

    @PostConstruct
    public void createCollection(){
        if(!jsonDBTemplate.collectionExists(Template.class)) {
            jsonDBTemplate.createCollection(Template.class);
        }
    }

    public List<Template> list(){
        return jsonDBTemplate.findAll(Template.class);
    }

    public void add(Template template){
        jsonDBTemplate.insert(template);
    }

    public void update(String name, Template template){
        final Template foundTemplate = find(name);
        delete(foundTemplate);
        add(template);
    }

    public void delete(Template template){
        jsonDBTemplate.remove(template, Template.class);
    }

    public Template find(String name){
        return jsonDBTemplate.findById(name, Template.class);
    }
}
