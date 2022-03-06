package com.dayosoft.excel.expression.parser;

import com.dayosoft.excel.exception.InvalidExpressionException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ExpressionHelper {


    public static boolean isValidExpression(String value, String regEx){
        Pattern logEntry = Pattern.compile(regEx);
        Matcher matchPattern = logEntry.matcher(value);
        return matchPattern.find();
    }

    public static boolean isValidExpressions(String value, String ... regEx){
        return Arrays.stream(regEx).anyMatch(exp->isValidExpression(value, exp));
    }

    public static boolean isValidExpression(String value){
        return isValidExpressions(value, RegExpression.EXPRESSION,
                RegExpression.FIRST_FUNC_EXPRESSION,
                RegExpression.ROW_FUNC_EXPRESSION,
                RegExpression.REF_FUNC_EXPRESSION,
                RegExpression.DIVIDE_FUNC_EXPRESSION,
                RegExpression.COL_ARR_FUNC_EXPRESSION,
                RegExpression.TOTAL_COL_FUNC_EXPRESSION,
                RegExpression.TOTAL_FUNC_EXPRESSION,
                RegExpression.TOTAL_NEGATIVE_FUNC_EXPRESSION);
    }

    public static String extractStringFromExpression(String value, String regExpression) throws InvalidExpressionException {
        Pattern logEntry = Pattern.compile(regExpression);
        Matcher matchPattern = logEntry.matcher(value);

        if(!matchPattern.find()) {
           throw new InvalidExpressionException(value + " is not a valid expression");
        }
        return matchPattern.group(1);
    }

}
