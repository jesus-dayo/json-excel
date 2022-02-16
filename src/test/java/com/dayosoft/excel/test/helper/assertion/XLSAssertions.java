package com.dayosoft.excel.test.helper.assertion;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.function.BiPredicate;

public class XLSAssertions {

   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualNumOfSheets = (w1, w2) -> {
      org.junit.jupiter.api.Assertions.assertEquals(w1.getNumberOfSheets(), w2.getNumberOfSheets(), "number of sheets are not equal");
      return w1.getNumberOfSheets() == w2.getNumberOfSheets();
   };

   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualSheetNames = (w1, w2) ->{
      for(int sheetIndex = 0;sheetIndex < w1.getNumberOfSheets(); sheetIndex++){
         HSSFSheet sheet1 = w1.getSheetAt(sheetIndex);
         HSSFSheet sheet2 = w2.getSheetAt(sheetIndex);
         org.junit.jupiter.api.Assertions.assertEquals(sheet1.getSheetName(), sheet2.getSheetName(),"Sheets names are not equal");
         return sheet1.getSheetName().equals(sheet2.getSheetName());
      }
      return true;
   };

   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualSheetColumns = (w1, w2) ->{
      for(int sheetIndex = 0;sheetIndex < w1.getNumberOfSheets();sheetIndex++){
         HSSFSheet sheet1 = w1.getSheetAt(sheetIndex);
         HSSFSheet sheet2 = w2.getSheetAt(sheetIndex);

         Iterator<Row> rowInSheet1 = sheet1.rowIterator();
         Iterator<Row> rowInSheet2 = sheet2.rowIterator();

         while (rowInSheet1.hasNext()) {
            int cellCounts1 = rowInSheet1.next().getPhysicalNumberOfCells();
            int cellCounts2 = rowInSheet2.next().getPhysicalNumberOfCells();
            org.junit.jupiter.api.Assertions.assertEquals(cellCounts1, cellCounts2,"Sheets have different count of columns");
            if(cellCounts1 != cellCounts2){
               return false;
            }
         }
      }
      return true;
   };


}
