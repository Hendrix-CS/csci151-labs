package virus.model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.EnumMap;

public class Simulation {

    private ArrayList<Person> people;
    private int ticks;

    public Simulation(int popCount, Pane world) {
        people = new ArrayList<Person>();
        for (int i = 0; i < popCount; i++) {
            Person p = new Person(State.SUSCEPTIBLE, world);
            people.add(p);
        }
        Person p = new Person(State.INFECTED, world);
        people.add(p);
    }

    public int getTicks() {
        return ticks;
    }

    public void resetTicks() {
        ticks = 0;
    }

    public void incTicks() {
        ticks++;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public EnumMap<State,Integer> getPopCounts() {
        EnumMap<State, Integer> currentPop = new EnumMap<State, Integer>(State.class);
        for (Person p : people) {
            if (!currentPop.containsKey(p.getState())) {
                currentPop.put(p.getState(), 0);
            }
            currentPop.put(p.getState(), 1 + currentPop.get(p.getState()));
        }
        return currentPop;
    }

    public void move() {
        for (Person p: people) {
            p.move();
        }
    }

    public void heal() {
        for (Person p: people) {
            p.getBetter();
        }
    }

    public void collisionCheck() {
        for (Person p : people) {
            for (Person q : people) {
                p.collide(q);
            }
        }
    }

    public void step() {
        move();
        heal();
        collisionCheck();
        incTicks();
    }
}
