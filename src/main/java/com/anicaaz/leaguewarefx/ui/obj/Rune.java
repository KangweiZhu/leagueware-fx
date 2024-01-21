package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rune {
    private String displayName;
    private int id;
    private String rawDescription;
    private String rawDisplayName;
}
