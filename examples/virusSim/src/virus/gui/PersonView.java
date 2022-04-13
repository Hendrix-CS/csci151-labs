package virus.gui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import virus.model.Person;

public class PersonView extends Parent {

    private Person person;
    private Circle c;

    public PersonView(Person person) {
        this.person = person;
        c = new Circle(person.radius, person.getState().getColor());
        c.setStroke(Color.BLACK);
        getChildren().add(c);
    }

    public void update() {
        c.setFill((person.getState().getColor()));
        c.setRadius(person.radius);
        c.setTranslateX(person.getLoc().getX());
        c.setTranslateY(person.getLoc().getY());
    }
}
