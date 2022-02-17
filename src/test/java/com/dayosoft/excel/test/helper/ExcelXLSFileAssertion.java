package com.dayosoft.excel.test.helper;

import com.dayosoft.excel.test.helper.assertion.XLSAssertions;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelXLSFileAssertion {

    public static final void isEqual(File excel1, File excel2) throws IOException, InvalidFormatException {
        HSSFWorkbook workbook1 = new HSSFWorkbook(new FileInputStream(excel1));
        HSSFWorkbook workbook2 = new HSSFWorkbook(new FileInputStream(excel2));

        XLSAssertions.assertEqualNumOfSheets
                .and(XLSAssertions.assertEqualSheetColumnsAndRows)
                .test(workbook1, workbook2);
    }

}
