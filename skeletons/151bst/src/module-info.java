module binary.search.trees {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;

    exports binarytree.core;
    opens binarytree.gui;
}