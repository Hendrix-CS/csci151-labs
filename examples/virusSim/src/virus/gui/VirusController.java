package virus.gui;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import virus.model.*;

import java.util.EnumMap;

public class VirusController {

    @FXML
    Pane world;

    @FXML
    Pane histogram;

    @FXML
    Pane timechart;

    @FXML
    Button startButton;

    @FXML
    Button stopButton;

    @FXML
    Button resetButton;

    @FXML
    Button stepButton;

    @FXML
    Slider sizeSlider;

    @FXML
    Slider travelSlider;

    @FXML
    Slider sickTimeSlider;

    @FXML
    TextField stepCount;

    Simulation sim;

    EnumMap<State, Rectangle> hrects = new EnumMap<State, Rectangle>(State.class);

    private Movement clock;

    private class Movement extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;
        private int ticks = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                drawCharts();
                last = now;
                ticks++;
                stepCount.setText("" + ticks);
            }
        }

        public void resetTicks() {
            ticks = 0;
            stepCount.setText("" + ticks);
        }

        public int getTicks() {
            return ticks;
        }
    }

    @FXML
    public void initialize() {

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSize();
            }
        });
        travelSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setLimit();
            }
        });
        sickTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSickTime();
            }
        });
        clock = new Movement();
        disableButtons(true, true, true);


        world.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @FXML
    public void setup() {
        clock.stop();
        clock.resetTicks();

        world.getChildren().clear();

        sim = new Simulation(100, world);
        sim.draw();

        setSize();
        setLimit();
        setSickTime();

        disableButtons(true, false, false);

        histogram.getChildren().clear();
        timechart.getChildren().clear();
        int offset = 0;
        for (State s : State.values()) {
            Rectangle r = new Rectangle(50, 0, s.getColor());
            r.setTranslateX(offset);
            offset += 55;
            hrects.put(s, r);
            histogram.getChildren().add(r);
        }
        drawCharts();
    }

    public void setSize() {
        Person.radius = (int) (sizeSlider.getValue());
        sim.draw();
    }

    public void setLimit() {
        Position.limit = (int)(travelSlider.getValue());
    }

    public void setSickTime() {
        Person.healtime = 50 * (int)(sickTimeSlider.getValue());
    }

    public void disableButtons(boolean stop, boolean step, boolean start) {
        stopButton.setDisable(stop);
        stepButton.setDisable(step);
        startButton.setDisable(start);
    }

    @FXML
    public void start() {
        System.out.println("Starting Simulation");
        clock.start();
        disableButtons(false, true, true);
    }

    @FXML
    public void stop() {
        System.out.println("Stopping!");
        clock.stop();
        disableButtons(true, false, false);
    }

    @FXML
    public void step() {
        sim.step();
    }

    public void drawCharts() {
        EnumMap<State, Integer> currentPop = new EnumMap<State, Integer>(State.class);
        for (Person p : sim.getPeople()) {
            if (!currentPop.containsKey(p.getState())) {
                currentPop.put(p.getState(), 0);
            }
            currentPop.put(p.getState(), 1 + currentPop.get(p.getState()));
        }
        for (State state : hrects.keySet()) {
            if (currentPop.containsKey(state)) {
                hrects.get(state).setHeight(currentPop.get(state));
                hrects.get(state).setTranslateY(30 + 100 - currentPop.get(state));

                Circle c = new Circle(1, state.getColor());
                c.setTranslateX(clock.getTicks() / 5.0);
                c.setTranslateY(130 - currentPop.get(state));
                timechart.getChildren().add(c);
            }
        }
        if (!currentPop.containsKey(State.INFECTED)) {
            clock.stop();
            disableButtons(true, true, true);
        }
    }
}
