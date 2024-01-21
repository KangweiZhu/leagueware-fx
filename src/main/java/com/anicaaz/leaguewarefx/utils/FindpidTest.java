package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.ui.obj.GameData;
import com.anicaaz.leaguewarefx.ui.obj.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class FindpidTest {

    public static WinDef.DWORD findPid() {
        WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD pid = null;
        while (Kernel32.INSTANCE.Process32Next(snapshot, processEntry)) {
            String processName = Native.toString(processEntry.szExeFile);
            if (processName.equals("League of Legends.exe")) {
                pid = processEntry.th32ProcessID;
                System.out.println("Found League of Legends process with PID: " + processEntry.th32ProcessID);
            }
        }
        return pid;
    }

    public static boolean checkIfProcessExists () {
        WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD pid = null;
        while (Kernel32.INSTANCE.Process32Next(snapshot, processEntry)) {
            String processName = Native.toString(processEntry.szExeFile);
            if (processName.equals("League of Legends.exe")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while (checkIfProcessExists()) {

        }
        LogUtil.log(FindpidTest.class.getName(), "main", "Game Client is running");
        HttpsUtil httpsUtil = new HttpsUtil(RequestConstants.BASEURL + RequestConstants.GAME_CLIENT_PORT + RequestConstants.ALL_GAME_DATA, RequestConstants.GET);
        String res = httpsUtil.sendHttpRequestAndGetResponse().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = objectMapper.readValue(res, GameData.class);
        List<Player> playerList = gameData.getAllPlayers();
        //JVM  --add-opens com.anicaaz.leaguewarefx/com.anicaaz.leaguewarefx.ui.obj=com.google.gson
        System.out.println(playerList.toString());
    }
}
