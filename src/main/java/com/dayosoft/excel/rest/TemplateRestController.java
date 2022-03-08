package com.dayosoft.excel.rest;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.repository.TemplateRepository;
import com.dayosoft.excel.response.TemplateResponse;
import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Template> all() {
        return templateRepository.list();
    }

    @PostMapping("/upload")
    public Template handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "description", required = false) String description) throws IOException {
        return excelTemplateReader.excelToJsonTemplate(name, description,
                file.getInputStream(), FileUtil.isExcel2007(file.getOriginalFilename()) ?
                        ExcelReportType.EXCEL_2007 : ExcelReportType.EXCEL_2003);
    }

    @PostMapping
    public TemplateResponse add(@RequestBody Template template) {
        templateRepository.add(template);
        return TemplateResponse.builder().format(template.getFormat()).name(template.getName()).success(true).build();
    }

    @GetMapping("{name}")
    public ResponseEntity<Template> find(@PathVariable String name) {
        final Template template = templateRepository.find(name);
        if(template == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(template);
    }

    @DeleteMapping("{name}")
    public TemplateResponse delete(@PathVariable String name) {
        final Template template = templateRepository.find(name);
        templateRepository.delete(template);
        return TemplateResponse.builder().format(template.getFormat()).name(template.getName()).success(true).build();
    }
}
