package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.constants.RequestConstants;
import com.anicaaz.leaguewarefx.ui.clientobj.*;
import com.anicaaz.leaguewarefx.ui.render.MatchResultBrief;
import com.anicaaz.leaguewarefx.utils.EffectsRenderer;
import com.anicaaz.leaguewarefx.utils.FileUtil;
import com.anicaaz.leaguewarefx.utils.HttpsUtil;
import com.anicaaz.leaguewarefx.utils.LogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 负责控制用户主页（main-view）的资源显示和逻辑。
 */
public class MainController implements Initializable {
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
    private ImageView profileIcon;
    @FXML
    private Text summonerName;
    @FXML
    private Text summonerLevel;
    @FXML
    private ImageView currentRankImage;
    @FXML
    private ImageView historyHighestRankImage;
    @FXML
    private ListView<Pane> matchHistoryList;

    /**
     * 如果点击到tools，那么就加载tool-view的fxml文件。
     */
    @FXML
    public void toolsButtonOnClick() {
        loadView("views/tools-view.fxml");
    }

    /**
     * 点击overview（主页）按钮，则会重新load主页。
     */
    @FXML
    public void overviewButtonOnClick() {
        loadView("views/main-view.fxml");
    }

    /**
     * 设置用户名称。
     */
    public void setSummonerName() {
        summonerName.setText(LeagueWareFXStarter.gameName);
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
            String apiUrl = HttpsUtil.constructUrl(RequestConstants.BASEURL, LeagueWareFXStarter.appPort, RequestConstants.GET_PROFILE_ICON + "/" + LeagueWareFXStarter.profileIconId + ".jpg");
            HttpsUtil httpsUtil = new HttpsUtil(apiUrl, RequestConstants.GET);
            try {
                httpsUtil.downloadImage(AssetsFilePathConstants.profileIconRootPath, LeagueWareFXStarter.remotingAuthToken);
                Thread.sleep(3000);//等待下载
                profileIcon.setImage(new Image(AssetsFilePathConstants.profileIconFilePath));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setUpMyMatchHistory() {
        ObservableList<Pane> matchresults = FXCollections.observableArrayList();
        HttpsUtil httpsUtil = new HttpsUtil(RequestConstants.BASEURL + LeagueWareFXStarter.appPort + RequestConstants.GET_MATCH_HISTORY, RequestConstants.GET);
        try {
            String res = httpsUtil.sendHttpRequestAndGetResponse(LeagueWareFXStarter.remotingAuthToken);
            ObjectMapper objectMapper = new ObjectMapper();
            GameData gameData = objectMapper.readValue(res, GameData.class);
            List<Game> gamesList = gameData.getGames().getGames();

            for (int i = 0; i < gamesList.size(); i++) {
                Game game = gamesList.get(i);
                String mode = game.getGameMode();
                List<ParticipantIdentity> participantIdentities = game.getParticipantIdentities();
                List<Participant> participants = game.getParticipants();
                Participant me = participants.get(0);
                Stats stats = me.getStats();
                Long gameDateRaw = game.getGameCreation();
                Integer gameDurationRaw = game.getGameDuration();
                int item1Raw = stats.getItem1();
                int item2Raw = stats.getItem2();
                int item3Raw = stats.getItem3();
                int item4Raw = stats.getItem4();
                int item5Raw = stats.getItem5();
                int item6Raw = stats.getItem6();
                int item0Raw = stats.getItem0();

                int kill = stats.getKills();
                int death = stats.getDeaths();
                int assist = stats.getAssists();
                int summonerSpell1Raw = me.getSpell1Id();
                int summonerSpell2Raw = me.getSpell2Id();
                int championPlayedRaw = me.getChampionId();
                String kda = kill + " / " + death + " / " + assist;
                String result = me.getStats().isWin() ? "胜利" : "失败";

                //下载ChampionImage
                Image championImage = new Image(AssetsFilePathConstants.CHAMPIONICONSROOTIMAGE + championPlayedRaw + ".png");

                //下载SpellImage 1
                Image summonerSpell1 = new Image(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE + summonerSpell1Raw + ".png");

                //下载SpellImage 2
                Image summonerSpell2 = new Image(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE + summonerSpell2Raw + ".png");

                Image itemImage0 = null;
                Image itemImage1 = null;
                Image itemImage2 = null;
                Image itemImage3 = null;
                Image itemImage4 = null;
                Image itemImage5 = null;
                Image itemImage6 = null;
                if (item0Raw != 0) {
                    itemImage0 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item0Raw + ".png");
                }
                if (item1Raw != 0) {
                    itemImage1 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item1Raw + ".png");
                }
                if (item2Raw != 0) {
                    itemImage2 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item2Raw + ".png");
                }
                if (item3Raw != 0) {
                    itemImage3 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item3Raw + ".png");
                }
                if (item4Raw != 0) {
                    itemImage4 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item4Raw + ".png");
                }
                if (item5Raw != 0) {
                    itemImage5 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item5Raw + ".png");
                }
                if (item6Raw != 0) {
                    itemImage6 = new Image(AssetsFilePathConstants.ITEMICONROOTImage + item6Raw + ".png");
                }


                MatchResultBrief matchResultBrief = new MatchResultBrief(null, result, mode, kda, gameDurationRaw.toString(), gameDateRaw.toString(), championImage, summonerSpell1, summonerSpell2, itemImage0, itemImage1, itemImage2, itemImage3, itemImage4, itemImage5, itemImage6);
                matchresults.add(matchResultBrief.getView());
            }
            matchHistoryList.setItems(matchresults);
        } catch (IOException e) {
            LogUtil.log("无法获取到比赛记录");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        setUpMyMatchHistory();
        EffectsRenderer.addFadeInFadeOut(0.32, subPane);
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