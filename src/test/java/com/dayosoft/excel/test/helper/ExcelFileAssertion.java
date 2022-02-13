package com.dayosoft.excel.test.helper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileAssertion {

    public static final void isEqual(File excel1, File excel2) throws IOException, InvalidFormatException {
        HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(excel1));
        HSSFWorkbook workbook2 = new HSSFWorkbook(new FileInputStream(excel2));

        if(workbook1.getNumberOfSheets() != workbook2.getNumberOfSheets()){
            Assertions.fail("number of sheets are not equal");
            return;
        }
    }

}
