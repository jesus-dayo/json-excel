package com.dayosoft.excel.model;

import lombok.Data;

import java.util.List;

@Data
public class TemplateSheet {

    private Integer index;
    private String name;
    private List<TemplateRow> rows;
    private List<TemplateMerge> mergeRegions;
    private boolean isPrintGridlines;
    private boolean fitToPage;
    private boolean displayGuts;
    private boolean displayGridlines;
    private int defaultColumnWidth;
}
