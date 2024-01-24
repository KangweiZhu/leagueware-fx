package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

@Data
public class Participant {
    private int championId;
    private String highestAchievedSeasonTier;
    private int participantId;
    private int spell1Id;
    private int spell2Id;
    private Stats stats;
    private int teamId;
    private Timeline timeline;

    // getters and setters
}