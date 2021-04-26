module maze151 {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;

    exports maze.model to junit;
    opens maze.gui;
}