package games;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LightsOut game = new LightsOut(5);
        game.randomize();
        System.out.println(game);

        Scanner sc = new Scanner(System.in);
        while (!game.isSolved()) {
            try {
                System.out.println("X? ");
                int x = sc.nextInt();
                System.out.println("Y? ");
                int y = sc.nextInt();
                try {
                    game.toggle(x, y);
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    System.out.println("Out of bounds, try again!");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please type integers!");
                sc.next();
            }
            System.out.println(game);
        }
    }
}
