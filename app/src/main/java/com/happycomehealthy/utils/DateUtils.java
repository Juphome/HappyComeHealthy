package com.happycomehealthy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * Created by shixinshan on 2018/5/20.
 */

public class DateUtils {

    /**
     * GMT(格林威治标准时间)转换当前北京时间
     * 比如：1526217409 -->2018/5/13 21:16:49 与北京时间相差8个小时，调用下面的方法，是在1526217409加上8*3600秒
     * @param GMT 秒单部位
     * @return
     */
    public static String stampToDate(String GMT) {

        long lt = Long.parseLong(GMT)+8*3600;
        String res = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            res = simpleDateFormat.format(lt*1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
    /**
     * 输入：String date = “1527933378194”-->2018-06-02T09:56:18.194Z
     * 输出：2016-08-16 00:00:00
     * @param src
     * @return
     */
    public static String UTCStringtODefaulString2(String src) {
        try{
            long data = Long.parseLong(src);
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            String UTCString = utcFormat.format(data);
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = utcFormat.parse(UTCString);
            return defaultFormat.format(date);
        } catch(ParseException pe)
        {
            pe.printStackTrace();
            return null;
        }
    }
    /**
     * 输入：String date = “1527933378194”-->2018-06-02T09:56:18.194Z
     * 输出：2016-08-16 00:00:00
     * @param src
     * @return
     */
    public static String UTCStringtODefaulString3(String src) {
        try{
            long data = Long.parseLong(src);
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            String UTCString = utcFormat.format(data);
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-M-d");
            Date date = utcFormat.parse(UTCString);
            return defaultFormat.format(date);
        } catch(ParseException pe)
        {
            pe.printStackTrace();
            return null;
        }
    }

    /**
     * 输入：String date = “2016-08-15T16:00:00.000Z”
     * 输出：2016-08-16 00:00:00
     * @param UTCString
     * @return
     */
    public static String UTCStringtODefaultString(String UTCString) {
        try{
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = utcFormat.parse(UTCString);
            return defaultFormat.format(date);
        } catch(ParseException pe)
        {
            pe.printStackTrace();
            return null;
        }
    }
    /**
     * 获取格林威治时间(1970年至今的秒数)
     */
    public static long getGMTime1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/Greenwich"));
        String format = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date gmDate = null;
        try {
            gmDate = sdf1.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gmDate.getTime() / 1000;
    }

    /**
     * 获取格林威治时间 即1970年至今的秒数
     */
    public static long getGMTime2() {
        int round = (int) (System.currentTimeMillis() / 1000);
        return round;
    }

    /**
     * 获取时间HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //"\\s"以空格截断
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 获取当前时间的年月日时分秒
     * @return
     */
    public static String current() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
    }

    /**
     * 获取当前时间的年月日时分秒
     * @return
     */
    public static String getCurrentYear_Month() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        return year + "年" + month + "月" ;
    }

    /**
     * 得到昨天的日期
     *
     * @return
     */
    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到今天的日期
     *
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }
    /**
     * 得到今天的日期
     *
     * @return
     */
    public static String getCurrentYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 得到明天的日期
     *
     * @return
     */
    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = sdf.format(calendar.getTime());
        return tomorrow;
    }

    /**
     * 时间转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToStr1(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间转化为时间(几点)
     *
     * @param time
     * @return
     */
    public static String timeStampToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(time * 1000);
        return date;
    }

    /**
     * 将日期格式转化为时间(秒数)
     *
     * @param time
     * @return
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 将日期格式转化为时间(秒数)
     *
     * @param time
     * @return
     */
    public static long getString2Date(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time
     * @return
     */
    public static boolean judgeCurrTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(time);
            long t = date.getTime();
            long round = System.currentTimeMillis();
            if (t - round > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * 判断是否大于当前时间
     *
     * @param time
     * @return
     */
    public static boolean judgeCurrTime(long time) {
        long round = System.currentTimeMillis();
        if (time - round > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较后面的时间是否大于前面的时间
     *
     * @param
     * @return
     */
    public static boolean judgeTime2Time(String time1, String time2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            //转化为时间
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            //获取秒数作比较
            long l1 = date1.getTime() / 1000;
            long l2 = date2.getTime() / 1000;
            if (l2 - l1 > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 得到日期 yyyy-MM-dd
     *
     * @param time
     * @return
     */
    public static String formatDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(time * 1000);
        return date;
    }

    /**
     * 得到时间 HH:mm:ss
     *
     * @param timeStamp
     * @return
     */
    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 将一个时间转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        return time / 60 + "";
    }

    /**
     * 获得当前时间差
     *
     * @param timeStamp
     * @return
     */
    public static int nowCurrentTime(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = timeStamp - curTime;
        return (int) time;
    }


    /**
     * 获取当前的时 -->flag==true
     * 获取当前的分 -->flag==false
     *
     * @return
     */
    public static String nowCurrentPoint(boolean flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(System.currentTimeMillis());
        String[] split = date.split(":");
        String hour = null;
        String minute = null;
        if (flag) {
            if (split.length > 1) {
                hour = split[0];
                return hour;
            }
        } else {
            if (split.length > 1) {
                minute = split[1];
                return minute;
            }
        }
        return null;
    }

    /**
     * 将标准时间格式HH:mm:ss化为当前的时间差值
     *
     * @param str
     * @return
     */
    public static String StandardFormatStr(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(str);
            long timeStamp = d.getTime();

            long curTime = System.currentTimeMillis() / (long) 1000;
            long time = curTime - timeStamp / 1000;
            return time / 60 + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }


    /**
     * 日期变量转成对应的星期字符串
     *
     * @param date
     * @return
     */

    public static final int WEEKDAYS = 7;
    //星期字符数组
    public static String[] WEEK = {"周日", "周一", "周二", "周三",
            "周四", "周五", "周六"};

    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

}
