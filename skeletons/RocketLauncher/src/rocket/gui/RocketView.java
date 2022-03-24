package rocket.gui;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import rocket.model.Canister;
import rocket.model.Rocket;

public class RocketView extends Parent {

    private Rocket rocket;
    private Rectangle shell;
    private Rectangle cone;
    private CanisterView[] cvs;
    private double airY;
    private double groundY;

    public RocketView(double x, double airY, double groundY, Rocket rocket) {
        this.airY = airY;
        this.groundY = groundY;

        setTranslateX(x);
        setTranslateY(groundY);
        this.rocket = rocket;

        cone = new Rectangle();
        cone.setTranslateX(47);
        cone.setTranslateY(-47);
        cone.setRotate(45);
        cone.setWidth(141);
        cone.setHeight(141);
        cone.setArcWidth(20);
        cone.setArcHeight(20);
        cone.setFill(Color.GREY);
        getChildren().add(cone);

        shell = new Rectangle();
        shell.setTranslateX(15);
        shell.setWidth(200);
        shell.setHeight(200);
        shell.setArcWidth(20);
        shell.setArcHeight(20);
        shell.setFill(Color.LIGHTGREY);
        getChildren().add(shell);

        cvs = new CanisterView[Rocket.FUEL_SPOTS];
        for (int i = 0; i < Rocket.FUEL_SPOTS; i++) {
            resetCanister(i);
        }
    }

    public void resetCanister(int spot) {
        if (rocket.addFuelCanister(spot)) {
            if (cvs[spot] != null) {
                getChildren().remove(cvs[spot]);
            }
            Canister can = rocket.getCanister(spot);
            CanisterView cv = new CanisterView(30 + 60 * spot, 140, can, rocket, spot);
            getChildren().add(cv);
            cvs[spot] = cv;
            cv.setOnMouseClicked(event -> resetCanister(spot));
            update();
        }
    }

    public void update() {
        if (rocket.isOnGround()) {
            setTranslateY(groundY);
        } else {
            setTranslateY(airY);
        }

        for (int i = 0; i < Rocket.FUEL_SPOTS; i++) {
            if (cvs[i] != null) {
                cvs[i].update();
            }
        }
    }
}
