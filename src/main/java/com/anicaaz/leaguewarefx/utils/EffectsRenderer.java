package com.anicaaz.leaguewarefx.utils;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class EffectsRenderer {
    public static void addFadeInFadeOut(Double seconds, Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(seconds), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
