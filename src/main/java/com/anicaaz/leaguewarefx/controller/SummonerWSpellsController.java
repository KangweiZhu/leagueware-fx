package com.anicaaz.leaguewarefx.controller;

import com.anicaaz.leaguewarefx.constants.GlobalVariables;
import com.anicaaz.leaguewarefx.utils.LogUtil;
import com.anicaaz.leaguewarefx.utils.OSUtil;
import com.anicaaz.leaguewarefx.utils.TimeUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import lombok.Data;

import static com.anicaaz.leaguewarefx.constants.GlobalVariables.SPELL_COUNTER_MAP;
import static com.anicaaz.leaguewarefx.constants.GlobalVariables.spellCounterMapToString;

@Data
public class SummonerWSpellsController {
    @FXML
    private ImageView championImage;
    @FXML
    private ImageView summonerSpell1;
    @FXML
    private ImageView summonerSpell2;
    private String championName;
    private String spellOneName;
    private String spellTwoName;
    private Integer enemyId;

    public void updateAll(Image championIcons, Image summonerSpell1Image, Image summonerSpell2Image, String championName, String spellOneName, String spellTwoName, Integer enemyId) {
        this.championImage.setImage(championIcons);
        this.summonerSpell1.setImage(summonerSpell1Image);
        this.summonerSpell2.setImage(summonerSpell2Image);
        this.championName = championName;
        this.spellOneName = spellOneName;
        this.spellTwoName = spellTwoName;
        this.enemyId = enemyId;
    }

    public void spellOneClickedListener() {
        processSpellClick(0);
    }

    public void spellTwoClickedListener() {
        processSpellClick(1);
    }

    public void championImageClickedListener() {
        new Thread(() -> {
            prepareSpellInput(2);
        }).start();
    }

    private void processSpellClick(int spellId) {
        new Thread(() -> {
            String input = prepareSpellInput(spellId);
            updateSpellCounterMap(input, spellId);
        }).start();
    }

    private String prepareSpellInput(int spellId) {
        OSUtil.focusGameWindow("League of Legends (TM) Client");
        TimeUtils.sleep(1000);
        OSUtil.pressEnter();
        String input;
        if (spellId == 2) {
            input = spellCounterMapToString();
        } else {
            input = filterById(spellId);
        }
        OSUtil.typeString(input);
        OSUtil.selectAllAndCopy();
        OSUtil.pressEnter();
        return input;
    }

    private void updateSpellCounterMap(String input, int spellId) {
        SPELL_COUNTER_MAP.computeIfAbsent(enemyId, k -> new String[2])[spellId] = input;
    }

    public String filterById(Integer spellId) {
        String endTime = TimeUtils.gameTimeConverter(GlobalVariables.GAME_TIME + 300);
        switch (enemyId) {
            case 0: assignCooldownTime(spellId, "ENEMY_ONE_SPELL_ONE_END", "ENEMY_ONE_SPELL_TWO_END", endTime); break;
            case 1: assignCooldownTime(spellId, "ENEMY_TWO_SPELL_ONE_END", "ENEMY_TWO_SPELL_TWO_END", endTime); break;
            case 2: assignCooldownTime(spellId, "ENEMY_THREE_SPELL_ONE_END", "ENEMY_THREE_SPELL_TWO_END", endTime); break;
            case 3: assignCooldownTime(spellId, "ENEMY_FOUR_SPELL_ONE_END", "ENEMY_FOUR_SPELL_TWO_END", endTime); break;
            case 4: assignCooldownTime(spellId, "ENEMY_FIVE_SPELL_ONE_END", "ENEMY_FIVE_SPELL_TWO_END", endTime); break;
            default: LogUtil.log("Attempting to set the spell cooldown of a non-existing summoner or dummy."); return "";
        }
        return endTime + getSpellName(spellId);
    }

    private void assignCooldownTime(Integer spellMode, String varOne, String varTwo, String endTime) {
        if (spellMode == 0) {
            GlobalVariables.assignVariable(varOne, endTime);
        } else {
            GlobalVariables.assignVariable(varTwo, endTime);
        }
    }

    private String getSpellName(Integer spellMode) {
        return spellMode == 0 ? championName + " " + spellOneName : championName + " " + spellTwoName;
    }
}
