package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.PlatformConstants;

/**
 *
 * 负责 系统 相关的工具类
 *
 * @author anicaazhu
 */
public class OSUtil {

    public static String checkPlatform() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("This is a Windows operating system.");
            return PlatformConstants.PLATFORM_WINDOWS;
        } else if (os.contains("nux") || os.contains("nix") || os.contains("aix") || os.contains("mac")) {
            System.out.println("This is a Unix or Linux operating system.");
            return PlatformConstants.PLATFORM_LINUX;
        } else {
            System.out.println("Your OS is not recognized.");
            return PlatformConstants.PLATFORM_UNIX;
        }
    }
}
