package com.dayosoft.excel.test.helper;

import com.dayosoft.excel.test.helper.assertion.ExcelAssertions;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileAssertion {

    public static final void isExcel2004Equal(File excel1, File excel2) throws IOException, InvalidFormatException {
        HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(excel1));
        HSSFWorkbook workbook2 = new HSSFWorkbook(new FileInputStream(excel2));

        ExcelAssertions.assertEqualNumOfSheets
                .and(ExcelAssertions.assertEqualSheetColumnsAndRows)
                .and(ExcelAssertions.assertEqualMergedRegions)
                .test(workbook1, workbook2);
    }

    public static final void isExcel2007Equal(File excel1, File excel2) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook1 = new XSSFWorkbook(excel1);
        XSSFWorkbook workbook2 = new XSSFWorkbook(excel2);

        ExcelAssertions.assertEqualNumOfSheets
                .and(ExcelAssertions.assertEqualSheetColumnsAndRows)
                .and(ExcelAssertions.assertEqualMergedRegions)
                .test(workbook1, workbook2);
    }

}
