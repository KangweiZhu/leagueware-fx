package com.anicaaz.leaguewarefx.ui.clientobj;

import lombok.Data;

import java.util.Map;

@Data
public class Timeline {
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
    private String lane;
    private int participantId;
    private String role;
    private Map<String, Double> xpDiffPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
}