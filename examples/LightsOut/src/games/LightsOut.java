package games;

public class LightsOut {

    // Field
    private boolean[][] grid;

    // Constructor
    public LightsOut(int size) {
        grid = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = false;
            }
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    s += "#";
                } else {
                    s += ".";
                }
            }
            s += "\n";
        }
        return s;
    }

    public void randomize() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (Math.random() < 0.5) {
                    grid[i][j] = true;
                }
            }
        }
    }

    public void randomMoves(int n) {
        for (int i = 0; i < n; i++) {
            toggle((int)(Math.random() * grid.length),
                    (int)(Math.random() * grid.length));
        }
    }

    public void toggle(int x, int y) {
        grid[x][y] = !grid[x][y];
        if (x < grid.length - 1) {
            grid[x + 1][y] = !grid[x + 1][y];
        }
        if (x > 0) {
            grid[x - 1][y] = !grid[x - 1][y];
        }
        if (y < grid.length - 1) {
            grid[x][y + 1] = !grid[x][y + 1];
        }
        if (y > 0) {
            grid[x][y - 1] = !grid[x][y - 1];
        }
    }


    public boolean isSolved() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getState(int x, int y) {
        return grid[x][y];
    }

    public int getSize() {
        return grid.length;
    }

}
