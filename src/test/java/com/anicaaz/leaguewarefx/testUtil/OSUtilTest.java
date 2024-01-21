package com.anicaaz.leaguewarefx.testUtil;

/**
 *  负责对 操作系统 工具类进行测试
 */
public class OSUtilTest {

    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("This is a Windows operating system.");
        } else if (os.contains("nux") || os.contains("nix") || os.contains("aix") || os.contains("mac")) {
            System.out.println("This is a Unix or Linux operating system.");
        } else {
            System.out.println("Your OS is not recognized.");
        }
    }
}
