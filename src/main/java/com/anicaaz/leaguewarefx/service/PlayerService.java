package com.anicaaz.leaguewarefx.service;

import com.anicaaz.leaguewarefx.constants.AssetsFilePathConstants;
import com.anicaaz.leaguewarefx.controller.OverlayController;
import com.anicaaz.leaguewarefx.ui.ingameobj.Item;
import com.anicaaz.leaguewarefx.ui.ingameobj.Player;
import com.anicaaz.leaguewarefx.ui.ingameobj.Spell;
import com.anicaaz.leaguewarefx.ui.ingameobj.SummonerSpells;
import com.anicaaz.leaguewarefx.ui.render.SummonerWSpells;
import com.anicaaz.leaguewarefx.utils.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
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

    public void drawEnemySpellsInfo() {
        for (int i = 0; i < playerList.size(); i++) {
            Player curPlayer = playerList.get(i);
            if (curPlayer.getTeam().equals("CHAOS")) {
                //设置召唤师技能
                SummonerSpells curSummonerSpells = curPlayer.getSummonerSpells();
                Spell spellOne = curSummonerSpells.getSummonerSpellOne();
                Spell spellTwo = curSummonerSpells.getSummonerSpellTwo();
                String spellOneName = spellOne.getDisplayName();
                String spellTwoName = spellTwo.getDisplayName();
                String spellJsonString = FileUtil.jsonFile2String("/com/anicaaz/leaguewarefx/assets/json/summonerSpells.json");
                String spellOneId = FileUtil.getIdFromJsonByName(spellJsonString, spellOneName);
                String spellTwoId = FileUtil.getIdFromJsonByName(spellJsonString, spellTwoName);
                String s = AssetsFilePathConstants.SUMMONERSPELLICONSROOT + spellOneId;
                Image spellImageOne = FileUtil.getImageById(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE, spellOneId);
                Image spellImageTwo = FileUtil.getImageById(AssetsFilePathConstants.SUMMONERSPELLICONSIMAGE, spellTwoId);

                String championName = curPlayer.getChampionName();
                String championJsonString = FileUtil.jsonFile2String("/com/anicaaz/leaguewarefx/assets/json/champion-summary.json");
                String championId = FileUtil.getIdFromJsonByName(championJsonString, championName);
                Image championImage = FileUtil.getImageById(AssetsFilePathConstants.CHAMPIONICONSROOTIMAGE, championId);
                SummonerWSpells summonerWSpells = new SummonerWSpells(championImage, spellImageOne, spellImageTwo);
                OverlayController.drawChampionWSpells(summonerWSpells.getPane());
            }
        }
    }
}
