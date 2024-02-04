package com.anicaaz.leaguewarefx.constants;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GlobalVariables {
    public static volatile Double GAME_TIME = 0.00;
    public static volatile String SPELL_COUNTER_STRING = "";
    public static volatile String ENEMY_ONE_SPELL_ONE_END = "";
    public static volatile String ENEMY_ONE_SPELL_TWO_END = "";
    public static volatile String ENEMY_TWO_SPELL_ONE_END = "";
    public static volatile String ENEMY_TWO_SPELL_TWO_END = "";
    public static volatile String ENEMY_THREE_SPELL_ONE_END = "";
    public static volatile String ENEMY_THREE_SPELL_TWO_END = "";
    public static volatile String ENEMY_FOUR_SPELL_ONE_END = "";
    public static volatile String ENEMY_FOUR_SPELL_TWO_END = "";
    public static volatile String ENEMY_FIVE_SPELL_ONE_END = "";
    public static volatile String ENEMY_FIVE_SPELL_TWO_END = "";
    public static final Map<Integer, String[]> SPELL_COUNTER_MAP = new HashMap<>();

    public static void assignVariable(String variableName, String value) {
        try {
            GlobalVariables.class.getField(variableName).set(null, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static String spellCounterMapToString() {
        StringBuilder sb = new StringBuilder();
        SPELL_COUNTER_MAP.forEach((key, value) -> {
            if (value != null) {
                for (int i = 0; i < value.length; i++) {
                    if (value[i] != null) {
                        sb.append(value[i]).append(" ");
                    }
                    if (i < value.length - 1) {
                        sb.append(". ");
                    }
                }
            }
        });
        return sb.toString();
    }
}
