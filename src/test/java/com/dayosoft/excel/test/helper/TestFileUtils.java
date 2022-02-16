package com.dayosoft.excel.test.helper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileUtils {

    public static String readJsonFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
