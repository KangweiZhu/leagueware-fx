package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.HttpsUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

public class ToolsController {
    @FXML
    private GridPane mainGridPane;
    @FXML
    private Pane mainPane;
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
    private Timer timer;
    @FXML
    public void toolsButtonOnClick() {
        loadView("tools-view.fxml");
    }

    @FXML
    public void overviewButtonOnClick() {
        loadView("main-view.fxml");
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
            String apiUrl = HttpsUtils.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.POST_AUTO_ACCEPT);
            HttpsUtils httpsUtils = new HttpsUtils(apiUrl, RequestConstants.POST);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        httpsUtils.sendHttpRequestAndGetRespCode(LeagueWareFXStarter.remotingAuthToken);
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
            String apiUrl = HttpsUtils.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.POST_AUTO_DECLINE);
            HttpsUtils httpsUtils = new HttpsUtils(apiUrl, RequestConstants.POST);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        httpsUtils.sendHttpRequestAndGetRespCode(LeagueWareFXStarter.remotingAuthToken);
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
}
