package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.sun.jna.platform.FileUtils;

import java.io.File;
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
            res = diffInHours % 24 + "小时" + diffInMinutes % 60 + "分前";
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
        int min = time / 60;
        int sec = time % 60;
        return min + "分钟" + sec + "秒";
    }

    public static String gameTimeConverter(double time) {
        int timeInt = (int) time;
        int min = timeInt / 60;
        int sec = timeInt % 60;
        String tempMin = String.valueOf(min);
        String tempSec = String.valueOf(sec);
        if (min < 10) {
            tempMin = "0" + tempMin;
        }
        if (sec < 10) {
            tempSec = "0" + tempSec;
        }
        return tempMin + tempSec;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        boolean res = FileUtil.checkIfFileExist(String.valueOf(LeagueWareFXStarter.class.getResource(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE).toString() + "1.png"));
        System.out.println(LeagueWareFXStarter.class.getResource("/com/anicaaz/leaguewarefx/assets/static/summonerSpellIcons/1.png"));
//        System.out.println(res);
        System.out.println(FileUtil.checkIfFileExist("file:/C:/Users/Administrator/Documents/leagueware-fx/target/classes/com/anicaaz/leaguewarefx/assets/static/summonerSpellIcons/1.png"));
    }
}
