package holiday;

public class ConditionalLight implements HolidayLight{

    // Fields
    private String current;

    // Constructor
    public ConditionalLight() {
        current = "RED";
    }

    // Methods
    @Override
    public String getColor() {
        return current;
    }

    @Override
    public void nextColor() {
        if (current.equals("RED")) {
            current = "GREEN";
        } else if (current.equals("GREEN")) {
            current = "BLUE";
        } else if (current.equals("BLUE")) {
            current = "RED";
        }
    }
}
