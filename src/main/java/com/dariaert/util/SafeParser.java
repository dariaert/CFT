package com.dariaert.util;

public class SafeParser {

    public static boolean isInteger(String s) {
        try {
            new java.math.BigInteger(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            Double.parseDouble(s);
            return s.contains(".") || s.toUpperCase().contains("E");
        } catch (Exception e) {
            return false;
        }
    }

}
