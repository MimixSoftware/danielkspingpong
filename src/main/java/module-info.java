module com.danielkspingpong.danielkspingpong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.danielkspingpong.danielkspingpong to javafx.fxml;
    exports com.danielkspingpong.danielkspingpong;
    exports com.danielkspingpong.danielkspingpong.model;
    opens com.danielkspingpong.danielkspingpong.model to javafx.fxml;
}