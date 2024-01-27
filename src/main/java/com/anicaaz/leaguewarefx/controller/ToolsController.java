package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.service.PlayerService;
import com.anicaaz.leaguewarefx.ui.ingameobj.GameData;
import com.anicaaz.leaguewarefx.ui.ingameobj.Player;
import com.anicaaz.leaguewarefx.utils.EffectsRenderer;
import com.anicaaz.leaguewarefx.utils.HttpsUtil;
import com.anicaaz.leaguewarefx.utils.LogUtil;
import com.anicaaz.leaguewarefx.utils.OSUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ToolsController implements Initializable {
    @FXML
    private GridPane mainGridPane;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane subPane;
    @FXML
    private Button overviewButton;
    @FXML
    private Button personInfoButton;
    @FXML
    private Button searchPlayerButton;
    @FXML
    private Button rankingButton;
    @FXML
    private Button toolsButton;
    @FXML
    private CheckBox autoAcceptCheckBox;
    @FXML
    private CheckBox autoDeclineCheckBox;
    @FXML
    private CheckBox fakePromotionCheckBox;
    @FXML
    private ChoiceBox fakePromotionChoiceBox;
    @FXML
    private CheckBox ingameOverlayCheckBox;
    private Timer timer;
    private Thread monitorIngameOverlayThread;
    private volatile boolean monitorIngameOverlayThreadFlag;
    private static final PlayerService playerService = new PlayerService();

    @FXML
    public void toolsButtonOnClick() {
        loadView("views/tools-view.fxml");
    }

    @FXML
    public void overviewButtonOnClick() {
        loadView("views/main-view.fxml");
    }

    /**
     * If the auto accept button is clicked, then this function will handle the "clicked" action
     */
    public void autoAcceptedCheckBoxOnClicked() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        if (autoAcceptCheckBox.isSelected()) {
            String apiUrl = HttpsUtil.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.POST_AUTO_ACCEPT);
            HttpsUtil httpsUtil = new HttpsUtil(apiUrl, RequestConstants.POST);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        httpsUtil.sendHttpRequestAndGetRespCode(LeagueWareFXStarter.remotingAuthToken);
                        System.out.println("auto accept sending");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 0, 7000);
        } else {
            timer.cancel();
            System.out.println("auto accept cancelled");
        }
    }

    /**
     * If the auto decline button is clicked, then this function will handle the "clicked" action
     */
    public void autoDeclineCheckBoxOnClicked() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        if (autoDeclineCheckBox.isSelected()) {
            String apiUrl = HttpsUtil.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.POST_AUTO_DECLINE);
            HttpsUtil httpsUtil = new HttpsUtil(apiUrl, RequestConstants.POST);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        httpsUtil.sendHttpRequestAndGetRespCode(LeagueWareFXStarter.remotingAuthToken);
                        System.out.println("auto decline sending");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 0, 7000);
        } else {
            timer.cancel();
            System.out.println("auto decline cancelled");
        }
    }

    public void fakePromotionCheckBoxOnClicked() {
        initProxyServer();

    }

    private void initProxyServer() {

    }

    /**
     * 游戏内绘制按钮被选中，或点击后变成未选中状态。通过多线程来解决干扰。
     *
     */
    public void inGameOverlayCheckBoxOnClicked() {
        if (ingameOverlayCheckBox.isSelected()) {
            monitorIngameOverlayThreadFlag = true;
            monitorIngameOverlayThread = new Thread(() -> {
                try {
                    this.ingameOverlayHelper();
                } catch (IOException e) {
                    LogUtil.log(ToolsController.class.getName(), "inGameOverlayCheckBoxOnClicked", "IOException");
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            monitorIngameOverlayThread.start();
        } else {
            monitorIngameOverlayThreadFlag = false;
        }
    }

    private void ingameOverlayHelper() throws IOException, InterruptedException {
        int logFlag = 0;
        int initializedFlag = 0;
        //如果一直为true，则进程一直执行。
        while (monitorIngameOverlayThreadFlag) {
            while (OSUtil.checkProcess("League of Legends.exe")) {
                if (logFlag >= 1) {
                    return;
                }
            }
            if (logFlag == 0) {
                LogUtil.log(ToolsController.class.getName(), "ingameOverlayHelper", "Game Client is running");
                logFlag++;
                //停20秒，等待端口被暴露
                Thread.sleep(20000);
            }

            //向端口发送消息，并等待返回值
            HttpsUtil httpsUtil = new HttpsUtil(RequestConstants.BASEURL + RequestConstants.GAME_CLIENT_PORT + RequestConstants.ALL_GAME_DATA, RequestConstants.GET);
            String res = httpsUtil.sendHttpRequestAndGetResponse();
            ObjectMapper objectMapper = new ObjectMapper();
            GameData gameData = objectMapper.readValue(res, GameData.class);
            List<Player> playerList = gameData.getAllPlayers();

            //物品更新逻辑
            playerService.setPlayerList(playerList);
            if (playerService.getPrevplayerList() == null || playerService.getPrevplayerList().isEmpty()) {
                playerService.setPrevplayerList(playerList);
            }
            playerService.checkPlayerItemUpdate();
            playerService.setPrevplayerList(playerList);
            if (initializedFlag++ == 0) {
                playerService.drawEnemySpellsInfo();
            }

            // 停0.5秒。
            Thread.sleep(500);
        }
    }

    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(LeagueWareFXStarter.class.getResource(fxmlFileName));
            Node newView = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EffectsRenderer.addFadeInFadeOut(0.32, subPane);
    }
}
