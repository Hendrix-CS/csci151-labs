package snap;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SnapToGridController {

    @FXML
    Pane pane;

    private int size = 200;
    private int spots = 5;
    private int squareSize = size / spots;

    private ArrayList<Piece> pieces;
    private Rectangle[][] grid;

    @FXML
    public void initialize() {
        grid = new Rectangle[spots][spots];
        for (int i = 0; i < size; i += squareSize) {
            for (int j = 0; j < size; j += squareSize) {
                Rectangle r = new Rectangle(i, j, squareSize, squareSize);
                grid[i / squareSize][j / squareSize] = r;
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);
            }
        }
        pieces = new ArrayList<Piece>();
        for (int i = 0; i < 5; i++) {
            Circle c = new Circle();
            c.setFill(Color.GREEN);
            c.setStroke(Color.BLACK);
            double radius = squareSize / 3.0;
            int x = squareSize / 2 + squareSize * (int)(Math.random() * spots);
            int y = squareSize / 2 + squareSize * (int)(Math.random() * spots);
            Piece p = new Piece(x, y, radius, c);
            pieces.add(p);

            c.setOnMousePressed(event -> pressed(event, p));
            c.setOnMouseDragged(event -> dragged(event, p));
            c.setOnMouseReleased(event -> released(event, p));

            pane.getChildren().add(c);
            p.draw();
        }
    }

    public void pressed(MouseEvent event, Piece p) {
        p.setColor(Color.DARKGOLDENROD);
    }

    public void dragged(MouseEvent event, Piece p) {
        p.setX(p.getX() + event.getX());
        p.setY(p.getY() + event.getY());
        p.draw();
    }

    public void released(MouseEvent event, Piece p) {
        int gridx = (int)p.getX() / squareSize;
        int gridy = (int)p.getY() / squareSize;
        grid[gridx][gridy].setFill(Color.CRIMSON);
        p.setX(squareSize / 2 + squareSize * gridx);
        p.setY(squareSize / 2 + squareSize * gridy);
        p.draw();
    }
}
