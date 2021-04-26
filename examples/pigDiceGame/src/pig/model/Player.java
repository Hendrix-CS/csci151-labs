package pig.model;

public class Player {

    // Data Fields
    private String name;
    private int turnScore;
    private int totalScore;

    // Constructor
    public Player(String name) {
        this.name = name;
        turnScore = 0;
        totalScore = 0;
    }

    // Accessor Methods
    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTurnScore() {
        return turnScore;
    }

    // Game Play Methods
    public void resetTurnScore() {
        turnScore = 0;
    }

    public void updateTurn(int roll) {
        turnScore += roll;
    }

    public void saveScore() {
        totalScore += turnScore;
        resetTurnScore();
    }

    public static void main(String[] args) {
        Die d = new Die(6, 1);
        Player p = new Player("Mark");
        System.out.println("Turn Score: " + p.getTurnScore());
        System.out.println("Total Score: " + p.getTotalScore());
        System.out.println("Rolling...");
        for (int i = 0; i < 10; i++) {
            d.roll();
            p.updateTurn(d.getTop());
            System.out.println("Turn Score: " + p.getTurnScore());
            System.out.println("Total Score: " + p.getTotalScore());
        }
        p.saveScore();
        System.out.println("Saving...");
        System.out.println("Turn Score: " + p.getTurnScore());
        System.out.println("Total Score: " + p.getTotalScore());
    }
}
