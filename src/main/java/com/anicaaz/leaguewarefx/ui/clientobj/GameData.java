package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

@Data
public class GameData {
    private long accountId;
    private Games games;
    private String platformId;
}
