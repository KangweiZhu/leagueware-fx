package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDetails {
    private String gameMode;
    private double gameTime;
    private String mapName;
    private int mapNumber;
    private String mapTerrain;
}
