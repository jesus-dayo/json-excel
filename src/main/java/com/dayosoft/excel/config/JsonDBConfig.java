package com.dayosoft.excel.config;

import io.jsondb.JsonDBTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JsonDBConfig {

    private JsonDBTemplate jsonDBTemplate;

    @PostConstruct
    public void initDB(){
        jsonDBTemplate = new JsonDBTemplate("/tmp/dbFiles", "com.dayosoft.excel.model");
    }

    @Bean
    public JsonDBTemplate jsonDBTemplate(){
        return jsonDBTemplate;
    }

}
