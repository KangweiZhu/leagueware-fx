package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.utils.OSUtil;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import lombok.Data;

@Data
public class SummonerWSpellsController {
    @FXML
    private ImageView championImage;
    @FXML
    private ImageView summonerSpell1;
    @FXML
    private ImageView summonerSpell2;

    public void updateAll(Image championIcons, Image summonerSpell1, Image summonerSpell2) {
        this.championImage.setImage(championIcons);
        this.summonerSpell1.setImage(summonerSpell1);
        this.summonerSpell2.setImage(summonerSpell2);
    }

    public void spellOneClickedListener() {
        new Thread(() -> {

            OSUtil.focusGameWindow("League of Legends (TM) Client");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            OSUtil.pressEnter();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /*OSUtil.nativeTypeString("0811jg 0912mid 0913top 0914bot 15ff noob sup");*/
            OSUtil.typeString("0811jg 0912mid 0913top 0914bot");
            OSUtil.pressEnter();
        }).start();
    }

    public void spellTwoClickedListener() {
    }
}
