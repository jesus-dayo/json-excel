package com.dayosoft.excel.test.helper.assertion;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExcelAssertions {

   public static final BiPredicate<Workbook, Workbook> assertEqualNumOfSheets = (w1, w2) -> {
      assertEquals(w1.getNumberOfSheets(), w2.getNumberOfSheets(), "number of sheets are not equal");
      return w1.getNumberOfSheets() == w2.getNumberOfSheets();
   };

   public static final BiPredicate<Workbook, Workbook> assertEqualMergedRegions = (w1, w2) -> {
      for (int sheetIndex = 0; sheetIndex < w1.getNumberOfSheets(); sheetIndex++) {
         Sheet sheet1 = w1.getSheetAt(sheetIndex);
         Sheet sheet2 = w2.getSheetAt(sheetIndex);
         final List<CellRangeAddress> mergedRegions1 = sheet1.getMergedRegions();
         final List<CellRangeAddress> mergedRegions2 = sheet2.getMergedRegions();
         if(mergedRegions1.size() != mergedRegions2.size()){
            fail("Merged regions are not equal "+mergedRegions1.size()+" vs "+mergedRegions2.size());
            return false;
         }
      }
      return true;
   };

   public static final BiPredicate<Workbook, Workbook> assertEqualSheetColumnsAndRows = (w1, w2) ->{
      for(int sheetIndex = 0;sheetIndex < w1.getNumberOfSheets();sheetIndex++){
         Sheet sheet1 = w1.getSheetAt(sheetIndex);
         Sheet sheet2 = w2.getSheetAt(sheetIndex);

         Iterator<Row> rowsInSheet1 = sheet1.rowIterator();
         Iterator<Row> rowsInSheet2 = sheet2.rowIterator();

         if(sheet1.getLastRowNum() != sheet2.getLastRowNum()){
            fail("Sheets have different count of rows , expected "+sheet1.getLastRowNum()+" but got "+sheet2.getLastRowNum());
            return false;
         }

         int row = 1;
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
               DataFormatter dataFormatter = new DataFormatter();
               String formattedCell1Str = dataFormatter.formatCellValue(cell1);
               String formattedCell2Str = dataFormatter.formatCellValue(cell2);
               if(!formattedCell1Str.equals(formattedCell2Str)){
                  fail("Cell are not equal, expected "+ formattedCell1Str +" but got "+ formattedCell2Str + " on cell "+cellIndex + " and row "+ row);
                  return false;
               }
            }
            row++;
         }
      }
      return true;
   };


}
