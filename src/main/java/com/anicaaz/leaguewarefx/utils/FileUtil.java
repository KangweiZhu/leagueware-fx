package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    /**
     * 本地json文件转换成String
     * @param filePath 相对路径
     * @return Json字符串
     */
    public static String jsonFile2String(String filePath) {
        /*String filePath = "/com/anicaaz/leaguewarefx/assets/json/summonerSpells.json";*/
        String res = null;
        try {
            res = Files.readString(Paths.get(LeagueWareFXStarter.class.getResource(filePath).toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return res;
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

    public static String getIdFromJsonByName(String jsonString, String name) {
        String id = "-1";
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String nameInJsonFile = jsonObject.get("name").getAsString();
            if (name.equals(nameInJsonFile)) {
                id = jsonObject.get("id").getAsString();
                break;
            }
        }
        return id;
    }

    public static Image getImageById(String baseFilePath, String id) {
        return new Image(baseFilePath + id + ".png");
    }

    public static void main(String[] args) {
        String test = FileUtil.jsonFile2String("/com/anicaaz/leaguewarefx/assets/json/summonerSpells.json");
        System.out.println(test);
    }
}
