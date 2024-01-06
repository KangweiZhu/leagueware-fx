package com.anicaaz.leaguewarefx.plugin;


import com.anicaaz.leaguewarefx.utils.LogUtil;

/**
 * todo: 实现游戏内喊话插件
 * @author anicaa
 */
public class IngameSpam {

    public static Integer findPid() {
        int returnValue = 0;

        if (returnValue == 0) {
            LogUtil.log(IngameSpam.class.getName(), String.valueOf(IngameSpam.class.getMethods()[0]), "没有找到改进程");
        }
        return returnValue;
    }
}
