package com.dayosoft.excel.expression;

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

    public static boolean isValidExpression(Object value){
        if (value == null) {
            return false;
        }
        final boolean validExpressions = isValidExpressions(value.toString(), RegExpression.EXPRESSION);
        return validExpressions;
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
