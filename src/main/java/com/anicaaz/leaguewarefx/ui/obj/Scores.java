package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scores {
    private int assists;
    private int creepScore;
    private int deaths;
    private int kills;
    private double wardScore;
}
