package com.dayosoft.excel.rest;

import com.dayosoft.excel.config.ReportConfig;
import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.repository.TemplateRepository;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.type.ExcelReportType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportRestController {
    private final TemplateRepository templateRepository;
    private final JsonExcelGenerator jsonExcelGenerator;
    private final ReportConfig reportConfig;

    @PostMapping("/generate/{name}")
    public ResponseEntity<Resource> generate(@PathVariable String name,
                                             @RequestBody String json) throws IOException {
        final Template template = templateRepository.find(name);

        final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMYYhhmmss"));
        final JsonExcelRequest request = JsonExcelRequest.builder().template(template)
                .data(json)
                .reportType(ExcelReportType.findByExtension(template.getFormat()))
                .fileName(StringUtils.deleteWhitespace(name) + now)
                .directory(reportConfig.getDirectory())
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

}
