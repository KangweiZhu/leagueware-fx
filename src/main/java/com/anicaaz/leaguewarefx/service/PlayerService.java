package com.anicaaz.leaguewarefx.service;

import com.anicaaz.leaguewarefx.controller.OverlayController;
import com.anicaaz.leaguewarefx.ui.ingameobj.Item;
import com.anicaaz.leaguewarefx.ui.ingameobj.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerService {

    private List<Player> playerList;
    private List<Player> prevplayerList;
    private OverlayController overlayController;
    public void checkPlayerItemUpdate() {
        for (int i = 0; i < playerList.size(); i++) {
            Player currentPlayer = playerList.get(i);
            Player prevCurrentPlayer = prevplayerList. get(i);
            if (currentPlayer.getTeam().equals("CHAOS")) {
                List<Item> currentPlayerItems = currentPlayer.getItems();
                int count = 0;
                for (Item item : currentPlayerItems) {
                    count += item.getPrice();
                }
                List<Item> prevCurrentPlayerItems = prevCurrentPlayer.getItems();
                int prevCount = 0;
                for (Item item : prevCurrentPlayerItems) {
                    prevCount += item.getPrice();

                }
                if (prevCount != count) {
                    OverlayController.addNotification(currentPlayer.getChampionName() + "装备更新了");
                }
            }
        }
    }
}
