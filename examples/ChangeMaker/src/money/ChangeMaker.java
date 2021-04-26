package money;

import java.util.Scanner;

public class ChangeMaker {

    public static void main(String[] args) {

        Currency[] coins = Currency.values();
        for (int i = 0; i < coins.length; i++) {
            System.out.println(coins[i] + " has value " +
                    coins[i].getValue());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("How much change do you need to give? ");
        int change = sc.nextInt();

        // Determine the change with the least number of coins
        int biggest = coins.length - 1;
        while (change > 0) {
            if (change >= coins[biggest].getValue()) {
                int count = change / coins[biggest].getValue();
                change -= count * coins[biggest].getValue();
                System.out.println(coins[biggest] + " " + count);
            } else {
                biggest--;
            }
        }
        System.out.println("All done!");
    }
}
