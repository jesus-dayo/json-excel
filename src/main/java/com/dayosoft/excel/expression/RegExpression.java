package com.dayosoft.excel.expression;

public final class RegExpression {

    public static final String EXPRESSION = "\\$\\{(.*?)\\}";
    public static final String FUNC_EXPRESSION = "\\((.*?)\\)";
    public static final String COL_ARR_FUNC_EXPRESSION = "colArr" + FUNC_EXPRESSION;
    public static final String ROW_FUNC_EXPRESSION = "row" + FUNC_EXPRESSION;
    public static final String REF_FUNC_EXPRESSION = "ref" + FUNC_EXPRESSION;
    public static final String DIVIDE_FUNC_EXPRESSION = "divide" + FUNC_EXPRESSION;
    public static final String TOTAL_COL_FUNC_EXPRESSION = "totalCol" + FUNC_EXPRESSION;
    public static final String TOTAL_FUNC_EXPRESSION = "total" + FUNC_EXPRESSION;
    public static final String TOTAL_NEGATIVE_FUNC_EXPRESSION = "totalNegative" + FUNC_EXPRESSION;
    public static final String SUM_FUNC_EXPRESSION = "sum" + FUNC_EXPRESSION;
    public static final String TODAY_FUNC_EXPRESSION = "today" + FUNC_EXPRESSION;
    public static final String REGEX_FUNC_EXPRESSION = "regEx" + FUNC_EXPRESSION;
    public static final String OBJECT_EXPRESSION = "^([^#/(/)][0-9A-Za-z\\s:]+)$";
}
