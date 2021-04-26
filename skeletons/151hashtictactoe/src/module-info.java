module hashtictactoe {
        requires javafx.graphics;
        requires javafx.fxml;
        requires javafx.controls;
        requires junit;

        exports tictactoe.core;
        exports tictactoe.learn;
        opens tictactoe.gui;
}