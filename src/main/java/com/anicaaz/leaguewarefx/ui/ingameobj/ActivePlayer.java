package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivePlayer {
    private Abilities abilities;
    private ChampionStats championStats;
    private double currentGold;
    private FullRunes fullRunes;
    private int level;
    private String summonerName;
    private boolean teamRelativeColors;
}
