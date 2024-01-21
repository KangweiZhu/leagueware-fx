package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPlayers {
    private List<Player> allPlayers;
}
