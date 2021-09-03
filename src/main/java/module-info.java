module fr.arnaud.pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.arnaud.pong to javafx.fxml;
    exports fr.arnaud.pong;
}