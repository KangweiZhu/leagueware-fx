package com.anicaaz.leaguewarefx;

import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.EncodingUtil;
import com.anicaaz.leaguewarefx.utils.ExecuteCommand;
import com.anicaaz.leaguewarefx.utils.HttpsUtils;
import com.anicaaz.leaguewarefx.utils.LogUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * LeagueWare程序的主入口，负责在视图显示前，获取必要的信息。如token, 端口，用户信息
 * @author anicaa
 */
public class LeagueWareFXStarter extends Application {
    public static String remotingAuthToken;
    public static String appPort;
    public static String gameName;
    public static Long accountId;
    public static Integer profileIconId;
    public static Integer summonerLevel;

    /**
     * 从main-view中加载主页，并设置窗口属性。
     * todo: 未检测到LCU进程时，一直处于一个等待界面，而不是直接报错。
     *
     * @param stage fxml文件对应的stage.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LeagueWareFXStarter.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 700);
        stage.setTitle("LeagueWare");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * 主入口。获取必要信息，并调用javafx的launch方法来启动app.
     * @param args
     */
    public static void main(String[] args) {
        getConnectionPrerequisites();
        getSummonerInfo();
        launch();
    }

    /**
     * 获取获取token和端口号
     */
    public static void getConnectionPrerequisites() {
        String rawToken = ExecuteCommand.getRemoteAuthToken();
        remotingAuthToken = "Basic " + EncodingUtil.encodeToBase64("riot:" + rawToken);
        appPort = ExecuteCommand.getAppPort();
        LogUtil.log(LeagueWareFXStarter.class.getName(), "getConnectionPrerequisites", "remotingAuthToken: " + remotingAuthToken);
        LogUtil.log(LeagueWareFXStarter.class.getName(), "getConnectionPrerequisites", "appPort: " + appPort);
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