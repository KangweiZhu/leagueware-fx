package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.PlatformConstants;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

/**
 *
 * 负责 操作系统 相关的工具类
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
}
