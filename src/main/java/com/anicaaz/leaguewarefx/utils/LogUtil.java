package com.anicaaz.leaguewarefx.utils;

/**
 * 打日志
 * 目前是向终端输出
 * @author anicaa
 */
public class LogUtil {

    /**
     * 向控制台输出类名，方法名，消息。
     * @param className 类名
     * @param methodName 方法名
     * @param msg 消息
     */
    public static void log(String className, String methodName, String msg) {
        System.out.printf("[%s类的%s方法]：%s\n", className, methodName, msg);
    }
}
