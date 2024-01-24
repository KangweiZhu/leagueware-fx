package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

@Data
public class Player {
    private long accountId;
    private long currentAccountId;
    private String currentPlatformId;
    private String gameName;
    private String matchHistoryUri;
    private String platformId;
    private int profileIcon;
    private String puuid;
    private long summonerId;
    private String summonerName;
    private String tagLine;
}