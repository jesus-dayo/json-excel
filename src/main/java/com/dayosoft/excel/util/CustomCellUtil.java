package com.dayosoft.excel.util;

import com.dayosoft.excel.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class CustomCellUtil {

    public static void setCellValue(Cell cell, Object value, String type){
        switch (type){
            case "string":{
                if(value == null || value.equals("null")){
                    cell.setCellValue(StringUtils.EMPTY);
                } else {
                    cell.setCellValue(value.toString());
                }
                break;
            }
            case "decimal":
            case "double":{
                if(value == null || value.equals("null")){
                    cell.setCellValue(0.0);
                } else {
                    cell.setCellValue(Double.parseDouble(value.toString()));
                }
                break;
            }
            default:{
                log.error("Unable to identify type " + type);
            }
        }
    }

    public static void setCellValue(Cell cell, Object value){
        if(value == null || value.equals("null")){
            cell.setCellValue(StringUtils.EMPTY);
            return;
        }
        if (value instanceof String) {
            if (isValidDate(value.toString())){
                cell.setCellValue(getDate(value.toString()));
            } else {
                cell.setCellValue(value.toString());
            }
        } else if (value instanceof Integer) {
            cell.setCellValue(Integer.parseInt(value.toString()));
        } else if (value instanceof Double) {
            cell.setCellValue(Double.parseDouble(value.toString()));
        }
        else {
            log.error("Unable to identify type " + value);
        }
    }

    public static boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Date getDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static String getCellAddress(Integer rowNum, Integer col){
        return new CellReference(rowNum,col).formatAsString();
    }

    public static AddressResult getAddressResults(Integer row,
                                                  Integer col,
                                                  TemplateSheet templateSheet) {
        final Optional<TemplateRow> foundRow = templateSheet.getRows().stream().filter(r -> r.getOriginalRowNum() == row).findFirst();
        if(foundRow.isPresent()){
            final TemplateRow rowRef = foundRow.get();
            final Optional<TemplateColumn> foundColumn = rowRef.getColumns().stream().filter(c -> c.getOriginalCol() == col).findFirst();
            if(foundColumn.isPresent()){
                final TemplateColumn colRef = foundColumn.get();
                if(colRef.isRendered()){
                    String address = new CellReference(rowRef.getRowNum(),colRef.getCol()).formatAsString();
                    return AddressResult.builder()
                            .lastRow(colRef.getLastRowNum())
                            .address(address)
                            .build();
                }
            }
        }
        return null;
    }

    public static Optional<TemplatePosition> getPosition(String value){
        if (value.contains(",")) {
            final String[] split = value.split(",");
            final Integer row = Integer.parseInt(split[0]);
            final Integer col = Integer.parseInt(split[1]);
            return Optional.of(TemplatePosition.builder().row(row).col(col).build());
        }

        final CellAddress cellAddress = new CellAddress(value);
        if(cellAddress != null){
            return Optional.of(TemplatePosition.builder().row(cellAddress.getRow()).col(cellAddress.getColumn()).build());
        }
        return Optional.empty();
    }

    public static Optional<TemplateRange> getPositionRange(String value){
        if (!value.contains(",")) {
            return Optional.empty();
        }
        final String[] split = value.replace(" ","").split(",");
        if(split.length == 4){
            final Integer row1 = Integer.parseInt(split[0]);
            final Integer col1 = Integer.parseInt(split[1]);
            final Integer row2 = Integer.parseInt(split[2]);
            final Integer col2 = Integer.parseInt(split[3]);
            return Optional.of(TemplateRange.builder().start(TemplatePosition.builder().row(row1).col(col1).build())
                    .end(TemplatePosition.builder().row(row2).col(col2).build()).build());
        } else if (split.length == 2){
            final CellAddress start = new CellAddress(split[0]);
            final CellAddress end = new CellAddress(split[1]);

            if(start == null || end == null){
                return Optional.empty();
            }

            return Optional.of(TemplateRange.builder()
                    .start(TemplatePosition.builder().row(start.getRow()).col(start.getColumn()).build())
                    .end(TemplatePosition.builder().row(end.getRow()).col(end.getColumn()).build()).build());
        }


        return Optional.empty();
    }

}
