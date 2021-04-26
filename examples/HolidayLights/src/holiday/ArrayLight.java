package holiday;

public class ArrayLight implements HolidayLight {

    // Fields
    private String[] colors;
    private int current;

    // Constructor
    public ArrayLight() {
        colors = new String[4];
        colors[0] = "RED";
        colors[1] = "GREEN";
        colors[2] = "BLUE";
        colors[3] = "YELLOW";
        current = 0;
    }

    @Override
    public String getColor() {
        return colors[current];
    }

    @Override
    public void nextColor() {
        current++;
        current %= colors.length;
    }

    public int testing() {
        return 7;
    }
}
