package maze.gui;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import maze.model.Move;
import maze.model.Position;
import maze.model.Puzzle;

public class MazeController {
	@FXML
	TextField width;
	@FXML
	TextField height;
	@FXML
	Pane maze;
	@FXML
	CheckBox placeExplorer;
	@FXML
	CheckBox placeGoal;
	@FXML
	CheckBox clearFill;

	@FXML
	Button forward;
	@FXML
	Button left;
	@FXML
	Button right;

	Puzzle mazeData;
	Polygon ex;
	HashMap<Position,Rectangle> cells = new HashMap<>();
	double cellWidth, cellHeight;

	@FXML
	public void initialize() {
		width.setText("15");
		height.setText("10");

		/********** TODO UNCOMMENT FOR STEP 4

		setupButton(forward, Move.FORWARD);
		setupButton(left, Move.LEFT);
		setupButton(right, Move.RIGHT);

		***********/

		clearFill.setSelected(true);

		clearFill.setOnAction(event -> {placeGoal.setSelected(false);placeExplorer.setSelected(false);});
		placeGoal.setOnAction(event -> {placeExplorer.setSelected(false);clearFill.setSelected(false);});
		placeExplorer.setOnAction(event -> {placeGoal.setSelected(false);clearFill.setSelected(false);});
	}

	void setupButton(Button b, Move m) {
		/*********** TODO UNCOMMENT FOR STEP 4

		b.setOnAction(event -> {
			if (mazeData.hasExplorer()) {
				mazeData.moveExplorer(m);
				placeFigure();
			}
		});

		************/
	}

	@FXML
	public void resetMaze() {
		int w = Integer.parseInt(width.getText());
		int h = Integer.parseInt(height.getText());
		mazeData = new Puzzle(w, h);
		maze.getChildren().clear();

		cellWidth = maze.getWidth() / w;
		cellHeight = maze.getHeight() / h;

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Rectangle cell = new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
				Position spot = new Position(x, y);
				cells.put(spot, cell);
				cell.setFill(mazeData.getColorFor(spot));
				cell.setOnMouseClicked(event -> {
					if (placeExplorer.isSelected()) {

						/******* TODO UNCOMMENT FOR STEP 4
						if (mazeData.canEnter(spot)) {
							mazeData.placeExplorer(spot);
							maze.getChildren().remove(ex);
							ex = new Polygon(new double[]{cellWidth/4, cellHeight/4, 3*cellWidth/4, cellHeight/4, cellWidth/2, 3*cellHeight/4});
							ex.setFill(Color.GREEN);
							maze.getChildren().add(ex);
							placeFigure();
						}

						********/

					} else if (placeGoal.isSelected()) {
						/********* TODO UNCOMMENT FOR STEP 4


						mazeData.placeGoal(spot);
						cells.get(spot).setFill(Color.ORANGE);

						**********/
					} else {
						if (mazeData.canEnter(spot)) {
							mazeData.fill(spot);
						} else {
							mazeData.clear(spot);
						}
						cell.setFill(mazeData.getColorFor(spot));
					}
				});
				maze.getChildren().add(cell);
			}
		}
	}

	void placeFigure() {
		/********** TODO UNCOMMENT FOR STEP 4


		Position spot = mazeData.getExplorerPosition();
		ex.setTranslateX(spot.getX() * cellWidth);
		ex.setTranslateY(spot.getY() * cellHeight);
		ex.setRotate(mazeData.getExplorerHeading().getRotation());

		***********/
	}

	void recolor() {
		for (int i = 0; i < mazeData.getWidth(); i++) {
			for (int j = 0; j < mazeData.getHeight(); j++) {
				Position p = new Position(i, j);
				cells.get(p).setFill(mazeData.getColorFor(p));
			}
		}
	}
}
