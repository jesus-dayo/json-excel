package com.dayosoft.excel.expression.renderer;

import com.dayosoft.excel.model.MappedResults;
import com.dayosoft.excel.model.RenderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class TodayRenderer extends NonDataRelatedRenderer {

    @Override
    public void render(RenderRequest renderRequest, MappedResults mappedResults) {
        final List<String> results = mappedResults.getResults();
        renderRequest.getCell().setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern(results.get(0))));
        renderRequest.getTemplateColumn().setRendered(true);
    }
}
