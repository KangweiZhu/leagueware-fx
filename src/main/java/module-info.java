module com.anicaaz.leaguewarefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires jdk.httpserver;
    requires littleproxy;
    requires io.netty.all;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires com.google.gson;

    opens com.anicaaz.leaguewarefx to javafx.fxml;
    opens com.anicaaz.leaguewarefx.controller to javafx.fxml;
    opens com.anicaaz.leaguewarefx.utils to javafx.fxml;

    exports com.anicaaz.leaguewarefx;
    exports com.anicaaz.leaguewarefx.controller;
    exports com.anicaaz.leaguewarefx.utils;
    exports com.anicaaz.leaguewarefx.constants;
    exports com.anicaaz.leaguewarefx.uiPlayground;
    exports com.anicaaz.leaguewarefx.ui.obj;
}