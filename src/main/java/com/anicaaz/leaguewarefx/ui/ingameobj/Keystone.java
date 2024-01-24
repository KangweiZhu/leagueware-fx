package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keystone {
    private String displayName;
    private int id;
    private String rawDescription;
    private String rawDisplayName;
}