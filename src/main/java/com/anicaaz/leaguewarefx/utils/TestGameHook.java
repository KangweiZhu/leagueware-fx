package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestGameHook extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Assuming you have placeholder images and they are named as img1.png, img2.png, etc.
        for (int i = 0; i < 6; i++) {
            VBox characterBox = createCharacterBox("Character" + (i + 1), "img" + (i + 1) + ".png", "Player" + (i + 1), "50%", "1000");
            gridPane.add(characterBox, i % 3, i / 3);
        }

        Scene scene = new Scene(gridPane, 1024, 768);
        primaryStage.setTitle("Game Scoreboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCharacterBox(String characterName, String imagePath, String playerName, String winRate, String score) {
        ImageView imageView = new ImageView(new Image(AssetsFilePathConstants.profileIconFilePath));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        Label nameLabel = new Label(characterName);
        Label playerLabel = new Label(playerName);
        Label winRateLabel = new Label("Win Rate: " + winRate);
        Label scoreLabel = new Label("Score: " + score);

        VBox box = new VBox(10, imageView, nameLabel, playerLabel, winRateLabel, scoreLabel);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black;");

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
