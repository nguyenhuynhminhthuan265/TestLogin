package com.test.tbcm.utils;

import com.google.gson.Gson;

import java.time.ZonedDateTime;

public class LoggerHelperUtils {
    private static final boolean IS_MODE_DEBUG  = true;
    public static void logger(String tag, String message){
        if (IS_MODE_DEBUG){
            ZonedDateTime currentDate = DateUtil.getCurrentDate();
            System.out.println("********************************************************************************************************************************************************");
            System.out.println("***   "+ tag + ":::: " +currentDate +" :::: " + message);
            System.out.println("********************************************************************************************************************************************************");
        }
    }

    public static void logger(String tag, Object message){
        if (IS_MODE_DEBUG){
            ZonedDateTime currentDate = DateUtil.getCurrentDate();
            System.out.println("********************************************************************************************************************************************************");
            System.out.println("***   "+ tag + ":::: " +currentDate +" :::: " + new Gson().toJson(message));
            System.out.println("********************************************************************************************************************************************************");
        }
    }
}
