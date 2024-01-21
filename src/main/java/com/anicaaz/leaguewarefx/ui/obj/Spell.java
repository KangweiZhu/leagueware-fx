package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spell {
    private String displayName;
    private String rawDescription;
    private String rawDisplayName;
}