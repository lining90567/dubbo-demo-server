package com.ln.demo.common.util;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期时间工具类
 * @author Administrator
 *
 */
public final class DateTimeUtils {

    /**
     * 得到UTC日期时间
     * @param date
     * @param format
     * @return
     */
    public static Date getUTCDate(String date, String format) {
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(format);
        java.util.TimeZone utcZone = java.util.TimeZone.getTimeZone("UTC");
        simpleDateFormat.setTimeZone(utcZone);
        try {
            java.util.Date utcDate = simpleDateFormat.parse(date);
            return utcDate;
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 得到UTC日期时间
     * @param date
     * @return
     */
    public static Date getUTCDate(String date) {
		return getUTCDate(date, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
    
}
