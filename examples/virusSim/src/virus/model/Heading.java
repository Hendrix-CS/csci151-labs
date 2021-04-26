package virus.model;

public class Heading {

    private double dx;
    private double dy;
    public static final double SPEED = 2;

    public Heading(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Heading() {
        double dir = Math.random() * 2 * Math.PI;
        dx = Math.sin(dir);
        dy = Math.cos(dir);
    }

    public double getDX(){
        return dx * SPEED;
    }

    public double getDY() {
        return dy * SPEED;
    }

    public void bounceX() {
        dx *= -1;
    }

    public void bounceY() {
        dy *= -1;
    }
}
