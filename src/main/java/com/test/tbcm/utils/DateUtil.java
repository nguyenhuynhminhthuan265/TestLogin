package com.test.tbcm.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String FORMAT_ONLY_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIMEZONE_GMT = "GMT+7";
    public static final String FORMAT_DATE_HOUR_MINUTE = "dd/MM/yyyy - hh:mm";

    public static ZonedDateTime getCurrentDate() {
        return ZonedDateTime.now();
    }

    public static ZonedDateTime toZoneDateTime(Date date) {
        try {
            return ZonedDateTime.ofInstant(date.toInstant(),
                    ZoneId.systemDefault());
        } catch (Exception e) {

        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        try {
            return LocalDateTime.ofInstant(date.toInstant(),
                    ZoneId.systemDefault());
        } catch (Exception e) {

        }
        return null;
    }

    public static ZonedDateTime convertStringToZonedDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        return zonedDateTime;
    }

    public static String randomNow() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(now);
    }

    public static String convertZonedDateTimeToString(ZonedDateTime date) {
        return DateTimeFormatter.ofPattern(FORMAT_DATE_HOUR_MINUTE).format(date);
    }

}
