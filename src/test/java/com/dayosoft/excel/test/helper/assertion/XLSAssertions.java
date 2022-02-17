package com.dayosoft.excel.test.helper.assertion;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class XLSAssertions {

   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualNumOfSheets = (w1, w2) -> {
      assertEquals(w1.getNumberOfSheets(), w2.getNumberOfSheets(), "number of sheets are not equal");
      return w1.getNumberOfSheets() == w2.getNumberOfSheets();
   };

//   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualSheetNames = (w1, w2) ->{
//      for(int sheetIndex = 0;sheetIndex < w1.getNumberOfSheets(); sheetIndex++){
//         HSSFSheet sheet1 = w1.getSheetAt(sheetIndex);
//         HSSFSheet sheet2 = w2.getSheetAt(sheetIndex);
//         assertEquals(sheet1.getSheetName(), sheet2.getSheetName(),"Sheets names are not equal");
//         return sheet1.getSheetName().equals(sheet2.getSheetName());
//      }
//      return true;
//   };

   public static final BiPredicate<HSSFWorkbook, HSSFWorkbook> assertEqualSheetColumnsAndRows = (w1, w2) ->{
      for(int sheetIndex = 0;sheetIndex < w1.getNumberOfSheets();sheetIndex++){
         HSSFSheet sheet1 = w1.getSheetAt(sheetIndex);
         HSSFSheet sheet2 = w2.getSheetAt(sheetIndex);

         Iterator<Row> rowsInSheet1 = sheet1.rowIterator();
         Iterator<Row> rowsInSheet2 = sheet2.rowIterator();

         if(sheet1.getLastRowNum() != sheet2.getLastRowNum()){
            fail("Sheets have different count of rows , expected "+sheet1.getLastRowNum()+" but got "+sheet2.getLastRowNum());
            return false;
         }

         while (rowsInSheet1.hasNext()) {
            final Row rowInSheet1 = rowsInSheet1.next();
            int cellCounts1 = rowInSheet1.getPhysicalNumberOfCells();
            if(!rowsInSheet2.hasNext()){
               fail("There is no more rows available for 2nd excel file");
               return false;
            }
            final Row rowInSheet2 = rowsInSheet2.next();
            int cellCounts2 = rowInSheet2.getPhysicalNumberOfCells();
            if(cellCounts1 != cellCounts2){
               fail("Sheets have different count of columns");
               return false;
            }

            for(int cellIndex = 0; cellIndex < cellCounts1; cellIndex++) {
               final Cell cell1 = rowInSheet1.getCell(cellIndex);
               final Cell cell2 =  rowInSheet2.getCell(cellIndex);
               if(!cell1.getStringCellValue().equals(cell2.getStringCellValue())){
                  fail("Cell is not equal, expected "+cell1.getStringCellValue()+" but got "+ cell2.getStringCellValue());
                  return false;
               }
            }
         }
      }
      return true;
   };


}
