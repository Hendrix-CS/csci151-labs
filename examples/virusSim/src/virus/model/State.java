package virus.model;

import javafx.scene.paint.Color;

public enum State {
    SUSCEPTIBLE {
        public Color getColor() {
            return Color.BLUE;
        }
    }, INFECTED {
        public Color getColor() {
            return Color.RED;
        }
    }, RECOVERED {
        public Color getColor() {
            return Color.GREEN;
        }
    };

    public abstract Color getColor();
}
