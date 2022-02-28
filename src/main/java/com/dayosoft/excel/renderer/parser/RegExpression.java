package com.dayosoft.excel.renderer.parser;

public final class RegExpression {

    public static final String EXPRESSION = "\\$\\{(.*?)\\}";
    public static final String FUNC_EXPRESSION = "\\((.*?)\\)";
    public static final String FIRST_FUNC_EXPRESSION = "first"+FUNC_EXPRESSION;
    public static final String OBJECT_EXPRESSION = ":";


}
