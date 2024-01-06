package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.utils.FileUtil;
import com.anicaaz.leaguewarefx.utils.HttpsUtils;
import com.anicaaz.leaguewarefx.utils.LogUtil;
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

/**
 * 负责控制用户主页（main-view）的资源显示和逻辑。
 */
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

    /**
     * 如果点击到tools，那么就加载tool-view的fxml文件。
     */
    @FXML
    public void toolsButtonOnClick() {
        loadView("tools-view.fxml");
    }

    /**
     * 点击overview（主页）按钮，则会重新load主页。
     */
    @FXML
    public void overviewButtonOnClick() {
        loadView("main-view.fxml");
    }

    /**
     * 设置用户名称。
     */
    public void setSummonerName() {
        summonerName.setText("召唤师名称: " + LeagueWareFXStarter.gameName);
    }

    /**
     * 设置用户等级。
     */
    public void setSummonerLevel() {
        summonerLevel.setText("等级: " + LeagueWareFXStarter.summonerLevel.toString());
    }

    /**
     * 设置头像图片。如果本地下载头像图片，则不用再下载，以提高页面加载速度。反之下载。
     * 注意：下载时，File类使用路径是从src开始。而javafx的Image类中的setImage,则是从从com.xxx开始
     */
    public void setprofileIcon() {
        if (FileUtil.checkIfFileExist(AssetsFilePathConstants.profileIconRootPath)) {
            LogUtil.log(getClass().getName(), "setprofileIcon", "图片已存在。");
            profileIcon.setImage(new Image(AssetsFilePathConstants.profileIconFilePath));
        } else {
            LogUtil.log(getClass().getName(), "setprofileIcon", "图片不存在，开始下载。");
            String apiUrl = HttpsUtils.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.GET_PROFILE_ICON + "/" + LeagueWareFXStarter.profileIconId + ".jpg");
            HttpsUtils httpsUtils = new HttpsUtils(apiUrl, RequestConstants.GET);
            try {
                httpsUtils.downloadImage(AssetsFilePathConstants.profileIconRootPath, LeagueWareFXStarter.remotingAuthToken);
                profileIcon.setImage(new Image(AssetsFilePathConstants.profileIconFilePath));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 在进入main-view.fxml之后，最先调用这三个方法，以初始化页面。
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setprofileIcon();
        setSummonerName();
        setSummonerLevel();
    }

    /**
     * 负责处理页面的切换。
     * @param fxmlFileName fxml文件的名称
     */
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