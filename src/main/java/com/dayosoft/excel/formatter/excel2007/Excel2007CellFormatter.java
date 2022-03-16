package com.dayosoft.excel.formatter.excel2007;

import com.dayosoft.excel.formatter.Formatter;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.styles.StylesMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Excel2007CellFormatter implements Formatter {

    @Override
    public void format(RenderRequest renderRequest) {
        final Sheet sheet = renderRequest.getSheet();
        final Workbook workbook = sheet.getWorkbook();
        final Map<String, String> styles = renderRequest.getTemplateColumn().getStyles();
        if (!styles.isEmpty()) {
            CellStyle newCellStyle = workbook.createCellStyle();
            final Font font = workbook.createFont();
            newCellStyle.setFont(font);
            StylesMapper.applyStyles(workbook, newCellStyle, styles);
            renderRequest.getCell().setCellStyle(newCellStyle);
        }
        sheet.setColumnWidth(renderRequest.getCell().getColumnIndex(), renderRequest.getTemplateColumn().getColumnWidth());
    }
}
