package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.PlatformConstants;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.annotation.Documented;

/**
 *
 * 负责 操作系统 相关的工具类
 *
 * @author anicaazhu
 */
public class OSUtil {
    private interface MyUser32 extends Library {
        MyUser32 INSTANCE = Native.load("user32", MyUser32.class);
        int KEYEVENT_KEYUP = 0x0002;
        void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
    }

    private static final MyUser32 myUser32 = MyUser32.INSTANCE;
    private static final byte byte_VKENTER = 0x0D;

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

    /**
     * 创建快照，并遍历所有进程。用于检查指定名字的进程是否存在
     *
     * @param target 进程名
     * @return true存在， false不存在
     */
    public static boolean checkProcess(String target) {
        WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD pid = null;
        while (Kernel32.INSTANCE.Process32Next(snapshot, processEntry)) {
            String processName = Native.toString(processEntry.szExeFile);
            if (processName.equals(target)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找 指定名字的进程的 pid
     * @param target 进程名字
     * @return pid
     */
    public static WinDef.DWORD findPid(String target) {
        WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD pid = null;
        while (Kernel32.INSTANCE.Process32Next(snapshot, processEntry)) {
            String processName = Native.toString(processEntry.szExeFile);
            if (processName.equals(target)) {
                pid = processEntry.th32ProcessID;
                System.out.println("Found process with PID: " + processEntry.th32ProcessID);
            }
        }
        return pid;
    }

    /**
     * 单线程情况没问题。可以通过OSUtil中的Main方法进行测试。
     * javafx多线程情况编码和按键会被影响（似乎）。
     * 使用Robot就没有这个问题。其余native方法也是一样
     */
    @Deprecated
    public static void nativePressEnter() {
        myUser32.keybd_event(byte_VKENTER, (byte) 0, 0, 0);
        myUser32.keybd_event(byte_VKENTER, (byte) 0, myUser32.KEYEVENT_KEYUP, 0);
    }

    @Deprecated
    public static void nativeTypeString(String text) {
        for (char c : text.toCharArray()) {
            byte keyCode = (byte) c;
            myUser32.keybd_event(keyCode, (byte) 0, 0, 0);
            myUser32.keybd_event(keyCode, (byte) 0, myUser32.KEYEVENT_KEYUP, 0);
        }
    }

        public static void pressEnter() {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(100);
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    public static void typeString(String text) {
        try {
            Robot robot = new Robot();
            char[] chars = text.toCharArray();
            for (char c : chars) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (keyCode != KeyEvent.CHAR_UNDEFINED) {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    Thread.sleep(100);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void performInputInGameWindow(String windowTitle, String text) {
        // 查找游戏窗口
        WinDef.HWND hwnd = findGameWindow(windowTitle);

        if (hwnd != null) {
            // 设置窗口焦点
            User32.INSTANCE.SetForegroundWindow(hwnd);
            // 等待一段时间以确保焦点已经移动到正确的位置
            try {
                Thread.sleep(1000); // 可根据需要调整等待时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 执行输入逻辑
            nativeTypeString(text);
        } else {
            System.out.println("Game window not found.");
        }
    }


    @Deprecated
    public static void nativePressKey(byte keyCode, boolean needShift) {
        if (needShift) {

        } else {

        }
    }

    public static void focusGameWindow(String windowTitle) {
        // 查找游戏窗口
        WinDef.HWND hwnd = findGameWindow(windowTitle);
        if (hwnd != null) {
            // 设置窗口焦点
            User32.INSTANCE.SetForegroundWindow(hwnd);
        }
    }

    private static WinDef.HWND findGameWindow(String windowTitle) {
        char[] buffer = new char[512];
        User32 user32 = User32.INSTANCE;

        WinDef.HWND hwnd = null;
        while ((hwnd = user32.FindWindowEx(null, hwnd, null, null)) != null) {
            user32.GetWindowText(hwnd, buffer, buffer.length);
            String title = Native.toString(buffer);
            if (title.contains(windowTitle)) {
                return hwnd;
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        focusGameWindow("League of Legends (TM) Client");
        OSUtil.pressEnter();
        Thread.sleep(300);
        OSUtil.typeString("12 15 jg 13 13 mid 14 14 top 15 15 mid 16 16 ffid 14 14 top 15 15 mid 16 16 ff" +
                "");
        OSUtil.pressEnter();
    }
}
