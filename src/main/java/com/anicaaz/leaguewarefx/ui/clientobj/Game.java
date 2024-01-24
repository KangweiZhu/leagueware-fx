package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

import java.util.List;

@Data
public class Game {
    private long gameCreation;
    private String gameCreationDate;
    private int gameDuration;
    private long gameId;
    private String gameMode;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private List<ParticipantIdentity> participantIdentities;
    private List<Participant> participants;
    private String platformId;
    private int queueId;
    private int seasonId;
    private List<Team> teams;
}
