package com.perseus.utopia.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Guoteng Jiang
 * @date 2023/7/17 20:54
 */
public class DateUtils {

    public static final String DATE = "yyyy-MM-dd";

    public static final String MONTH_DATE = "MM-dd";

    public static final String DATE_NUM = "yyyyMMdd";

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String CH_Y_M_R = "yyyy年MM月dd日 HH时mm分";

    public static final String DATE_TIMESTAMP = "yyyyMMddHHmmssSSSS";

    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String DATE_TIME_MILLS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String VOD_DATE = "yyyyMMddHHmmss";

    public static final String DAY = "天";

    public static final String HOUR = "小时";

    public static final String MINUTE = "分钟";

    public static final String SECOND = "秒";

    public static final String DATE_HOUR = "yyyyMMddHH";

    public static Date getDateFromString(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            System.out.println("dateFormat.parse error"+e);
        }
        return null;
    }

    public static String getFormatDateStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            System.out.println("dateFormat.parse error"+e);
        }
        return null;
    }

    public static String getChYMR(Date date) {
        return getFormatDateStr(date, CH_Y_M_R);
    }

    public static String getDateTime(Date date) {
        return getFormatDateStr(date, DATE_TIME);
    }

    public static int getDateNum(Date date) {
        String dateStr = getFormatDateStr(date, DATE_NUM);
        return Integer.parseInt(dateStr);
    }

    public static int getDateNumFromTimestamp(Long timestamp) {
        Date date = getDateFromTimestamp(timestamp);
        String dateStr = getFormatDateStr(date, DATE_NUM);
        return Integer.parseInt(dateStr);
    }

    /**
     * @param timestamp 毫秒级别 形如： 1629388672000
     */
    public static Date getDateFromTimestamp(Long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME);
        String d = dateFormat.format(timestamp);
        Date date = getDateFromString(d, DATE_TIME);
        return date;
    }

    public static Date getDateAfter(Date date, int dateAfter) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dateAfter);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Long getDateDiffMinutes(Date date1, Date date2) throws RuntimeException {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        return (d1 - d2) / (1000 * 60);
    }

    public static Long getDateDiffSeconds(Date date1, Date date2) throws RuntimeException {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        return (d1 - d2) / 1000;
    }

    public static Integer getDateDiff(String date1, String date2) throws RuntimeException {

        Date dateNum1 = DateUtils.getDateFromString(date1, DateUtils.DATE);
        Date dateNum2 = DateUtils.getDateFromString(date2, DateUtils.DATE);
        if (Objects.isNull(dateNum1) || Objects.isNull(dateNum2)) {
            throw new RuntimeException("日期转换异常");
        }
        Long d1 = dateNum1.getTime();
        Long d2 = dateNum2.getTime();
        long dayDiff = (d1 - d2) / TimeUnit.DAYS.toMillis(1);
        return (int) dayDiff;
    }

    public static boolean dayAfter(Date date1, Date date2) throws RuntimeException {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        long dayDiff = (d1 - d2) / TimeUnit.DAYS.toMillis(1);
        return dayDiff > 0;
    }

    public static boolean dayBefore(Date date1, Date date2) throws RuntimeException {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        long dayDiff = (d2 - d1) / TimeUnit.DAYS.toMillis(1);
        return dayDiff > 0;
    }

    public static boolean daySame(Date date1, Date date2) throws RuntimeException {
        Long d1 = date1.getTime();
        Long d2 = date2.getTime();
        long dayDiff = (d1 - d2) / TimeUnit.DAYS.toMillis(1);
        return dayDiff == 0;
    }

    public static Integer getDateDiffHours(String date1, String date2) {
        Date dateNum1 = DateUtils.getDateFromString(date1, DateUtils.YYYY_MM_DD_HH_MM);
        Date dateNum2 = DateUtils.getDateFromString(date2, DateUtils.YYYY_MM_DD_HH_MM);

        Long d1 = dateNum1.getTime();
        Long d2 = dateNum2.getTime();
        long hourDiff = (d1 - d2) / TimeUnit.HOURS.toMillis(1);
        return (int) hourDiff;
    }

    public static Date addHours(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date getDateBefore(Date date, int dateBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -dateBefore);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateTimeBefore(Date date, int dateBefore) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -dateBefore);
        return calendar.getTime();
    }

    public static Date getTimeBeforeDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    public static Date getDateHour(int hour, Date videoDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(videoDate);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getDateStrFromDateTimeStr(String dateTime) {
        return dateTime.substring(0, 10);
    }

    public static Date addTime(Date date, long time, TimeUnit timeUnit) {
        return new Date(date.getTime() + timeUnit.toMillis(time));
    }

    public static Date addYear(Date date, int delta) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, delta);
        return calendar.getTime();
    }

    public static Date getLastSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    public static Date getCurrentMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static boolean isDefault(Date date) {
        return date.getTime() == 0L;
    }

    public static Date transferString2Date(String s) {
        return getDateFromString(s, DATE);
    }

    public static LocalDate transferString2LocalDate(String s) {
        return transferDate2LocalDate(transferString2Date(s));
    }

    public static LocalDate transferDate2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String getOneDayBefore(String dateString) {
        Date date = getDateFromString(dateString, DATE);
        Date oneDayBefore = getDateBefore(date, 1);
        return getFormatDateStr(oneDayBefore, DATE);
    }

    /**
     * get specified days after the input date as a String with format yyyy-MM-dd
     * @param dateString
     * @return
     */
    public static String getDaysAfter(String dateString, int daysAfter) {
        Date date = getDateFromString(dateString, DATE);
        Date oneDayAfter = getDateAfter(date, daysAfter);
        return getFormatDateStr(oneDayAfter, DATE);
    }

    /**
     * 找到输入日期所在的周的周日
     * @param dateStr yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfWeek(String dateStr) {
        Date date = getDateFromString(dateStr, DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != 1) {
            c.add(Calendar.DATE, 8 - dayOfWeek);
        }
        return getFormatDateStr(c.getTime(), DATE);
    }

    /**
     * 找到输入日期所在的周的周日
     * @param dateStr yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getFirstDayOfWeek(String dateStr) {
        Date date = getDateFromString(dateStr, DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek > 2) {
            c.add(Calendar.DATE, -(dayOfWeek - 2));
        } else if (dayOfWeek < 2) {
            c.add(Calendar.DATE, -6);
        }
        return getFormatDateStr(c.getTime(), DATE);
    }

    /**
     * to judge if a date is in a date range.
     * Format: yyyy-MM-dd
     * @param dateStr 要判断的日期
     * @param startStr 日期范围开始
     * @param endStr 日期范围结束
     * @return
     */
    public static Boolean isInDateRange(String dateStr, String startStr, String endStr) {
        return dateStr.compareTo(startStr) >= 0 && dateStr.compareTo(endStr) <= 0;
    }

    /**
     * to judge if a date is in a datetime range.
     * Format: Date
     * @param date 要判断的date
     * @param start date范围开始
     * @param end date范围结束
     * @return
     */
    public static Boolean isInDateRange(Date date, Date start, Date end) {
        if (date.equals(start) || date.after(start)) {
            return date.equals(end) || date.before(end);
        }
        return false;
    }

    /**
     * to judge if a date is in a datetime range.
     * Format: Date
     * @param date 要判断的date
     * @param startStr date范围开始
     * @param endStr date范围结束
     * @return
     */
    public static Boolean isInDateRange(Date date, String startStr, String endStr) {
        Date start = getDateFromString(startStr, DATE_TIME);
        Date end = getDateFromString(endStr, DATE_TIME);
        if (date.equals(start) || date.after(start)) {
            return date.equals(end) || date.before(end);
        }
        return false;
    }

    /**
     * 获取一个时间的之前的十分钟整点
     * e.g.
     * 输入 2022-11-29 18:49:55
     * 输出 2022-11-29 18:40:00
     * @param date date
     * @return
     */
    public static Date findNaturalTenMinutesTimePointBeforeTheTime(Date date) {
        String dateStr = getFormatDateStr(date, DATE_TIME);
        assert dateStr != null;
        String outputStr = dateStr.substring(0, 15) + "0:00";
        return DateUtils.getDateFromString(outputStr, DATE_TIME);
    }

    /**
     * 获取指定日期的00:00:00对应的时间
     *
     * @param date date
     * @return 指定日期当天第一秒时间
     */
    public static Date getDateFirstSecondTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期23:59:59对应的时间
     *
     * @param date date
     * @return 指定日期当天最后一秒时间
     */
    public static Date getDateLastSecondTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的指定时分秒
     * e.g.
     * 输入date为（Date类型）2022-02-11 12:34:56
     * 输入time为（String类型）23:12:23
     * 输出时间为（Date类型）2022-02-11 23:12:23
     *
     * @param date date
     * @param time time
     * @return result
     */
    public static Date getSpecifiedTimeOfDate(Date date, String time) {
        String dateStr = getFormatDateStr(date, DateUtils.DATE_TIME);
        assert dateStr != null;
        String nineteenOClockOfTheDayStr = dateStr.substring(0, 11) + time;
        return getDateFromString(nineteenOClockOfTheDayStr, DATE_TIME);
    }
}
