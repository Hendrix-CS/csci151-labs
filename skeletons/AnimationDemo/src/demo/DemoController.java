package demo;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class DemoController {

	@FXML
	private Pane pane;

	private ArrayList<BouncyBall> juggling;
	private ArrayList<BouncyBallView> bvs;

	private Movement clock;

	private class Movement extends AnimationTimer {

		private long FRAMES_PER_SEC = 50L;
		private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

		private long last = 0;
		private ArrayList<BouncyBall> bs;

		public void setBalls(ArrayList<BouncyBall> bs) {
			this.bs = bs;
		}

		@Override
		public void handle(long now) {
			if (now - last > INTERVAL) {
				for (BouncyBall b : bs) {
					b.move();
				}
				updateViews();
				last = now;
			}
		}
	}

	@FXML
	public void initialize() {
		juggling = new ArrayList<BouncyBall>();
		bvs = new ArrayList<BouncyBallView>();

		for (int i = 0; i < 5; i++) {
			makeBouncyBall();
		}

		clock = new Movement();
		clock.setBalls(juggling);
		clock.start();
	}

	private void makeBouncyBall() {
		BouncyBall b = new BouncyBall(20);
		juggling.add(b);

		BouncyBallView bv = new BouncyBallView(b);
		bvs.add(bv);
		pane.getChildren().add(bv);

		updateViews();
	}

	private void updateViews() {
		for (BouncyBallView bv : bvs) {
			bv.update();
		}
	}
}
