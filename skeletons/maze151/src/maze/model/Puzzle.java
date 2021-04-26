package maze.model;

import javafx.scene.paint.Color;

//Create an implementation that passes all tests in PuzzleTest.

public class Puzzle {
	
	private Maze dungeon;
	private Explorer hero;
	private Position goal;
	
	public Puzzle(int w, int h) {
		// TODO STEP 2 Complete this method
	}
	
	public Color getColorFor(Position spot) {
		// TODO STEP 2 Complete this method
		return Color.BLACK;
	}

	public boolean canEnter(Position spot) {
		// TODO STEP 2 Complete this method
		return false;
	}

	public void fill(Position spot) {
		// TODO STEP 2 Complete this method
		
	}

	public boolean clear(Position spot) {
		// TODO STEP 2 Complete this method
		return false;
	}

	public int getWidth() {
		// TODO STEP 2 Complete this method
		return 0;
	}

	public int getHeight() {
		// TODO STEP 2 Complete this method
		return 0;
	}

}
