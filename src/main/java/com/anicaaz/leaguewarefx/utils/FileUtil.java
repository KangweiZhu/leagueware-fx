package com.anicaaz.leaguewarefx.utils;

import java.io.File;

/**
 * 文件工具类
 * @author anicaa
 */
public class FileUtil {

    /**
     * Check if a file exists in a certain path or not.
     * @return true if exists, false if not exists.
     */
    public static boolean checkIfFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
