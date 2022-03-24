package rocket.gui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import rocket.model.Canister;
import rocket.model.Rocket;

public class CanisterView extends Parent {

    private Rectangle[] pellets;
    private Rectangle shell;

    public static final int PELLET_HEIGHT = 15;

    private Canister can;
    private Rocket rocket;

    private int spot;

    public CanisterView(double x, double y, Canister can, Rocket rocket, int spot) {
        setTranslateX(x);
        setTranslateY(y);
        this.can = can;
        this.rocket = rocket;
        this.spot = spot;

        shell = new Rectangle();
        shell.setWidth(50);
        shell.setHeight(10 + Canister.CANISTER_SIZE * (PELLET_HEIGHT + 3));
        shell.setArcWidth(20);
        shell.setArcHeight(20);
        shell.setFill(Color.GREY);
        getChildren().add(shell);

        pellets = new Rectangle[Canister.CANISTER_SIZE];
        for (int i = 0; i < Canister.CANISTER_SIZE; i++) {
            Rectangle r = new Rectangle();
            r.setTranslateX(5);
            r.setTranslateY(5 + i * (PELLET_HEIGHT + 3));
            r.setWidth(40);
            r.setHeight(PELLET_HEIGHT);
            r.setArcWidth(5);
            r.setArcHeight(5);
            r.setFill(Color.DARKRED);
            getChildren().add(r);
            pellets[i] = r;
        }
    }

    public void update() {
        for (int i = 0; i < Canister.CANISTER_SIZE; i++) {
            if (i < can.getPellets()) {
                pellets[i].setFill(Color.LAWNGREEN);
            } else {
                pellets[i].setFill(Color.DARKRED);
            }
        }
    }
}
