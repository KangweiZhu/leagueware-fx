package com.anicaaz.leaguewarefx.uiPlayground;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.sun.jna.platform.win32.KnownFolders;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.concurrent.ConcurrentLinkedQueue;

public class AnimatedNotificationSample extends Application {

    private final ConcurrentLinkedQueue<HBox> notificationQueue = new ConcurrentLinkedQueue<>();
    private final VBox notificationContainer = new VBox(10); // 5 is the spacing between messages
    private final Duration displayTime = Duration.seconds(5);
    private Stage notificationStage;

    @Override
    public void start(Stage primaryStage) {
        notificationStage = new Stage(StageStyle.TRANSPARENT);
        notificationStage.setAlwaysOnTop(true);

        Scene scene = new Scene(notificationContainer);
        scene.setFill(null);
        // Assume the CSS is correctly set up in the same package
        scene.getStylesheets().add(getClass().getResource("/com/anicaaz/leaguewarefx/assets/css/style.css").toExternalForm());

        notificationStage.setScene(scene);
        notificationStage.show();

        // Example of adding notifications
        addNotification("Blue Sentinel Dead!");
        new Thread(() -> {
            try {
                // Simulate asynchronous arrival of messages
                Thread.sleep(3000);
                Platform.runLater(() -> addNotification("Red Brambleback Alive!"));Thread.sleep(3000);
                Platform.runLater(() -> addNotification("Red Brambleback Alive!"));Thread.sleep(3000);
                Platform.runLater(() -> addNotification("Red Brambleback Alive!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void addNotification(String messageText) {
        Label notificationLabel = new Label(messageText);
        notificationLabel.setMinSize(50, 50);
        notificationLabel.getStyleClass().add("notification-label");
        Image image = new Image(AssetsFilePathConstants.profileIconFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(imageView, notificationLabel);
        notificationQueue.add(hBox);
        notificationContainer.getChildren().add(hBox);

        // Remove the notification after a delay
        new Thread(() -> {
            try {
                Thread.sleep((long) displayTime.toMillis());
                Platform.runLater(() -> {
                    notificationContainer.getChildren().remove(hBox);
                    notificationQueue.remove(hBox);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void adjustNotifications() {
        // Slide up any notifications
        if (!notificationQueue.isEmpty()) {
            // Calculate the distance to move up, which is the height of a single notification plus spacing
            double distance = -notificationContainer.getChildren().get(0).getBoundsInParent().getHeight() - notificationContainer.getSpacing();

            for (HBox hBox : notificationQueue) {
                TranslateTransition transition = new TranslateTransition(Duration.millis(2), hBox);
                transition.setByY(distance);
                transition.play();
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
