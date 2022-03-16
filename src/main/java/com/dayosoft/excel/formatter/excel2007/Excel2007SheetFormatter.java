package com.dayosoft.excel.formatter.excel2007;

import com.dayosoft.excel.formatter.Formatter;
import com.dayosoft.excel.model.RenderRequest;
import com.dayosoft.excel.model.TemplateMerge;
import com.dayosoft.excel.model.TemplateSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class Excel2007SheetFormatter implements Formatter {

    @Override
    public void format(RenderRequest renderRequest) {
        final Sheet wbSheet = renderRequest.getSheet();
        final TemplateSheet templateSheet = renderRequest.getTemplateSheet();
        wbSheet.setDisplayGridlines(templateSheet.isDisplayGridlines());
        wbSheet.setPrintGridlines(templateSheet.isPrintGridlines());
        wbSheet.setFitToPage(templateSheet.isFitToPage());
        wbSheet.setDisplayGuts(templateSheet.isDisplayGuts());

        final List<TemplateMerge> templateMerges = templateSheet.getMergeRegions();
        templateMerges.forEach(templateMerge -> {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(
                    templateMerge.getStart().getRow(),
                    templateMerge.getEnd().getRow(),
                    templateMerge.getStart().getCol(),
                    templateMerge.getEnd().getCol());
            wbSheet.addMergedRegion(cellRangeAddress);
        });
    }
}
