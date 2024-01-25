package com.anicaaz.leaguewarefx.ui.render;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.controller.MatchResultBriefController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import lombok.Data;

import java.io.IOException;

@Data
public class MatchResultBrief {
    private Pane view;
    private String result;
    private String mode;
    private String kda;
    private String gameDuration;
    private String gameDate;
    private Image championImage;
    private Image summonerSpell1;
    private Image summonerSpell2;
    private Image itemImage0;
    private Image itemImage1;
    private Image itemImage2;
    private Image itemImage3;
    private Image itemImage4;
    private Image itemImage5;
    private Image itemImage6;
    private Image itemImage7;

    public MatchResultBrief(Pane view, String result, String mode, String kda, String gameDuration, String gameDate,
                            Image championImage, Image summonerSpell1, Image summonerSpell2, Image itemImage0, Image itemImage1,
                            Image itemImage2, Image itemImage3, Image itemImage4, Image itemImage5, Image itemImage6) {
        this.view = view;
        this.result = result;
        this.mode = mode;
        this.kda = kda;
        this.gameDuration = gameDuration;
        this.gameDate = gameDate;
        this.championImage = championImage;
        this.summonerSpell1 = summonerSpell1;
        this.summonerSpell2 = summonerSpell2;
        this.itemImage0 = itemImage0;
        this.itemImage1 = itemImage1;
        this.itemImage2 = itemImage2;
        this.itemImage3 = itemImage3;
        this.itemImage4 = itemImage4;
        this.itemImage5 = itemImage5;
        this.itemImage6 = itemImage6;

        FXMLLoader loader = new FXMLLoader(LeagueWareFXStarter.class.getResource("views/components/matchHistory-brief.fxml"));
        try {
            this.view = loader.load();
            MatchResultBriefController controller = loader.getController();
            controller.updateAll(result, mode, kda, gameDuration, gameDate, championImage, summonerSpell1, summonerSpell2,
                    itemImage0, itemImage1, itemImage2, itemImage3, itemImage4, itemImage5, itemImage6);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
