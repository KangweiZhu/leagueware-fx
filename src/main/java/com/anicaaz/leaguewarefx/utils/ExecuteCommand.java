package com.anicaaz.leaguewarefx.utils;

import com.anicaaz.leaguewarefx.LeagueWareFXStarter;
import com.anicaaz.leaguewarefx.constants.PlatformConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommand {

    /**
     * Get the token of current running lcu process.
     * @return The Remoting-Auth-Token of cmdline output.
     */
    public static String getRemoteAuthToken() {
        ProcessBuilder processBuilder = null;
        if (LeagueWareFXStarter.RUNNING_PLATFORM == PlatformConstants.PLATFORM_LINUX) {
            processBuilder = new ProcessBuilder("bash", "-c", "ps -eo cmd | grep 'LeagueClientUx.exe'");
        } else if (LeagueWareFXStarter.RUNNING_PLATFORM == PlatformConstants.PLATFORM_WINDOWS) {
           processBuilder = new ProcessBuilder("cmd.exe", "/c", "wmic PROCESS WHERE name='LeagueClientUx.exe' GET commandline");
        }
        String remoteAuthToken = null;
        try {
            Process lcuProcess = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(lcuProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("--remoting-auth-token")) {
                    remoteAuthToken = line.split("--remoting-auth-token=")[1].split(" ")[0];
                    remoteAuthToken = remoteAuthToken.replaceAll("\"", ""); // windows平台有引号问题
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return remoteAuthToken;
    }

    /**
     * Get the port that current lcu in running on
     * @return The port that current lcu in running on
     */
    public static String getAppPort() {
        ProcessBuilder processBuilder = null;
        if (LeagueWareFXStarter.RUNNING_PLATFORM == PlatformConstants.PLATFORM_LINUX) {
            processBuilder = new ProcessBuilder("bash", "-c", "ps -eo cmd | grep 'LeagueClientUx.exe'");
        } else if (LeagueWareFXStarter.RUNNING_PLATFORM == PlatformConstants.PLATFORM_WINDOWS) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "wmic PROCESS WHERE name='LeagueClientUx.exe' GET commandline");
        }
        String appPort = null;
        try {
            Process process = processBuilder.start();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine())!= null) {
                if (line.contains("--app-port")) {
                    appPort = line.split("--app-port=")[1].split(" ")[0];
                    appPort = appPort.replaceAll("\"",""); // windows平台存在引号问题
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return appPort;
    }
}
