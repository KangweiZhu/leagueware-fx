package com.anicaaz.leaguewarefx;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;

/**
 * LeagueWare程序的主入口，负责在视图显示前，获取必要的信息。如token, 端口，用户信息
 *
 * @author anicaa
 */
public class LeagueWareFXStarter extends Application {
    public static String remotingAuthToken;
    public static String appPort;
    public static String gameName;
    public static Long accountId;
    public static Integer profileIconId;
    public static Integer summonerLevel;
    public static final String RUNNING_PLATFORM = OSUtil.checkPlatform();
    public static Double SCREEN_HEIGHT;
    public static Double SCREEN_WIDTH;
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * 从main-view中加载主页，并设置窗口属性。
     * todo: 未检测到LCU进程时，一直处于一个等待界面，而不是直接报错。
     *
     * @param stage fxml文件对应的stage.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LeagueWareFXStarter.class.getResource("views/main-view.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);//避免圆角窗体出现白边
        stage.setTitle("LeagueWare");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT); // Conditional Feature。 如果平台不支持，则降级为UNDECORATED。同样为了避免白边。

        // 计算出窗体左上角和当前鼠标按下的位置
        root.setOnMousePressed(event -> {
            xOffset = event.getScreenX() - stage.getX();
            yOffset = event.getScreenY() - stage.getY();
        });

        // 拖动到了新位置，还原出原先位置
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        stage.show();
    }

    /**
     * 主入口。获取必要信息，并调用javafx的launch方法来启动app.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        getConnectionPrerequisites();
        getSummonerInfo();
        getScreenInfo();
        //todo: 优化成python脚本。

        // 下载所有召唤师图片
        if (!FileUtil.checkIfFileExist(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE + "1.png")) {
            LogUtil.log("召唤师技能图标库不存在，下载中");
            FileUtil.downloadSummonerSpellsIcons("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/summoner-spells.json");
            LogUtil.log("召唤师技能图标库下载完成");
        } else {
            LogUtil.log("召唤师技能图标库存在");
        }

        // 下载所有英雄正方形小图标
        if (!FileUtil.checkIfFileExist(AssetsFilePathConstants.CHAMPIONICONSROOTIMAGE + "1.png")) {
            LogUtil.log("召唤师方形小图标库不存在，下载中");
            FileUtil.downloadChampionIcons("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-summary.json");
            LogUtil.log("召唤师方形小图标库下载完成");
        } else {
            LogUtil.log("召唤师方形小图标库存在");
        }

        // 下载所有装备缩略图图标
        if (!FileUtil.checkIfFileExist(AssetsFilePathConstants.ITEMICONROOTImage + "1001.png")) {
            LogUtil.log("装备缩略图图标库不存在，下载中");
            FileUtil.downloadItemIcons("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/items.json");
            LogUtil.log("装备缩略图图标库下载完成");
        } else {
            LogUtil.log("装备缩略图图标库存在");
        }

        if (FileUtil.checkIfFileExist(AssetsFilePathConstants.profileIconFilePath)) {
            LogUtil.log("主页头像已存在");
        } else {
            LogUtil.log("主页头像不存在， 开始下载");
            String apiUrl = HttpsUtil.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.GET_PROFILE_ICON + "/" + LeagueWareFXStarter.profileIconId + ".jpg");
            HttpsUtil httpsUtil = new HttpsUtil(apiUrl, RequestConstants.GET);
            try {
                httpsUtil.downloadImage(AssetsFilePathConstants.profileIconRootPath, LeagueWareFXStarter.remotingAuthToken);
                LogUtil.log("主页头像下载完成");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
        String apiUrl = HttpsUtil.constructUrl(RequestConstants.BASEURL, appPort, RequestConstants.GET_CURRENT_SUMMONER_NAME);
        HttpsUtil httpsUtil = new HttpsUtil(apiUrl, RequestConstants.GET);
        try {
            Map<String, Object> stringObjectMap = httpsUtil.sendHttpRequest(remotingAuthToken);
            gameName = (String) stringObjectMap.get("gameName");
            accountId = (Long) stringObjectMap.get("accountId");
            profileIconId = (Integer) stringObjectMap.get("profileIconId");
            summonerLevel = (Integer) stringObjectMap.get("summonerLevel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getScreenInfo() {
        Screen screen = Screen.getPrimary();
        SCREEN_HEIGHT = (Double) screen.getBounds().getHeight();
        SCREEN_WIDTH = (Double) screen.getBounds().getWidth();
    }
}