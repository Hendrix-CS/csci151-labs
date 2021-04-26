package demo;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DemoController {

	@FXML
	private Pane pane;

	private ArrayList<Ball> juggling;

	private Movement clock;

	private class Movement extends AnimationTimer {

		private long FRAMES_PER_SEC = 50L;
		private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

		private long last = 0;
		private ArrayList<Ball> bs;

		public void setBalls(ArrayList<Ball> bs) {
			this.bs = bs;
		}

		@Override
		public void handle(long now) {
			if (now - last > INTERVAL) {
				for (Ball b : bs) {
					b.move();
					b.draw();
				}
				last = now;
			}
		}
	}

	@FXML
	public void initialize() {
		juggling = new ArrayList<Ball>();

		for (int i = 0; i < 5; i++) {
			makeCircle();
		}

		clock = new Movement();
		clock.setBalls(juggling);
		clock.start();
	}

	private void makeCircle() {
		Circle c = new Circle();
		c.setFill(Color.GREEN);
		c.setStroke(Color.BLACK);
		Ball b = new Ball(10, c, pane);
		c.setOnMouseDragged(event -> drag(event, b));
		c.setOnMousePressed(event -> pressed(event, b));
		c.setOnMouseReleased(event -> released(event, b));
		juggling.add(b);
		pane.getChildren().add(c);
	}

	// What to do when the mouse is Pressed
	private void pressed(MouseEvent event, Ball b) {
		b.toggleMovement();
		b.setColor(Color.DARKRED);
		event.consume();
	}

	// What to do when the mouse is Released
	private void released(MouseEvent event, Ball b) {
		b.toggleMovement();
		b.setColor(Color.DODGERBLUE);
	}

	// What to do when the mouse is Dragged
	private void drag(MouseEvent event, Ball b) {
		b.setX(b.getX() + event.getX());
		b.setY(b.getY() + event.getY());
		b.draw();
	}
}
