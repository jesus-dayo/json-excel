package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.*;
import com.dayosoft.excel.util.CustomCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TodayRenderer extends CellRenderer<String> {

    @Override
    public void render(Cell cell, String type, TemplateColumn templateColumn, String value, String data, String key, List<DelayedRender> delayedRenders) {
        templateColumn.setRendered(true);
        cell.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern(value)));
    }
}
