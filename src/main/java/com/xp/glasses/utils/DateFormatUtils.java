package com.xp.glasses.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;


/**
 * @author Mrxiong
 * @date 2020/01/10
 */
@SuppressWarnings("unused")
public class DateFormatUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);

    final static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate,Date bdate){
        try {
            smdate=sdf.parse(sdf.format(smdate));
            bdate=sdf.parse(sdf.format(bdate));
        }catch (Exception e){
            logger.error("dateFormatUtil:计算两个日期之前的天数报错:{}",e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * java.util.Date转换成java.sql.TimeStamp
     *
     * @param date 日期
     * @return TimeStamp对象
     */
    public static Timestamp dateToTimestamp(Date date) {
        Date newDate = new Date();
        if (date == null) {
            newDate = new Date();
        }
        return new Timestamp(newDate.getTime());
    }

    /**
     * 获取某两个时间点，按照1小时为间隔来分割的时间点
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getDatesBetweenTwoDateByHour(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.HOUR, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        lDate.remove(lDate.size()-1);
        return lDate;
    }


    /***
     *
     * @param startTeachingDate 要改变的时间
     * @param number 改变的天数
     * @return
     */
    public  static Date getNewDate(Date startTeachingDate,int number){
        // 创建“简体中文”的Locale
        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
        Calendar c = Calendar.getInstance();
        c.setTime(startTeachingDate);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+number);
        Date newDate = c.getTime();
        return newDate;
    }

    /***
     *
     * @param startTeachingDate 要改变的时间
     * @param number 改变的小时数据
     * @return
     */
    public  static Date getNewHour(Date startTeachingDate,int number){
        // 创建“简体中文”的Locale
        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
        Calendar c = Calendar.getInstance();
        c.setTime(startTeachingDate);
        int hour=c.get(Calendar.HOUR);
        c.set(Calendar.HOUR,hour+number);
        Date newDate = c.getTime();
        return newDate;
    }
    /***
     *获取当钱时间的起始值
     * @param date 要改变的时间
     * @return
     */
    public  static Date getNewHour(Date date){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sdf.format(date);
        Date parse=null;
        try {
             parse = sdf.parse(format);
        }catch (Exception e){
            logger.error("时间转换出错:{}",e);
            return null;
        }
        return parse;
    }


    /***
     *获取当钱时间前 或者后 一小时的最大值
     * @param date 要改变的时间
     * @return
     */
    public  static Date getNewHourDate(Date date,int hour,Boolean isEnd){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH");
        String format = sdf.format(date);
        Date parse=null;
        try {
            parse = sdf.parse(format);
            Date newHour = getNewHour(parse, hour);
            if(isEnd){
                parse=new Date(newHour.getTime() +60*60*1000-1000);
            }else{
                parse=newHour;
            }
        }catch (Exception e){
            logger.error("时间转换出错:{}",e);
            return null;
        }
        return parse;
    }


    /**
     *  获得某天最小时间 2017-10-15 00:00:00
     */
    public static Date getStartOfDay() {
        Date date=new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     * @return
     */
    public static Date getEndOfDay() {
        Date date=new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     *  获得某天最小时间 2017-10-15 00:00:00
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }



    /**
     *  获得某天最小时间搓 2017-10-15 00:00:00
     */
    public static long getStartOfDayTime(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        Date from = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return from.getTime();
    }

    /**
     * 获得某天最大时时间搓 2017-10-15 23:59:59
     * @param date
     * @return
     */
    public static long getEndOfDayTime(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        Date from = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return from.getTime();
    }

    /**
     * 按照格式解析时间数据
     * @param dateString
     * @return
     */
    public  static Date parseDate(String dateString) {
        try {
            long millis = Long.parseLong(dateString);
            return new Date(millis);
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return null;
        }
    }


    /**
     * 日期字符串
     * @param dateString
     * @return
     */
    public  static Date parseDateByStr(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static Date parseDateByStr(String dateString,String formatStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时函数
     * @param date
     * @return
     */
    public  static String formatDate(Date date) {
        return sdf.format(date);
    }
    /**
     * 格式化时函数
     * @param date
     * @return
     */
    public  static String formatDateByFmt(Date date,String formatStr) {
        SimpleDateFormat curr = new SimpleDateFormat(formatStr);
        return curr.format(date);
    }





    /**
     * 获取时间对应的值
     * @param date
     * @return
     */
    public  static Double getDateInteger(Date date){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        try {
            double v = Double.parseDouble(format);
            return v;
        }catch (Exception e){
            logger.error("时间转换出错:{}",e);
            return  null;
        }
    }
    /**
     * 获取时间对应的值
     * @param date
     * @return
     */
    public  static String getDateStr(Date date){
        String format = sdf.format(date);
        return  format;
    }


    /**
     * 计算两个时间差
     * @param beginTime 开始监视
     * @param endTime   结束时间
     * @return 返回时间差
     */

    public static Double timeDifferenceHourse(Date beginTime, Date endTime,Double interval) {
        if(beginTime==null || endTime==null){
            return null;
        }
        long diff = endTime.getTime() - beginTime.getTime();
        double hours = diff / interval;
        return hours;
    }


    /**
     * @param yearMonth 2019-02
     * @return
     */
    public static Date getBeginTime(String yearMonth) {
        String[] split = yearMonth.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        return getBeginTime(year,month);
    }

    /**
     * @param yearMonth 2019-02
     * @return
     */
    public static Date getEndTime(String yearMonth) {
        String[] split = yearMonth.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        return getEndTime(year,month);
    }

    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }


    /**
     * 获取所在日期是星期几
     * @param date
     * @return
     */
    public static Integer getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek==0){
            dayOfWeek=7;
        }
        return dayOfWeek;

        /*
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        int week =  0;
        switch (currSun){
             case "星期一": week=1; break;
             case "星期二": week=2; break;
             case "星期三": week=3; break;
             case "星期四": week=4; break;
             case "星期五": week=5; break;
             case "星期六": week=6; break;
             case "星期日": week=7; break;
             default: week=0;
        }
        return week;
        */
    }


    /**
     * 获取两个时间的间隔时间字符串
     * @param start
     * @param end
     * @return
     */
    public static String  intervalTime(Date start,Date end)  {
        if(start!=null && end!=null){
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 *60;
            long nm = 1000 * 60;
            long m = 1000 * 1;
            long diff = end.getTime() - start.getTime();
            long day = diff/nd;

            long extra_hour  = diff % nd;
            long hour = extra_hour/nh;

            long extra_minute  = extra_hour % nh;
            long minute = extra_minute/nm;

            long extra_second  = extra_minute%nm;
            long second = extra_second/m;

            if(day!=0){
               return    day+"天"+hour+"时"+minute+"分"+second+"秒";
            }
            if(hour!=0){
                return    hour+"时"+minute+"分"+second+"秒";
            }
            if(minute!=0){
                return    minute+"分"+second+"秒";
            }
            return     second+"秒";
        }
        return null;
    }

}
