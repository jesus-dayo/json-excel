package com.dayosoft.excel.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomCellUtilTest {

    @Test
    void givenColPosOrAddress_whenGetPosition_shouldReturnPos(){
        final String sampleAddress = "C2";
        final String sampleCommaPos = "4,1";
        assertEquals(4, CustomCellUtil.getPosition(sampleCommaPos).get().getRow());
        assertEquals(1, CustomCellUtil.getPosition(sampleCommaPos).get().getCol());
        assertEquals(1, CustomCellUtil.getPosition(sampleAddress).get().getRow());
        assertEquals(2, CustomCellUtil.getPosition(sampleAddress).get().getCol());
    }

    @Test
    void givenColPosOrAddress_whenGetPositionRange_shouldReturnPosRange(){
        final String sampleCommaDelPos = "4,1,5,1";
        final String sampleCommaDelAddress = "B5,B6";

        assertEquals(4, CustomCellUtil.getPositionRange(sampleCommaDelPos).get().getStart().getRow());
        assertEquals(1, CustomCellUtil.getPositionRange(sampleCommaDelPos).get().getStart().getCol());
        assertEquals(5, CustomCellUtil.getPositionRange(sampleCommaDelPos).get().getEnd().getRow());
        assertEquals(1, CustomCellUtil.getPositionRange(sampleCommaDelPos).get().getEnd().getCol());
        assertEquals(4, CustomCellUtil.getPositionRange(sampleCommaDelAddress).get().getStart().getRow());
        assertEquals(1, CustomCellUtil.getPositionRange(sampleCommaDelAddress).get().getStart().getCol());
        assertEquals(5, CustomCellUtil.getPositionRange(sampleCommaDelAddress).get().getEnd().getRow());
        assertEquals(1, CustomCellUtil.getPositionRange(sampleCommaDelAddress).get().getEnd().getCol());
    }

    @Test
    void givenStrDate_whenIsValid_shouldReturnTrue(){
        String given = "2022-03-08T16:00:00.000+00:00";

        final boolean actual = CustomCellUtil.isValidDate(given);

        assertTrue(actual);
    }

}