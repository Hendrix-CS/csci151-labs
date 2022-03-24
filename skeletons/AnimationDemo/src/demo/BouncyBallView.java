package demo;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BouncyBallView extends Parent {

    private BouncyBall ball;
    private Circle c;

    public BouncyBallView(BouncyBall ball) {
        this.ball = ball;

        c = new Circle();
        c.setFill(Color.GREEN);
        c.setStroke(Color.BLACK);
        c.setOnMouseDragged(event -> drag(event, ball));
        c.setOnMousePressed(event -> pressed(event, ball));
        c.setOnMouseReleased(event -> released(event, ball));
        getChildren().add(c);
    }

    // What to do when the mouse is Pressed
    private void pressed(MouseEvent event, BouncyBall b) {
        b.toggleMovement();
        c.setFill(Color.DARKRED);
    }

    // What to do when the mouse is Released
    private void released(MouseEvent event, BouncyBall b) {
        b.toggleMovement();
        c.setFill(Color.DODGERBLUE);
    }

    // What to do when the mouse is Dragged
    private void drag(MouseEvent event, BouncyBall b) {
        b.setX(b.getX() + event.getX());
        b.setY(b.getY() + event.getY());
        update();
    }

    public void update() {
        c.setRadius(ball.getRadius());
        c.setTranslateX(ball.getX());
        c.setTranslateY(ball.getY());
    }
}
