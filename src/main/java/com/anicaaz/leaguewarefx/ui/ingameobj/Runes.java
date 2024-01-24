package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Runes {
    private Keystone keystone;
    private RuneTree primaryRuneTree;
    private RuneTree secondaryRuneTree;
}