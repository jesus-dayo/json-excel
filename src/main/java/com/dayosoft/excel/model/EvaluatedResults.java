package com.dayosoft.excel.model;

import com.dayosoft.excel.expression.renderer.CellRenderer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EvaluatedResults {

    private Object results;
    private CellRenderer cellRenderer;

}
