package com.anicaaz.leaguewarefx.ui.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abilities {
    @JsonProperty("E")
    private Ability E;
    @JsonProperty("Passive")
    private Ability Passive;
    @JsonProperty("Q")
    private Ability Q;
    @JsonProperty("R")
    private Ability R;
    @JsonProperty("W")
    private Ability W;
}
