package com.anicaaz.leaguewarefx.constants;

/**
 * 请求路径常量值
 * @author anicaa
 */
public class RequestConstants {

    public static final String GET_CURRENT_SUMMONER_NAME = "/lol-summoner/v1/current-summoner";
    public static final String GET_PROFILE_ICON = "/lol-game-data/assets/v1/profile-icons";
    public static final String POST_AUTO_ACCEPT = "/lol-matchmaking/v1/ready-check/accept";
    public static final String POST_AUTO_DECLINE = "/lol-matchmaking/v1/ready-check/decline";
    public static final String GET_MATCH_HISTORY = "/lol-match-history/v1/products/lol/current-summoner/matches";
    public static final String CLIENT_NOTIFICATION = "/lol-ranked/v1/notifications";
    public static final String CHAMPION_LOADING_SCREEN_SKIN = "/lol-game-data/assets/v1/"; //todo:
    public static final String SUMMONER_SPELL_JSON = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/summoner-spells.json";
    public static final String SUMMONER_SPELL_ROOT = "lol-game-data/assets/DATA/Spells/Icons2D/";
    public static final String CHAMPION_ICON_BASE = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/";
    public static final String ALL_GAME_DATA = "/liveclientdata/allgamedata";
    public static final String PLAYER_LIST = "/liveclientdata/playerlist";
    public static final String BASEURL = "https://127.0.0.1:";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final Integer GAME_CLIENT_PORT = 2999;

}
