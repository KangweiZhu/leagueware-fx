package com.anicaaz.leaguewarefx.ui.render;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 游戏绘制中的消息提示
 *
 * @author anicaazhu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoCard extends Pane {
    private String info;
    private Label label;
    public InfoCard(String info) {
        this.info = info;

    }
}
