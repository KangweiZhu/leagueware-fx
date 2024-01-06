package com.anicaaz.leaguewarefx;

import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.EncodingUtil;
import com.anicaaz.leaguewarefx.utils.ExecuteCommand;
import com.anicaaz.leaguewarefx.utils.HttpsUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;


public class LeagueWareFXStarter extends Application {
    public static String remotingAuthToken;
    public static String appPort;
    public static String gameName;
    public static Long accountId;
    public static Integer profileIconId;
    public static Integer summonerLevel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LeagueWareFXStarter.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 700)    ;
        stage.setTitle("LeagueWare");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        getConnectionPrerequisites();
        getSummonerInfo();
        launch();
    }

    public static void getConnectionPrerequisites() {
        String rawToken = ExecuteCommand.getRemoteAuthToken();
        remotingAuthToken = "Basic " + EncodingUtil.encodeToBase64("riot:" + rawToken);
        appPort = ExecuteCommand.getAppPort();
        System.out.println(remotingAuthToken);
        System.out.println(appPort);
    }

    public static void getSummonerInfo() {
        String apiUrl = HttpsUtils.constructUrl(RequestConstants.BASEURL, appPort,  RequestConstants.GET_CURRENT_SUMMONER_NAME);
        HttpsUtils httpsUtils = new HttpsUtils(apiUrl, RequestConstants.GET);
        try {
            Map<String, Object> stringObjectMap = httpsUtils.sendHttpRequest(remotingAuthToken);
            gameName = (String) stringObjectMap.get("gameName");
            accountId = (Long) stringObjectMap.get("accountId");
            profileIconId = (Integer) stringObjectMap.get("profileIconId");
            summonerLevel = (Integer) stringObjectMap.get("summonerLevel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}