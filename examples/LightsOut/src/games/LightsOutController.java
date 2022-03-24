package games;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LightsOutController {

    @FXML
    private Button newGame;

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    private int moveCount;

    private LightsOut game;

    @FXML
    public void initialize() {
        game = new LightsOut(5);
    }

    @FXML
    public void reset() {
        game = new LightsOut(5);
        game.randomMoves(20);
        moveCount = 0;
        updateView();
    }

    public void updateView() {
        pane.getChildren().clear();
        int size = game.getSize();

        double cellWidth = pane.getWidth() / size;
        double cellHeight = pane.getHeight() / size;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Rectangle cell = new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                Position p = new Position(x, y);
                if (game.getState(x, y)) {
                    cell.setFill(Color.LIGHTGOLDENRODYELLOW);
                } else {
                    cell.setFill(Color.FIREBRICK);
                }
                cell.setStroke(Color.BLACK);
                cell.setOnMouseClicked(event -> {
                    game.toggle(p.getX(), p.getY());
                    moveCount++;
                    updateView();
                });
                pane.getChildren().add(cell);
            }
        }
        if (game.isSolved()) {
            label.setText("SOLVED!");
        } else {
            label.setText("Moves: " + moveCount);
        }
    }
}
