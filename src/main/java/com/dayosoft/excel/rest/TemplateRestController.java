package com.dayosoft.excel.rest;

import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import com.dayosoft.excel.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateRestController {

    private final ExcelTemplateReader excelTemplateReader;

    @GetMapping
    public List all(){
        return new ArrayList();
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        return excelTemplateReader.excelToJsonTemplate(file.getInputStream(), FileUtil.isExcel2007(file.getOriginalFilename())? ExcelReportType.EXCEL_2007: ExcelReportType.EXCEL_2003);
    }

}