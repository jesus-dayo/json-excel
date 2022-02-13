package com.dayosoft.excel.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonExcelRequest {

    private String json;
    private String fileName;
    private String directory;

}
