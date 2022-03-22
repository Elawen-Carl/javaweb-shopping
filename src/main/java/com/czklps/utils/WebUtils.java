package com.czklps.utils;

public class WebUtils {
    private WebUtils(){}

    public static int parseInt(String value, Integer defaultValue){
        try{
            if(value==null||value.trim().equals("")) return defaultValue;
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static double parseDouble(String value, Double defaultValue){
        try{
            if(value==null||value.trim().equals("")) return defaultValue;
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
