package com.anicaaz.leaguewarefx.ui.obj;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;

/**
 * 用于展示 用户信息的 小卡片
 *
 *
 * @author anicaazhu
 */
@NoArgsConstructor
public class PlayerInfoCard extends VBox {
    private ImageView heroPortrait;
    private Label nickname;
    private Label runes;
    private ImageView summonerSkill1;
    private ImageView summonerSkill2;

    public PlayerInfoCard(String heroImagePath, String nicknameText, String runesText, String skill1Path, String skill2Path) {
        // Set up the hero portrait
        Image heroImage = new Image(heroImagePath);
        heroPortrait = new ImageView(heroImage);
        heroPortrait.setFitHeight(100); // Adjust as needed
        heroPortrait.setFitWidth(100); // Adjust as needed

        // Set up the nickname label
        nickname = new Label(nicknameText);

        // Set up the runes label
        runes = new Label(runesText);

        // Set up the summoner skill images
        Image skill1Image = new Image(skill1Path);
        summonerSkill1 = new ImageView(skill1Image);
        summonerSkill1.setFitHeight(30); // Adjust as needed
        summonerSkill1.setFitWidth(30); // Adjust as needed

        Image skill2Image = new Image(skill2Path);
        summonerSkill2 = new ImageView(skill2Image);
        summonerSkill2.setFitHeight(30); // Adjust as needed
        summonerSkill2.setFitWidth(30); // Adjust as needed

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().addAll(heroPortrait, nickname, runes, summonerSkill1, summonerSkill2);

    }
}

