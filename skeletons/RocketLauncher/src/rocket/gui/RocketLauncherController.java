package rocket.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import rocket.model.Rocket;

public class RocketLauncherController {

    @FXML
    private Pane centerPane;

    private RocketView rocketView;

    private Rocket rocket;

    @FXML
    public void initialize() {
        rocket = new Rocket();
        rocketView = new RocketView(40, 100, 300, rocket);
        centerPane.getChildren().add(rocketView);
        updateViews();
    }

    @FXML
    public void blastOff() {
        rocket.blastOff();
        updateViews();
    }

    @FXML
    public void land() {
        rocket.land();
        updateViews();
    }

    public void updateViews() {
        rocketView.update();
    }
}
