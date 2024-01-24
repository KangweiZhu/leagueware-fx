package com.anicaaz.leaguewarefx.ui.ingameobj;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private List<String> Assisters;
    private int EventID;
    private String EventName;
    private double EventTime;
    private String KillerName;
    private String VictimName;
}
