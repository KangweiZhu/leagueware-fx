module com.anicaaz.leaguewarefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires jdk.httpserver;
    requires littleproxy;
    requires io.netty.all;
    requires com.anicaaz.leaguewarefxMain;

    opens com.anicaaz.leaguewarefx to javafx.fxml;
    opens com.anicaaz.leaguewarefx.testUI to javafx.fxml;
    opens com.anicaaz.leaguewarefx.testUtil to javafx.fxml;

}
