package com.dayosoft.excel.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Position {

    private int col;
    private int row;

}
