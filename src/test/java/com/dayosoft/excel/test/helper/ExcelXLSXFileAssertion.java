package com.dayosoft.excel.test.helper;

import com.dayosoft.excel.test.helper.assertion.ExcelAssertions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelXLSXFileAssertion {

    public static final void isEqual(File excel1, File excel2) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(excel1));
        XSSFWorkbook workbook2 = new XSSFWorkbook(new FileInputStream(excel2));

        ExcelAssertions.assertEqualNumOfSheets
                .and(ExcelAssertions.assertEqualSheetColumnsAndRows)
                .test(workbook1, workbook2);
    }

}
