package com.dayosoft.excel.rest;

import com.dayosoft.excel.config.ReportConfig;
import com.dayosoft.excel.generator.JsonExcelGenerator;
import com.dayosoft.excel.model.ReportPerformance;
import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.repository.ReportPerformanceRepository;
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
import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportRestController {

    private final TemplateRepository templateRepository;
    private final JsonExcelGenerator jsonExcelGenerator;
    private final ReportConfig reportConfig;
    private final ReportPerformanceRepository reportPerformanceRepository;

    @PostMapping("/generate/{name}")
    public ResponseEntity<Resource> generate(@PathVariable String name,
                                             @RequestBody String json) throws IOException {
        final File file = runReport(name, json);
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


    @PostMapping("/performance/generate/{name}/{count}")
    public ReportPerformance performanceTest(@PathVariable String name,
                                                    @PathVariable Integer count,
                                             @RequestBody String json) throws IOException {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < count; i++) {
            runReport(name, json);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTimeInMS = stopTime - startTime;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        final long MEGABYTE = 1024L * 1024L;
        long memory = runtime.totalMemory() - runtime.freeMemory();
        final ReportPerformance reportPerformance = ReportPerformance.builder()
                .elapsedTimeInMS(elapsedTimeInMS)
                .executedDateTime(LocalDateTime.now())
                .memoryInBytes(memory)
                .memoryInMB(memory / MEGABYTE)
                .templateName(name)
                .reportCount(count)
                .data(json)
                .build();
        reportPerformanceRepository.add(reportPerformance);

        return reportPerformance;
    }

    @GetMapping("/performance/{name}")
    public List<ReportPerformance> performanceByName(@PathVariable String name) {
        return reportPerformanceRepository.findByName(name);
    }

    private File runReport(String name, String json) throws IOException {
        final Template template = templateRepository.find(name);
        final String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMYYhhmmss"));
        final JsonExcelRequest request = JsonExcelRequest.builder().template(template)
                .data(json)
                .reportType(ExcelReportType.findByExtension(template.getFormat()))
                .fileName(StringUtils.deleteWhitespace(name) + now)
                .directory(reportConfig.getDirectory())
                .build();
        return jsonExcelGenerator.generateReport(request);
    }

}
