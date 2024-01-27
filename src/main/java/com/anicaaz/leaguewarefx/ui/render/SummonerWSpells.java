package com.anicaaz.leaguewarefx.ui.render;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.controller.SummonerWSpellsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import lombok.Data;

import java.io.IOException;

@Data
public class SummonerWSpells {
    private Pane pane;
    private Image championImage;
    private Image summonerSpell1;
    private Image summonerSpell2;
    private SummonerWSpellsController controller;
    public SummonerWSpells(Image championImage, Image summonerSpell1, Image summonerSpell2) {
        this.championImage = championImage;
        this.summonerSpell1 = summonerSpell1;
        this.summonerSpell2 = summonerSpell2;
        FXMLLoader fxmlLoader = new FXMLLoader(LeagueWareFXStarter.class.getResource("views/components/championWSpells-view.fxml"));
        try {
            Pane view = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.updateAll(championImage, summonerSpell1, summonerSpell2);
            this.pane = view;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
