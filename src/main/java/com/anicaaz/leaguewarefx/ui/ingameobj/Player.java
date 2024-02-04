package com.anicaaz.leaguewarefx.ui.ingameobj;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    @JsonProperty("championName")
    private String championName;
    @JsonProperty("isBot")
    private boolean isBot;
    @JsonProperty("isDead")
    private boolean isDead;
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("level")
    private int level;
    @JsonProperty("position")
    private String position;
    @JsonProperty("rawChampionName")
    private String rawChampionName;
    @JsonProperty("rawSkinName")
    private String rawSkinName;
    @JsonProperty("respawnTimer")
    private double respawnTimer;
    @JsonProperty("runes")
    private Runes runes;
    @JsonProperty("scores")
    private Scores scores;
    @JsonProperty("skinID")
    private int skinID;
    @JsonProperty("skinName")
    private String skinName;
    @JsonProperty("summonerName")
    private String summonerName;
    @JsonProperty("summonerSpells")
    private SummonerSpells summonerSpells;
    @JsonProperty("team")
    private String team;
    private Integer playerId;
}

