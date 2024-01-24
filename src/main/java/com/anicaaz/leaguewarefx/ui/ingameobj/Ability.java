package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    private int abilityLevel;
    private String displayName;
    private String id;
    private String rawDescription;
    private String rawDisplayName;
}
