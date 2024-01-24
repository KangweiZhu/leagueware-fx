package com.anicaaz.leaguewarefx.ui.render;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PlayerCard extends Pane {
    private ImageView champImage;
    private ImageView rankImage;
    private ImageView avatar;
    private String playerName;
    private List<String> rankedInfo;

    public PlayerCard(Image champImg, Image rankImg, Image avatarImg, String playerName, List<String> rankedInfo) {
        this.rankImage = new ImageView(rankImg);
        this.champImage = new ImageView(champImg);
        this.avatar = new ImageView(avatarImg);
        this.playerName = playerName;
        this.rankedInfo = rankedInfo;

        champImage.setFitHeight(220);
        champImage.setFitWidth(110);

        this.getChildren().add(champImage);
    }
}
