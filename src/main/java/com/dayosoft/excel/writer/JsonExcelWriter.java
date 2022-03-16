package com.dayosoft.excel.writer;

import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.model.TemplateSheet;
import com.dayosoft.excel.request.JsonExcelRequest;
import com.dayosoft.excel.template.helper.TemplateHelper;
import com.dayosoft.excel.type.ExcelReportType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonExcelWriter {

    private final SheetWriter sheetWriter;

    public File write(JsonExcelRequest jsonExcelRequest, ExcelReportType excelReportType) throws IOException {
        Workbook wb;
        if (excelReportType == ExcelReportType.EXCEL_2003) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        Template template = jsonExcelRequest.getTemplate();
        final List<TemplateSheet> sheets = template.getSheets();
        TemplateHelper.fillDependencies(sheets);
        sheets.forEach(templateSheet -> sheetWriter.write(RenderRequest.builder()
                .workbook(wb)
                .templateSheet(templateSheet)
                .data(jsonExcelRequest.getData())
                .skipExpression(jsonExcelRequest.isSkipRendering())
                .template(template)
                .build()));

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" + jsonExcelRequest.getFileName() + "." + excelReportType.getExtension());
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        return file;
    }

}
