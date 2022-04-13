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

import java.util.ArrayList;
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

    ArrayList<PersonView> pviews;

    HistogramView histogramView;
    ChartView chartView;

    private Movement clock;

    private class Movement extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                updateViews();
                last = now;
                stepCount.setText("" + sim.getTicks());
            }
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

        histogramView = new HistogramView(sim);
        histogram.getChildren().add(histogramView);
        chartView = new ChartView(sim);
        timechart.getChildren().add(chartView);

        world.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @FXML
    public void setup() {
        clock.stop();

        world.getChildren().clear();

        sim = new Simulation(100, world);

        pviews = new ArrayList<>();
        for (Person p : sim.getPeople()) {
            PersonView pv = new PersonView(p);
            world.getChildren().add(pv);
            pviews.add(pv);
        }

        setSize();
        setLimit();
        setSickTime();

        disableButtons(true, false, false);

        histogramView.reset();
        histogramView.setSim(sim);
        chartView.reset();
        chartView.setSim(sim);

        updateViews();
    }

    public void setSize() {
        Person.radius = (int) (sizeSlider.getValue());
        updatePeople();
    }

    public void updatePeople() {
        for (PersonView pv : pviews) {
            pv.update();
        }
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

    public void updateViews() {
        histogramView.update();
        chartView.update();
        updatePeople();
        if (!sim.getPopCounts().containsKey(State.INFECTED)) {
            clock.stop();
            disableButtons(true, true, true);
        }
    }
}
