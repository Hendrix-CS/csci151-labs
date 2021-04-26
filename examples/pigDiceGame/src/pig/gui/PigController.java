package pig.gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pig.model.Game;

import java.io.File;

public class PigController {

    // Data Fields
    Game pig;

    // FXML Connections
    @FXML
    ImageView dieImage;

    @FXML
    Button rollButton;

    @FXML
    Button holdButton;

    @FXML
    TextField p1turn;

    @FXML
    TextField p2turn;

    @FXML
    TextField p1total;

    @FXML
    TextField p2total;

    @FXML
    VBox p1box;

    @FXML
    VBox p2box;

    @FXML
    Label title;

    private Roller clock;

    private class Roller extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private int MAX_ROLLS = 20;

        private long last = 0;
        private int count = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                int r = 2 + (int)(Math.random() * 5);
                setDieImage(r);
                last = now;
                count++;
                if (count > MAX_ROLLS) {
                    clock.stop();
                    disableButtons(false);
                    roll();
                    count = 0;
                }
            }
        }
    }

    @FXML
    public void initialize() {
        clock = new Roller();
        pig = new Game("Player 1", "Player 2");
        updateViews();
    }

    public void updateViews() {
        setDieImage(pig.getDie().getTop());
        p1turn.setText("" + pig.getP1().getTurnScore());
        p1total.setText("" + pig.getP1().getTotalScore());
        p2turn.setText("" + pig.getP2().getTurnScore());
        p2total.setText("" + pig.getP2().getTotalScore());
        if (pig.p1Turn()) {
            p1box.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            p2box.setBackground(null);
        } else {
            p2box.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            p1box.setBackground(null);
        }
        if (pig.gameOver()) {
            disableButtons(true);
            title.setText("Game Over! " + pig.getCurrent().getName() + " wins!");
        }
    }

    public void setDieImage(int top) {
        dieImage.setImage(new Image("pig/resources/Dice" + top + ".png"));

        //File f = new File("src/pig/resources/Dice0.png");
        //System.out.println(f.toURI().toString());
        //dieImage.setImage(new Image(f.toURI().toString()));
    }

    public void disableButtons(boolean disable) {
        rollButton.setDisable(disable);
        holdButton.setDisable(disable);
    }

    public void rollAnimation() {
        clock.start();
        disableButtons(true);
    }

    public void roll() {
        pig.roll();
        updateViews();
    }

    public void hold() {
        pig.hold();
        updateViews();
    }
}
