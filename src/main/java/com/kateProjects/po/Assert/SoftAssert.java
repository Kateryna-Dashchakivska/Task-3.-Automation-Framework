package com.kateProjects.po.Assert;

public class SoftAssert {

    public static void assertTrueAppendResult(boolean condition, String message, StringBuilder result){
        if (!condition){
           result.append("\n" + message);
        }
    }

    public static void assertFalseAppendResult(boolean condition, String message, StringBuilder result){
        if (condition){
            result.append("\n" + message);
        }
    }
}