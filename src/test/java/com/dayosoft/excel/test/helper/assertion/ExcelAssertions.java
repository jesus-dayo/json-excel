package com.dayosoft.excel.test.helper.assertion;

import com.google.common.collect.ComparisonChain;
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
               final Cell cell2 = rowInSheet2.getCell(cellIndex);
               DataFormatter dataFormatter = new DataFormatter();
               String formattedCell1Str = dataFormatter.formatCellValue(cell1);
               String formattedCell2Str = dataFormatter.formatCellValue(cell2);
               if (!formattedCell1Str.equals(formattedCell2Str)) {
                  fail("Cell are not equal, expected " + formattedCell1Str + " but got " + formattedCell2Str + " on cell " + cellIndex + " and row " + row);
                  return false;
               }

               if (cell1 == null && cell2 == null) {
                  continue;
               } else if ((cell1 == null) || ((cell1 != null) && (cell2 == null))) {
                  fail("1 of the cells are null, both should be equal , expected cell 1" + cell1 + " but got cell 2 " + cell2 + " on cell " + cellIndex + " and row " + row);
                  return false;
               }

               final CellStyle cellStyle1 = cell1.getCellStyle();
               final CellStyle cellStyle2 = cell2.getCellStyle();


               int comparisonResult = compareStyles(cellStyle1, cellStyle2);
               if (comparisonResult < 0) {
                  fail("Cell styles are not equal,  on cell " + cellIndex + " and row " + row);
                  return false;
               }
            }
            row++;
         }
      }
      return true;
   };

   private static int compareStyles(CellStyle cellStyle1, CellStyle cellStyle2) {
      return ComparisonChain.start()
              .compare(cellStyle1.getLeftBorderColor(), cellStyle2.getLeftBorderColor())
              .compare(cellStyle1.getBorderLeft().getCode(), cellStyle2.getBorderLeft().getCode())
              .compare(cellStyle1.getAlignment().getCode(), cellStyle2.getAlignment().getCode())
              .compare(cellStyle1.getDataFormat(), cellStyle2.getDataFormat())
              .compare(cellStyle1.getBorderRight().getCode(), cellStyle2.getBorderRight().getCode())
              .compare(cellStyle1.getRightBorderColor(), cellStyle2.getRightBorderColor())
              .result();
   }


}
