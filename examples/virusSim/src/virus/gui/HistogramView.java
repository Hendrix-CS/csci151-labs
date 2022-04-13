package virus.gui;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import virus.model.Simulation;
import virus.model.State;

import java.util.EnumMap;

public class HistogramView extends Parent{

    private Simulation sim;
    private EnumMap<State, Rectangle> hrects = new EnumMap<State, Rectangle>(State.class);
    private Pane histogram;

    public HistogramView(Simulation sim) {
        this.sim = sim;
        reset();
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }

    public void reset() {
        getChildren().clear();
        this.histogram = new Pane();

        int offset = 0;
        for (State s : State.values()) {
            Rectangle r = new Rectangle(50, 0, s.getColor());
            r.setTranslateX(offset);
            offset += 55;
            hrects.put(s, r);
            histogram.getChildren().add(r);
        }
        getChildren().add(histogram);
    }

    public void update() {
        EnumMap<State, Integer> currentPop = sim.getPopCounts();

        for (State state : State.values()) {
            if (currentPop.containsKey(state)) {
                hrects.get(state).setHeight(currentPop.get(state));
                hrects.get(state).setTranslateY(30 + 100 - currentPop.get(state));
            }
        }
    }
}
