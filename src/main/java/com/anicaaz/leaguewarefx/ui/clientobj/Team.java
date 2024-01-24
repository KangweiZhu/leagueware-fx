package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private List<Ban> bans;
    private int baronKills;
    private int dominionVictoryScore;
    private int dragonKills;
    private boolean firstBaron;
    private boolean firstBlood;
    private boolean firstDargon; // 注意这里的拼写错误可能是原数据的问题
    private boolean firstInhibitor;
    private boolean firstTower;
    private int inhibitorKills;
    private int riftHeraldKills;
    private int teamId;
    private int towerKills;
    private int vilemawKills;
    private String win;
}