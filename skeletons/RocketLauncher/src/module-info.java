module RocketLauncher {
    requires javafx.fxml;
    requires javafx.controls;
    exports rocket.gui;
    exports rocket.model;
    opens rocket.model;
    opens rocket.gui;
}