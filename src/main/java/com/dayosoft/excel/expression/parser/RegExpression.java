package com.dayosoft.excel.expression.parser;

public final class RegExpression {

    public static final String EXPRESSION = "\\$\\{(.*?)\\}";
    public static final String FUNC_EXPRESSION = "\\((.*?)\\)";
    public static final String FIRST_FUNC_EXPRESSION = "first"+FUNC_EXPRESSION;
    public static final String COL_ARR_FUNC_EXPRESSION = "colArr"+FUNC_EXPRESSION;
    public static final String ROW_FUNC_EXPRESSION = "row"+FUNC_EXPRESSION;
    public static final String OBJECT_EXPRESSION = ":";

}