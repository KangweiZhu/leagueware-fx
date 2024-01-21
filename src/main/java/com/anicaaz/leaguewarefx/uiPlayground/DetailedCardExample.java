package com.anicaaz.leaguewarefx.uiPlayground;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.ui.obj.PlayerCard;
import com.anicaaz.leaguewarefx.ui.obj.PlayerInfoCard;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class DetailedCardExample extends Application {

    private static final int NUM_PLAYERS = 10; // Number of players

    @Override
    public void start(Stage primaryStage) {
        VBox leftTeamContainer = new VBox(10);

        PlayerCard playerCard = new PlayerCard(
                new Image(AssetsFilePathConstants.profileIconFilePath),
                new Image(AssetsFilePathConstants.profileIconFilePath),
                new Image(AssetsFilePathConstants.profileIconFilePath),
                "aaa",
                new ArrayList<String>()
        );
        leftTeamContainer.getChildren().add(playerCard);

        Scene scene = new Scene(leftTeamContainer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void animateCards(VBox playerCards) {
        // Get the width of the scene
        double sceneWidth = playerCards.getScene().getWidth();

        for (int i = 0; i < playerCards.getChildren().size(); i++) {
            PlayerInfoCard card = (PlayerInfoCard) playerCards.getChildren().get(i);

            // Animate the translation to the edge of the screen
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), card);
            translateTransition.setToX(i % 2 == 0 ? -sceneWidth : sceneWidth);

            // Animate the scale down of the card
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), card);
            scaleTransition.setToX(0.1);
            scaleTransition.setToY(0.1);

            // Play both animations at the same time
            ParallelTransition parallelTransition = new ParallelTransition(translateTransition, scaleTransition);
            parallelTransition.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
