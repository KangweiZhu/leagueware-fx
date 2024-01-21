module com.anicaaz.leaguewarefxmembershiponly {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.anicaaz.leaguewarefxmembershiponly to javafx.fxml;
    exports com.anicaaz.leaguewarefxmembershiponly;
}