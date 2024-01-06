package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.HttpsUtils;
import com.sun.net.httpserver.HttpServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    private ImageView profileIcon;
    @FXML
    private Text summonerName;
    @FXML
    private Text summonerLevel;
    private static HttpServer httpServer;
    @FXML
    public void toolsButtonOnClick() {
        loadView("tools-view.fxml");
    }

    @FXML
    public void overviewButtonOnClick() {
        loadView("main-view.fxml");
    }

    public void setSummonerName() {
        summonerName.setText("召唤师名称: " + LeagueWareFXStarter.gameName);
    }

    public void setSummonerLevel() {
        summonerLevel.setText("等级: " + LeagueWareFXStarter.summonerLevel.toString());
    }

    public void setprofileIcon() {
        String apiUrl = HttpsUtils.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort,
                RequestConstants.GET_PROFILE_ICON + "/" + LeagueWareFXStarter.profileIconId + ".jpg");
        HttpsUtils httpsUtils = new HttpsUtils(apiUrl, RequestConstants.GET);
        try {
            httpsUtils.downloadImage("src/main/resources/com/anicaaz/leaguewarefx/assets/static/profileIcon.jpg", LeagueWareFXStarter.remotingAuthToken);
            profileIcon.setImage(new Image("com/anicaaz/leaguewarefx/assets/static/profileIcon.jpg"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setprofileIcon();
        setSummonerName();
        setSummonerLevel();
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