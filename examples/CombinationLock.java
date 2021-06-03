public class CombinationLock {

    private int[] combination;
    private int[] display;
    private int digits;

    public CombinationLock(int numWheels, int digits) {
        combination = new int[numWheels];
        display = new int[numWheels];
        this.digits = digits;
        for (int i = 0; i < combination.length; i++) {
            combination[i] = (int)(Math.random() * digits);
        }
    }

    public void rotate(int wheel) {
        if (wheel >= 0 && wheel < display.length) {
            display[wheel]++;
            display[wheel] %= digits;
        }
    }

    public boolean isUnlocked() {
        for (int i = 0; i < combination.length; i++) {
            if (combination[i] != display[i]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < display.length; i++) {
            s += display[i] + " ";
        }
        return s;
    }

    public static void main(String[] args) {
        CombinationLock c = new CombinationLock(3, 10);
        System.out.println(c);
    }
}
