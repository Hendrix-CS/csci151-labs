package holiday;

public class LightTester {

    public static void main(String[] args) {
        HolidayLight light = new ArrayLight();

        for (int i = 0; i < 7; i++) {
            System.out.println(light.getColor());
            light.nextColor();
        }
    }
}
