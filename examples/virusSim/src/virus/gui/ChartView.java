package virus.gui;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import virus.model.Simulation;
import virus.model.State;

import java.util.EnumMap;

public class ChartView extends Parent {

    private Simulation sim;
    private Pane timechart;

    public ChartView(Simulation sim) {
        this.sim = sim;
        reset();
    }

    public void setSim(Simulation sim) {
        this.sim = sim;
    }

    public void reset() {
        getChildren().clear();
        timechart = new Pane();
        getChildren().add(timechart);
    }

    public void update() {
        EnumMap<State, Integer> currentPop = sim.getPopCounts();

        for (State state : State.values()) {
            if (currentPop.containsKey(state)) {

                Circle c = new Circle(1, state.getColor());
                c.setTranslateX(sim.getTicks() / 5.0);
                c.setTranslateY(130 - currentPop.get(state));
                timechart.getChildren().add(c);
            }
        }
    }
}
