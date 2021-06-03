public class MondaySample {

    // Conversion Function
    /*
    def temp_convert(fahrenheit: float) -> float:
            return (fahrenheit - 32) * (5 / 9)
    */
    public static double tempConvert(double fahrenheit) {
        return (fahrenheit - 32) * (5.0 / 9);
    }

    // Has a repeated letter Function
    /*
    def doubled(s: str) -> bool:
        for i in range(len(s) - 1):
            if s[i] == s[i + 1]:
                return True
        return False
     */

    public static boolean doubled(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    // Do the first two characters equal the last two?
    /*
    def first_last_2(s: str) -> bool:
        return s[:2] == s[-2:]
     */
    public static boolean firstLast2(String s) {
        return s.substring(0, 2).equals(s.substring(s.length() - 2, s.length()));
    }

    public static void main(String[] args) {
        System.out.println(tempConvert(90));
        System.out.println(doubled("World!"));
        System.out.println(doubled("Hello!"));
        System.out.println(firstLast2("hello"));
        System.out.println(firstLast2("hellohe"));
    }
}
