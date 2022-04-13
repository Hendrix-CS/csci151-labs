package virus.model;

import javafx.geometry.Bounds;

public class Person {

    public static int radius;

    private State state;
    private Position loc;
    private Heading heading;

    public static int healtime;
    private int sicktime = 0;

    private Position origin;
    private Bounds world;

    public Person(State state, Bounds world) {
        this.state = state;
        loc = new Position(world, radius);
        heading = new Heading();
        this.world = world;

        origin = new Position(loc.getX(), loc.getY());

    }

    public State getState() {
        return state;
    }

    public Position getLoc() {
        return loc;
    }

    public void move() {
        loc.move(heading, world, radius, origin);
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
