package com.anicaaz.leaguewarefx.utils;

import java.util.Base64;

public class EncodingUtil {

    /**
     * Encode the String input base64 format
     * @param input String
     * @return  Encoded String
     */
    public static String encodeToBase64(String input) {
        return new String(Base64.getEncoder().encode(input.getBytes()));
    }
}
