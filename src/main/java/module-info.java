module com.anicaaz.leaguewarefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires jdk.httpserver;
    requires littleproxy;
    requires io.netty.all;

    opens com.anicaaz.leaguewarefx to javafx.fxml;
    exports com.anicaaz.leaguewarefx;
    exports com.anicaaz.leaguewarefx.controller;
    opens com.anicaaz.leaguewarefx.controller to javafx.fxml;
}