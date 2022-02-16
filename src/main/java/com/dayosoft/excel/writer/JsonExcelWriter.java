package com.dayosoft.excel.writer;

import com.dayosoft.excel.request.JsonExcelRequest;

import java.io.File;
import java.io.IOException;

public interface JsonExcelWriter {

    File write(JsonExcelRequest jsonExcelRequest) throws IOException;

}
