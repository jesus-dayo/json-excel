package com.dayosoft.excel.util;

import com.dayosoft.excel.type.ExcelReportType;
import org.apache.commons.compress.utils.FileNameUtils;

import java.io.File;

public final class FileUtil {

    public static boolean isExcel2007(String filename){
        return ExcelReportType.EXCEL_2007.getExtension().equalsIgnoreCase(FileNameUtils.getExtension(filename));
    }

}
