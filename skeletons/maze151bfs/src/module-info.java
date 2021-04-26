module maze151 {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;

    exports maze.model to junit;
    exports maze.searchers to junit;
    opens maze.gui;
}