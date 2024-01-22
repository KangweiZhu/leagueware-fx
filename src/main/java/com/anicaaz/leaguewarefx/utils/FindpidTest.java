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

@Deprecated
public class FindpidTest {

    public static void main(String[] args) throws IOException {
        while (OSUtil.checkProcess("League of Legends.exe")) {

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
