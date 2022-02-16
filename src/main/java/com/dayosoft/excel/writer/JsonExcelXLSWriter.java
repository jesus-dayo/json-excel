package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JsonExcelXLSWriter implements JsonExcelWriter {

    public File write(JsonExcelRequest jsonExcelRequest) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        sheet.

        File file = new File(jsonExcelRequest.getDirectory() +
                "/" +jsonExcelRequest.getFileName() +
                ".xls");
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        wb.close();
        return file;
    }

}
