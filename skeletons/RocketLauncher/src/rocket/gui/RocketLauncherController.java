package rocket.gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import rocket.model.Rocket;

public class RocketLauncherController {

    @FXML
    private Pane centerPane;

    @FXML
    private Button blastOff;

    @FXML
    private Button land;

    private RocketView rocketView;

    private Rocket rocket;

    private final int airY = 100;
    private final int groundY = 300;

    private Movement clock;

    private class Movement extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;

        private double dy = -5;
        private double current = groundY;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                if ((dy < 0 && current > airY) ||
                        (dy > 0 && current < groundY)) {
                    current += dy;
                    blastOff.setDisable(true);
                    land.setDisable(true);
                } else if ((dy < 0 && current <= airY) ||
                        (dy > 0 && current >= groundY)) {
                    dy *= -1;
                    blastOff.setDisable(false);
                    land.setDisable(false);
                    stop();
                }
                rocketView.setTranslateY(current);
                updateViews();
                last = now;
            }
        }
    }

    @FXML
    public void initialize() {
        rocket = new Rocket();
        rocketView = new RocketView(40, airY, groundY, rocket);
        centerPane.getChildren().add(rocketView);
        clock = new Movement();
        updateViews();
    }

    @FXML
    public void blastOff() {
        if (rocket.blastOff()) {
            clock.start();
        }
        updateViews();
    }

    @FXML
    public void land() {
        if (rocket.land()) {
            clock.start();
        }
        updateViews();
    }

    public void updateViews() {
        rocketView.update();
    }
}
