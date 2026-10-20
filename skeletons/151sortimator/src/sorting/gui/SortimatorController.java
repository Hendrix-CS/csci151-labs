package sorting.gui;

import java.util.*;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;
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
    ArrayList<Rectangle> dots;
    double xOffset, yOffset;
    Queue<Setting<Integer>> updates = new LinkedList<>();
    ClassFinder<Sorter<Integer>> sorterFinder = new ClassFinder<>(Sorter.class, "sorting.algorithms");

    private long FRAMES_PER_SEC = 50L;
    private long NANO_INTERVAL = 1000000000L / FRAMES_PER_SEC;
    private List<Integer> sorted;
    private int greenIndex = 0;

    private AnimationTimer timer = new AnimationTimer() {
        long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > NANO_INTERVAL) {
                if (updates.isEmpty()) {
                    if (greenIndex == 0)
                    {
                        sorted = nums.stream().sorted().toList();
                    }

                    if (greenIndex < dots.size())
                    {
                        Color color = (sorted.get(greenIndex).equals(nums.get(greenIndex)) ? Color.LIMEGREEN : Color.RED);
                        dots.get(greenIndex).setFill(color);
                        greenIndex++;
                    }
                    else
                    {
                        timer.stop();
                        greenIndex = 0;
                    }
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
        sorted = null;
        greenIndex = 0;
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
            panel.getChildren().removeAll(dots);
        }

        int size = (int) numDots.getValue();
        xOffset = panel.getWidth() / size;
        yOffset = panel.getHeight() / size;

        //System.out.println(xOffset);
        //System.out.println(panel.getWidth());
        //System.out.println(panel.getHeight());

        nums = new ArrayList<>();
        dots = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            nums.add(i);

            double x = xOffset * i;
            double y = panel.getHeight() - (yOffset * (i + 1));

            Rectangle rectangle = new Rectangle(
                    x, y, xOffset, panel.getHeight() - y
            );

            rectangle.setFill(Color.BLUE);
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(1);

            panel.getChildren().add(rectangle);
            dots.add(rectangle);
        }

        updateCounts.setText("0");
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
        Rectangle line = dots.get(i);

        double x = xOffset * i;
        double y = panel.getHeight() - (yOffset * (where + 1));

        line.setX(x);
        line.setY(y);
        line.setHeight(panel.getHeight() - y);

        updateCounts.setText(
                Integer.toString(1 + Integer.parseInt(updateCounts.getText()))
        );
    }
}
