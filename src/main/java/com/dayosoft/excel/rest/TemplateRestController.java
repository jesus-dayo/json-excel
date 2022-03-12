package com.dayosoft.excel.rest;

import com.dayosoft.excel.config.ReportConfig;
import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.repository.TemplateRepository;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.response.TemplateResponse;
import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateRestController {

    private final ExcelTemplateReader excelTemplateReader;
    private final TemplateRepository templateRepository;
    private final JsonExcelGenerator jsonExcelGenerator;
    private final ReportConfig reportConfig;

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

    @PostMapping("/download/{name}")
    public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {
        final Template template = templateRepository.find(name);
        final JsonExcelRequest request = JsonExcelRequest.builder().template(template)
                .reportType(ExcelReportType.findByExtension(template.getFormat()))
                .fileName(StringUtils.deleteWhitespace(name))
                .directory(reportConfig.getDirectory())
                .skipRendering(true)
                .build();
        final File file = jsonExcelGenerator.generateReport(request);
        Path path  = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName());

        return ResponseEntity.ok()
                .contentLength(file.length())
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
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
