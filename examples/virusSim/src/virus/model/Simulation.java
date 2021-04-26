package virus.model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Simulation {

    private ArrayList<Person> people;

    public Simulation(int popCount, Pane world) {
        people = new ArrayList<Person>();
        for (int i = 0; i < popCount; i++) {
            Person p = new Person(State.SUSCEPTIBLE, world);
            people.add(p);
        }
        Person p = new Person(State.INFECTED, world);
        people.add(p);
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void move() {
        for (Person p: people) {
            p.move();
        }
    }
    public void draw() {
        for (Person p: people) {
            p.draw();
        }
    }

    public void collisionCheck() {
        for (Person p : people) {
            for (Person q : people) {
                p.collide(q);
            }
        }
    }

    public void heal() {
        for (Person p: people) {
            p.getBetter();
        }
    }

    public void step() {
        move();
        heal();
        collisionCheck();
        draw();
    }
}
