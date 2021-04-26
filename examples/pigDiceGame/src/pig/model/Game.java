package pig.model;

public class Game {

    public static final int MAX_SCORE = 100;

    // Data Fields
    private Die d;
    private Player p1;
    private Player p2;
    private Player current;

    // Constructor
    public Game(String p1name, String p2name) {
        d = new Die();
        p1 = new Player(p1name);
        p2 = new Player(p2name);
        current = p1;
    }

    // Accessor methods

    public Die getDie() {
        return d;
    }

    public Player getCurrent() {
        return current;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    // Status Methods
    public boolean gameOver() {
        return current.getTotalScore() >= MAX_SCORE;
    }

    public boolean p1Turn() {
        return current == p1;
    }

    // Game Play Methods
    public void switchTurn() {
        if (p1Turn()) {
            current = p2;
        } else {
            current = p1;
        }
    }

    public void roll() {
        d.roll();
        int t = d.getTop();
        current.updateTurn(t);
        if (t == 1) {
            current.resetTurnScore();
            switchTurn();
        }
    }

    public void hold() {
        current.saveScore();
        if (!gameOver()) {
            switchTurn();
            d.setTop(0);
        }
    }

    public static void main(String[] args) {
        Game g = new Game("Mark", "Ryan");
        for (int i = 0; i < 10; i++) {
            g.roll();
            g.roll();
            g.hold();
            System.out.println("Die Rolled " + g.getDie().getTop());
            System.out.println("p1 Turn: " + g.getP1().getTurnScore());
            System.out.println("p1 Total: " + g.getP1().getTotalScore());
            System.out.println("p2 Turn: " + g.getP2().getTurnScore());
            System.out.println("p2 Total: " + g.getP2().getTotalScore());
        }
    }
}
