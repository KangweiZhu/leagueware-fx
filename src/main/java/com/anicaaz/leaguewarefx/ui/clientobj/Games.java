package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

import java.util.List;

@Data
public class Games {
    private String gameBeginDate;
    private int gameCount;
    private String gameEndDate;
    private int gameIndexBegin;
    private int gameIndexEnd;
    private List<Game> games;
}