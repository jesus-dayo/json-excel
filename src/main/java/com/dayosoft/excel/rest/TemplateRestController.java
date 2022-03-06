package com.dayosoft.excel.rest;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.repository.TemplateRepository;
import com.dayosoft.excel.response.TemplateResponse;
import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.util.FileUtil;
import com.jsoniter.JsonIterator;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateRestController {

    private final ExcelTemplateReader excelTemplateReader;
    private final TemplateRepository templateRepository;

    @GetMapping
    public List<Template> all(){
        return templateRepository.list();
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        return excelTemplateReader.excelToJsonTemplate(file.getInputStream(), FileUtil.isExcel2007(file.getOriginalFilename())? ExcelReportType.EXCEL_2007: ExcelReportType.EXCEL_2003);
    }

    @PostMapping
    public TemplateResponse add(@RequestBody String json){
        Template template = JsonIterator.deserialize(json, Template.class);
        templateRepository.add(template);
        return TemplateResponse.builder().format(template.getFormat()).name(template.getName()).success(true).build();
    }

    @DeleteMapping("{name}")
    public TemplateResponse delete(@PathVariable String name){
        final Template template = templateRepository.find(name);
        templateRepository.delete(template);
        return TemplateResponse.builder().format(template.getFormat()).name(template.getName()).success(true).build();
    }

    @PutMapping
    public TemplateResponse update(@PathVariable String name, @RequestBody String json){
        Template template = JsonIterator.deserialize(json, Template.class);
        templateRepository.update(name, template);
        return TemplateResponse.builder().format(template.getFormat()).name(template.getName()).success(true).build();
    }
}
