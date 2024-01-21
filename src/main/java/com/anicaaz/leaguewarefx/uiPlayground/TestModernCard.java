package com.anicaaz.leaguewarefx.uiPlayground;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestModernCard extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // 创建阴影效果
        DropShadow dropShadow = new DropShadow(10, Color.BLACK);
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        // 创建外层卡片形状
        Rectangle card = new Rectangle(100, 50, 300, 150);
        card.setFill(Color.YELLOW);
        card.setEffect(dropShadow);

        // 创建内部召唤师名称背景
        Rectangle summonerNameBg = new Rectangle(120, 80, 160, 40);
        summonerNameBg.setFill(Color.BLUE);

        // 创建召唤师名称文本
        Text summonerName = new Text(130, 110, "Summoner Name");
        summonerName.setFill(Color.WHITE);

        // 创建段位背景
        Rectangle rankBg = new Rectangle(290, 130, 80, 20);
        rankBg.setFill(Color.GREEN);

        // 创建段位文本
        Text rankText = new Text(300, 145, "Rank");

        // 创建头像背景
        Rectangle avatarBg = new Rectangle(280, 80, 20, 20);
        avatarBg.setFill(Color.PINK);

        // 将所有元素添加到root
        root.getChildren().addAll(card, summonerNameBg, summonerName, rankBg, rankText, avatarBg);

        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Card Design");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
