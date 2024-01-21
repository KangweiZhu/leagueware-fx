package com.anicaaz.leaguewarefx.ui.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameData {
    private ActivePlayer activePlayer;
    private List<Player> allPlayers;
    private Events events;
    private GameDetails gameData;
}
