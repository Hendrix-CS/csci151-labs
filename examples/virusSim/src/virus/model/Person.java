package virus.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Person {

    public static int radius;

    private State state;
    private Position loc;
    private Heading heading;
    private Pane pane;

    public static int healtime;
    private int sicktime = 0;

    private Position origin;

    public Person(State state, Pane world) {
        this.state = state;
        loc = new Position(world, radius);
        heading = new Heading();
        this.pane = world;

        origin = new Position(loc.getX(), loc.getY());
    }

    public State getState() {
        return state;
    }

    public Position getLoc() {
        return loc;
    }

    public void move() {
        loc.move(heading, pane, radius, origin);
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean collide(Person other) {
        if (this.loc.distance(other.loc) < 2 * radius) {
            if (other.state == State.INFECTED && state == State.SUSCEPTIBLE) {
                setState(State.INFECTED);
            }
            return true;
        }
        return false;
    }

    public void getBetter() {
        if (state == State.INFECTED) {
            sicktime++;
            if (sicktime > healtime) {
                setState(State.RECOVERED);
                sicktime = 0;
            }
        }
    }
}
