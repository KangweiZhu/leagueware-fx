package com.anicaaz.leaguewarefx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.Data;

import java.io.IOException;

@Data
public class MatchResultBriefController {
    @FXML
    private Rectangle resIndicator;
    @FXML
    private Label result;
    @FXML
    private Label mode;
    @FXML
    private Label kda;
    @FXML
    private Label gameDuration;
    @FXML
    private Label gameDate;
    @FXML
    private ImageView championImage;
    @FXML
    private ImageView summonerSpell1;
    @FXML
    private ImageView summonerSpell2;
    @FXML
    private ImageView itemImage0;
    @FXML
    private ImageView itemImage1;
    @FXML
    private ImageView itemImage2;
    @FXML
    private ImageView itemImage3;
    @FXML
    private ImageView itemImage4;
    @FXML
    private ImageView itemImage5;
    @FXML
    private ImageView itemImage6;



    public void updateAll(String result, String mode, String kda, String gameDuration, String gameDate, Image championImage,
                           Image summonerSpell1, Image summonerSpell2, Image itemImage0, Image itemImage1, Image itemImage2,
                          Image itemImage3, Image itemImage4, Image itemImage5, Image itemImage6) {
        this.result.setText(result);
        this.mode.setText(mode);
        this.kda.setText(kda);
        this.gameDuration.setText(gameDuration);
        this.gameDate.setText(gameDate);
        this.championImage.setImage(championImage);
        this.summonerSpell1.setImage(summonerSpell1);
        this.summonerSpell2.setImage(summonerSpell2);
        this.itemImage0.setImage(itemImage0);
        this.itemImage1.setImage(itemImage1);
        this.itemImage2.setImage(itemImage2);
        this.itemImage3.setImage(itemImage3);
        this.itemImage4.setImage(itemImage4);
        this.itemImage5.setImage(itemImage5);
        this.itemImage6.setImage(itemImage6);
        this.gameDate.setText(gameDate);
        if (result.equals("失败")) {
            this.resIndicator.setFill(Paint.valueOf("#FF5859"));
            this.result.setTextFill(Paint.valueOf("#FF5859"));
        }
    }
}
