module pigDiceGame {
    requires javafx.fxml;
    requires javafx.controls;
    exports pig.gui;
    opens pig.gui;
    exports pig.model;
    opens pig.model;

}