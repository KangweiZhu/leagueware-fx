package com.anicaaz.leaguewarefx.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String dateDiffInDays(Date date) {
        Date current = new Date();
        long diffInMillies = current.getTime() - date.getTime(); // 时间差以毫秒计
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillies);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies);
        String res = diffInDays + "天前";
        if (diffInDays < 1) {
            res = diffInHours % 24 + "小时" + diffInMinutes % 60 + "分钟前";
        }
        /*String res = diffInDays + "天" +
        System.out.println(diffInDays + " days, ");
        System.out.println(diffInHours % 24 + " hours, ");
        System.out.println(diffInMinutes % 60 + " minutes, ");
        System.out.println(diffInSeconds % 60 + " seconds.");*/
        return res;
    }

    public static String dateDiffInMinute(Date date) {
        Date current = new Date();
        long diffInMillies = current.getTime() - date.getTime(); // 时间差以毫秒计
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillies);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies);
        String res = diffInDays + "天前";
        if (diffInHours % 24 >= 1) {
            res = diffInHours % 24 + "小时" + diffInMinutes % 60 + "分钟";
        } else {
            res = diffInMinutes % 60 + "分钟";
        }
        /*String res = diffInDays + "天" +
        System.out.println(diffInDays + " days, ");
        System.out.println(diffInHours % 24 + " hours, ");
        System.out.println(diffInMinutes % 60 + " minutes, ");
        System.out.println(diffInSeconds % 60 + " seconds.");*/
        return res;
    }

    public static String durationCalculator(Integer time) {
        Integer min = time / 60;
        Integer sec = time % 60;
        return min + "分钟" + sec + "秒";
    }
}
