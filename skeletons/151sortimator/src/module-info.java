module sortimator {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    exports sorting.algorithms to junit;
    opens sorting.gui;
}