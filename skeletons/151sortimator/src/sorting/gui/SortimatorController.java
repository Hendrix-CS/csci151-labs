package sorting.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import sorting.algorithms.Sorter;
import sorting.algorithms.Setting;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class SortimatorController {
    ArrayList<Integer> nums;
    ArrayList<Ellipse> dots;
    double xOffset, yOffset;
    Queue<Setting<Integer>> updates = new LinkedList<>();
    ClassFinder<Sorter<Integer>> sorterFinder = new ClassFinder<>(Sorter.class, "sorting.algorithms");

    private long FRAMES_PER_SEC = 50L;
    private long NANO_INTERVAL = 1000000000L / FRAMES_PER_SEC;

    private AnimationTimer timer = new AnimationTimer() {
        long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > NANO_INTERVAL) {
                if (updates.isEmpty()) {
                    timer.stop();
                } else {
                    try {
                        Setting<Integer> update = updates.remove();
                        updateDot(update.getWhere(), update.getValue());
                    } catch (IndexOutOfBoundsException exc) {
                        exc.printStackTrace();
                        killSort();
                    }
                }
                last = now;
            }
        }

    };

    void killSort() {
        timer.stop();
        updates.clear();
        Platform.runLater(() -> updateCounts.setText("0"));
    }

    @FXML
    Slider numDots;

    @FXML
    Pane panel;

    @FXML
    ChoiceBox<Sorter<Integer>> sorters;

    @FXML
    TextField updateCounts;

    @FXML
    void initialize() {
        Platform.runLater(() -> reset());
        sorterFinder.loadChoices(sorters);
        updateCounts.setEditable(false);
    }

    @FXML
    void scramble() {
        killSort();
        Collections.shuffle(nums);
        resynchDots();
    }

    @FXML
    void sort() {
        killSort();
        Sorter<Integer> sorter = sorters.getValue();
        sorter.sort(nums);
        for (Setting<Integer> setting: sorter) {
            updates.add(setting);
        }
        timer.start();
    }

    @FXML
    void reset() {
        killSort();
        if (dots != null) {
            for (Ellipse dot: dots) {
                panel.getChildren().remove(dot);
            }
        }


        int size = (int)numDots.getValue();
        xOffset = panel.getWidth() / size;
        yOffset = panel.getHeight() / size;
        nums = new ArrayList<Integer>();
        dots = new ArrayList<Ellipse>();
        for (int i = 0; i < size; i++) {
            nums.add(i);
            Ellipse dot = new Ellipse(xOffset, yOffset);
            dot.setFill(Color.BLUE);
            panel.getChildren().add(dot);
            dot.setTranslateX(xOffset * i);
            dot.setTranslateY(panel.getHeight() - (yOffset * i));
            dots.add(dot);
        }
    }

    void resynchDots() {
        for (int i = 0; i < nums.size(); i++) {
            resynchDot(i);
        }
    }

    void resynchDot(int i) {
        updateDot(i, nums.get(i));
    }

    void updateDot(int i, int where) {
        dots.get(i).setTranslateY(panel.getHeight() - (yOffset * where));
        updateCounts.setText(Integer.toString(1 + Integer.parseInt(updateCounts.getText())));
    }
}
