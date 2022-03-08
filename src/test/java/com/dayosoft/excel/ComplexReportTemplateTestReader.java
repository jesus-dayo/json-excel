package com.dayosoft.excel;

import com.dayosoft.excel.model.Template;
import com.dayosoft.excel.template.reader.ExcelTemplateReader;
import com.dayosoft.excel.type.ExcelReportType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ComplexReportTemplateTestReader {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ExcelTemplateReader excelTemplateReader = new ExcelTemplateReader();
        File sampleExcel = new File("src/test/resources/complex/Complex_template.xlsx");
        final FileInputStream excelFileStream = new FileInputStream(sampleExcel);

        final Template template = excelTemplateReader.excelToJsonTemplate("test","test",excelFileStream, ExcelReportType.EXCEL_2007);

        System.out.println(template);
    }

}
