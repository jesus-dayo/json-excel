package com.dayosoft.excel.template.reader;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.type.ExcelReportType;
import com.jsoniter.output.JsonStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class ExcelTemplateReader {

    public String excelToJsonTemplate(InputStream file, ExcelReportType reportType) throws IOException, InvalidFormatException {
        if(ExcelReportType.EXCEL_2007 == reportType){
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
            final Iterator<Sheet> sheetIterator = xssfWorkbook.iterator();
            List<com.dayosoft.excel.model.Sheet> sheets = new ArrayList<>();
            int sheetIndex = 0;
            while(sheetIterator.hasNext()){
                final Sheet sheet = sheetIterator.next();
                Map<String, String> styles = new HashMap<>();
                sheets.add(com.dayosoft.excel.model.Sheet.builder()
                        .index(sheetIndex)
                        .name(sheet.getSheetName())
                        .styles(styles)
                        .build());
                sheetIndex++;
            }
            return JsonStream.serialize(Template.builder()
                    .name("Add Unique Template Name")
                            .description("Add description")
                    .format(ExcelReportType.EXCEL_2007.getExtension()).sheets(sheets).build());
        } else{

            return "";
        }
    }


}
