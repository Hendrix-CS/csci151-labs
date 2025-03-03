package maze.gui;

import java.util.EnumMap;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import maze.model.*;
import maze.searchers.*;

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

	private Puzzle mazeData;
	private Polygon ex;
	private HashMap<Position,Rectangle> cells = new HashMap<>();
	private double cellWidth, cellHeight;
	private EnumMap<Cell,TextField> cellCounts = new EnumMap<>(Cell.class);

	// NEW FOR THIS LAB
	@FXML
	TextField open;
	@FXML
	TextField closed;
	@FXML
	TextField visited;
	@FXML
	TextField steps;
	@FXML
	ChoiceBox<String> stackBox;


	@FXML
	public void initialize() {
		width.setText("15");
		height.setText("10");


		setupButton(forward, Move.FORWARD);
		setupButton(left, Move.LEFT);
		setupButton(right, Move.RIGHT);

		setupChoices(stackBox);

		clearFill.setSelected(true);

		clearFill.setOnAction(event -> {placeGoal.setSelected(false);placeExplorer.setSelected(false);});
		placeGoal.setOnAction(event -> {placeExplorer.setSelected(false);clearFill.setSelected(false);});
		placeExplorer.setOnAction(event -> {placeGoal.setSelected(false);clearFill.setSelected(false);});

		cellCounts.put(Cell.OPEN, open);
		cellCounts.put(Cell.CLOSED, closed);
		cellCounts.put(Cell.VISITED, visited);
	}

	private void setupButton(Button b, Move m) {
		b.setOnAction(event -> {
			if (mazeData.hasExplorer()) {
				mazeData.moveExplorer(m);
				placeFigure();
			}
		});
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


						if (mazeData.canEnter(spot)) {
							mazeData.placeExplorer(spot);
							maze.getChildren().remove(ex);
							ex = new Polygon(cellWidth/4, cellHeight/4, 3*cellWidth/4,
									cellHeight/4, cellWidth/2, 3*cellHeight/4);
							ex.setFill(Color.GREEN);
							maze.getChildren().add(ex);
							placeFigure();
						}



					} else if (placeGoal.isSelected()) {


						mazeData.placeGoal(spot);
						cells.get(spot).setFill(Color.ORANGE);


					} else {
						if (mazeData.canEnter(spot)) {
							mazeData.fill(spot);
						} else {
							mazeData.clear(spot);
						}
						cell.setFill(mazeData.getColorFor(spot));
						updateCellCounts();
					}
				});
				maze.getChildren().add(cell);
			}
		}
		updateCellCounts();
	}

	private void placeFigure() {

		Position spot = mazeData.getExplorerPosition();
		ex.setTranslateX(spot.x() * cellWidth);
		ex.setTranslateY(spot.y() * cellHeight);
		ex.setRotate(mazeData.getExplorerHeading().getRotation());


	}

	void recolor() {
		for (int i = 0; i < mazeData.getWidth(); i++) {
			for (int j = 0; j < mazeData.getHeight(); j++) {
				Position p = new Position(i, j);
				if (mazeData.hasGoal() && mazeData.getGoal().equals(p)) {
					cells.get(p).setFill(Color.ORANGE);
				} else {
					cells.get(p).setFill(mazeData.getColorFor(p));
				}
			}
		}
		updateCellCounts();
	}

	@FXML
	void randomize() {
		/********** TODO UNCOMMENT FOR STEP 3

		 resetMaze();
		 if (stackBox.getSelectionModel().getSelectedItem().equals("Perfect")) {
		 	mazeData.tunnelRandomly(true);
		 } else {
			 mazeData.tunnelRandomly(false);
		 }
		 recolor();

		 ***********/
	}

	// NEW FOR THIS LAB
	private void updateCellCounts() {
		for (Cell type: Cell.values()) {
			cellCounts.get(type).setText(Integer.toString(mazeData.getCountOf(type)));
		}
	}

	private void setupChoices(ChoiceBox<String> choices) {
		choices.getItems().add("Perfect");
		choices.getItems().add("Braided");
		if (!choices.getItems().isEmpty()) {
			choices.getSelectionModel().select(0);
		}
	}
}
