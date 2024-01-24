package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.ui.ingameobj.GameData;
import com.anicaaz.leaguewarefx.ui.ingameobj.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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
