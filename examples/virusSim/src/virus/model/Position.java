package virus.model;

import javafx.geometry.Bounds;

public class Position {

    private double x;
    private double y;
    public static int limit;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(Bounds world, int radius) {
        this(radius + (Math.random() * (world.getWidth() - 2 * radius)),
                radius + (Math.random() * (world.getHeight() - 2 * radius)));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(Heading h, Bounds world, int radius, Position origin) {
        x += h.getDX();
        y += h.getDY();
        if (x > world.getWidth() - radius || x < radius || Math.abs(x - origin.x) > limit) {
            h.bounceX();
            x += h.getDX();
        }
        if (y > world.getHeight() - radius || y < radius || Math.abs(y - origin.y) > limit) {
            h.bounceY();
            y += h.getDY();
        }
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2));
    }
}
