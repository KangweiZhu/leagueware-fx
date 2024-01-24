package com.anicaaz.leaguewarefx.ui.ingameobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullRunes {
    private List<Rune> generalRunes;
    private Rune keystone;
    private RuneTree primaryRuneTree;
    private RuneTree secondaryRuneTree;
    private List<StatRune> statRunes;
}
