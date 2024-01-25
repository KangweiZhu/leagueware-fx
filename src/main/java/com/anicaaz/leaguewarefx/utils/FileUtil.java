package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Request;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 文件工具类
 * @author anicaa
 */
public class FileUtil {



    /**
     * Check if a file exists in a certain path or not.
     * @return true if exists, false if not exists.
     */
    public static boolean checkIfFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static void downloadSummonerSpellsIcons(String httpUrl) throws IOException {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.setApiUrl(httpUrl);
        httpsUtil.setRequestMethod(RequestConstants.GET);
        String jsonString = httpsUtil.sendHttpRequestAndGetResponse();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            String iconPath = jsonObject.get("iconPath").getAsString();
            String downloadUrl = RequestConstants.BASEURL + LeagueWareFXStarter.appPort + iconPath;
            System.out.println(downloadUrl);
            httpsUtil.setApiUrl(downloadUrl);
            httpsUtil.setRequestMethod(RequestConstants.GET);
            try {
                httpsUtil.downloadImage(AssetsFilePathConstants.SUMMONERSPELLICONSROOT + id + ".png", LeagueWareFXStarter.remotingAuthToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void downloadChampionIcons(String httpUrl) throws IOException {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.setApiUrl(httpUrl);
        httpsUtil.setRequestMethod(RequestConstants.GET);
        String jsonString = httpsUtil.sendHttpRequestAndGetResponse();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            String squarePortraitPath = jsonObject.get("squarePortraitPath").getAsString();
            String downloadUrl = RequestConstants.BASEURL + LeagueWareFXStarter.appPort + squarePortraitPath;
            httpsUtil.setApiUrl(downloadUrl);
            httpsUtil.setRequestMethod(RequestConstants.GET);
            try {
                httpsUtil.downloadImage(AssetsFilePathConstants.CHAMPIONICONSROOT + id + ".png", LeagueWareFXStarter.remotingAuthToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void downloadItemIcons(String httpUrl) throws IOException {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.setApiUrl(httpUrl);
        httpsUtil.setRequestMethod(RequestConstants.GET);
        String jsonString = httpsUtil.sendHttpRequestAndGetResponse();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for(JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            String iconPath = jsonObject.get("iconPath").getAsString();
            String downloadUrl = RequestConstants.BASEURL + LeagueWareFXStarter.appPort + iconPath;
            httpsUtil.setApiUrl(downloadUrl);
            httpsUtil.setRequestMethod(RequestConstants.GET);
            try {
                httpsUtil.downloadImage(AssetsFilePathConstants.ITEMICONROOT + id + ".png", LeagueWareFXStarter.remotingAuthToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
