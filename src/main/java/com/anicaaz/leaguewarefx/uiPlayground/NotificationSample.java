package com.anicaaz.leaguewarefx.uiPlayground;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

public class NotificationSample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label notificationLabel = new Label("Blue Sentinel Dead!");
        notificationLabel.getStyleClass().add("notification-label");
        notificationLabel.setMinHeight(50);
        notificationLabel.setMinWidth(50);
        HBox root = new HBox();
        Image image = new Image(AssetsFilePathConstants.profileIconFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        root.getChildren().addAll(imageView, notificationLabel);
        Scene scene = new Scene(root, 1920 - 300, 50);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/com/anicaaz/leaguewarefx/assets/css/style.css")));
        scene.setFill(null);
        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.TRANSPARENT);
        notificationStage.setScene(scene);
        notificationStage.setAlwaysOnTop(true);
        notificationStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(event -> notificationStage.hide());
        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

