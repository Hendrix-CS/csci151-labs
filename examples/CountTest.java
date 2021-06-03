package counting;

public class CountTest {

    public static double sum(double[] array) {  ///  array.length = n
        double result = 0;   // 1 step
        for (int i = 0;      // 1 step
             i < array.length;     // 3 steps * (n + 1)
             i++) {                // 3 steps * n
            result += array[i];    // 6 steps * n    array, i, array[i], result, +, assignment
        }
        return result;  // 1 step
    }  // total = 12n + 6

    public static void reverse(int[] array) {
        for (int i = 0;   // 1 step
             i < array.length / 2;  //  4 * ((n / 2) + 1)
             i++) { //  3 * (n / 2)
            int temp = array[i]; // 4 * (n / 2)
            array[i] = array[array.length - 1 - i];  // 9 * (n / 2)
            array[array.length - 1 - i] = temp;
        }
    } // total =  23n + 5

    // total = 25n + 1  22n + 5   23n + 5  26n + 5

    public static int over9000(int num) {
        for (int i = 0; i < 9000; i++) {
            num += i;
        }
        return num;
    }

    public static double sum(double[][] matrix) {
        double result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    public static double sumLowerTriangle(double[][] matrix) {
        double result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    public static boolean linearSearch(int[] data, int target) {
        for (int i = 0; (i < data.length) && (data[i] <= target);  i++) {
            if (data[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static void insertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int j;
            for (j = i - 1; (j >= 0) && (data[j] > data[i]); j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = data[i];
        }
    }

    public static boolean arrayequal(int[] first, int[] second) {
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }

    public static double sum2(double[] array) {  // array length is n
        double result = 0;  // step
        for (int i = 0; i < array.length; i++) {  // step
            result += array[i];  // 4 steps
        }  // 3 steps + 3 step
        return result; // step
    }   // 3 + 10n

    public static void reverse2(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {  // 1 step
            int temp = array[i];   // 3 steps
            array[i] = array[array.length - 1 - i]; // 7 steps
            array[array.length - 1 - i] = temp; // 7 steps
        }  // 3 steps  + 4 steps
    } // 1 + (24 n) / 2      1 + 12n


}
