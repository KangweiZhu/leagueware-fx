package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.sun.jna.platform.win32.KnownFolders;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.concurrent.ConcurrentLinkedQueue;

public class OverlayController {
    private static final VBox notificationContainer = new VBox(15);
    private static final ConcurrentLinkedQueue<HBox> notificationQueue = new ConcurrentLinkedQueue<>();
    private static final Duration displayTime = Duration.seconds(5);
    private static final VBox championIconWSpellsContainer = new VBox(30);
    private static final Pane pane = new Pane();
    private static Stage notificationStage;

    static {
        Platform.runLater(() -> {
            Rectangle2D screenBound = Screen.getPrimary().getBounds();

            notificationStage = new Stage(StageStyle.TRANSPARENT);
            notificationStage.setMinHeight(screenBound.getHeight());
            notificationStage.setMinWidth(screenBound.getWidth());
            notificationStage.setAlwaysOnTop(true);

            notificationContainer.setMinWidth(250);
            notificationContainer.setLayoutX((screenBound.getWidth() - notificationContainer.getMinWidth()) / 2);
            notificationContainer.setLayoutY(40);

            championIconWSpellsContainer.setLayoutX(10);
            championIconWSpellsContainer.setLayoutY((screenBound.getHeight() - championIconWSpellsContainer.getPrefHeight()) / 2);

            pane.setStyle("-fx-background-color: transparent");
            pane.getChildren().addAll(notificationContainer, championIconWSpellsContainer);

            Scene scene = new Scene(pane);
            scene.setFill(null);
            scene.getStylesheets().add(OverlayController.class.getResource("/com/anicaaz/leaguewarefx/assets/css/style.css").toExternalForm());

            notificationStage.setScene(scene);
            notificationStage.show();
        });
    }

    public static void addNotification(String messageText) {
        Platform.runLater(() -> {
            HBox notificationItem = getNotificationItem(messageText);
            notificationQueue.add(notificationItem);
            notificationContainer.getChildren().add(notificationItem);

            // Remove the notification after a delay
            new Thread(() -> {
                try {
                    Thread.sleep((long) displayTime.toMillis());
                    Platform.runLater(() -> {
                        notificationContainer.getChildren().remove(notificationItem);
                        notificationQueue.remove(notificationItem);
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        });
    }

    public static void drawChampionWSpells(Pane championWSpellsView)    {
        Platform.runLater(() -> {
            championWSpellsView.setStyle("-fx-background-color: transparent");
            championIconWSpellsContainer.getChildren().add(championWSpellsView);
        });

    }

    private static HBox getNotificationItem(String messageText) {
        Label notificationLabel = new Label(messageText);
        notificationLabel.setMinSize(50, 50);
        notificationLabel.getStyleClass().add("notification-label");

        Image image = new Image(AssetsFilePathConstants.profileIconFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        HBox hBox = new HBox(imageView, notificationLabel);
        return hBox;
    }
}
