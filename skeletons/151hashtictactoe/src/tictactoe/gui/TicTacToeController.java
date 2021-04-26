package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tictactoe.core.Coord;
import tictactoe.core.Game;
import tictactoe.core.Result;
import tictactoe.core.Position;
import tictactoe.core.Status;
import tictactoe.core.Symbol;
import tictactoe.learn.AI;
import tictactoe.learn.HashTable;

public class TicTacToeController {
	@FXML
	TextField iterations;
	
	@FXML
	Label status;
	
	@FXML
	Button train;
	
	@FXML
	Button newGame;
	
	@FXML
	Button aiMove;
	
	@FXML
	Pane board;
	
	AI ai;
	Game game;
	HashTable<Coord,Rectangle> heatMaps = new HashTable<>();
	
	@FXML
	public void initialize() {
		
		iterations.setText("1000");
		
		train.setOnAction(event -> {
			try {
				ai.selfTrain(Integer.parseInt(iterations.getText()));
				status.setText("Trained " + iterations.getText() + " times");
				heatMap();
			} catch (NumberFormatException nfe) {
				status.setText(iterations.getText() + ": " + "Not a number");	
			}
			iterations.setText("");
		});
		
		newGame.setOnAction(event -> {restart();});
		
		board.setOnMouseClicked(event -> {
			int x = (int)(event.getX() / xCell());
			int y = (int)(event.getY() / yCell());
			Symbol at = game.get(x, y);
			if (at == Symbol.EMPTY && !game.isOver()) {
				placePiece(x, y);
			}
		});
		
		ai = new AI();
		aiMove.setOnAction(event -> {
			if (!game.isOver()) {
				Coord move = ai.pickBestMove(game.getCurrentPosition(), game.currentPlayer());
				placePiece(move.getX(), move.getY());
			} 
		});
		
		restart();
	}

	double xCell() {return board.getWidth() / Position.SIZE;}
	double yCell() {return board.getHeight() / Position.SIZE;}
	
	void placePiece(int x, int y) {
		game.takeTurn(x, y);
		plotPiece(x, y);
		status.setText("Turn: " + game.currentPlayer());
		if (game.isOver()) {
			ai.train(game);
			Result result = game.getStatus();
			status.setText("Game Over: " + result.getStatus());
			if (result.getStatus() != Status.DRAW) {
				Coord start = result.getStart();
				Coord end = result.getEnd();
				double offset = 0.5;
				Line endStroke = new Line(xCell() * (offset + start.getX()), yCell() * (offset + start.getY()),
						xCell() * (offset + end.getX()), yCell() * (offset + end.getY()));
				endStroke.setStroke(Color.RED);
				endStroke.setStrokeWidth(5);
				board.getChildren().add(endStroke);
			}
		}
		heatMap();
	}

	void restart() {
		game = new Game(); 
		
		board.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		board.getChildren().clear();
		
		for (Coord c: game.allPositions()) {
			Rectangle r = new Rectangle(c.getX() * xCell(), c.getY() * yCell(), xCell(), yCell());
			r.setFill(Color.BLUE);
			heatMaps.put(c, r);
			board.getChildren().add(r);
		}

		double xCell = board.getWidth() / Position.SIZE;
		double yCell = board.getHeight() / Position.SIZE;
		for (int i = 1; i < Position.SIZE; i++) {
			board.getChildren().add(makeLine(0, i * yCell, board.getWidth(), 5));
			board.getChildren().add(makeLine(i * xCell, 0, 5, board.getHeight()));
		}
		status.setText("Turn: " + game.currentPlayer());
		heatMap();
	}
	
	Rectangle makeLine(double x1, double y1, double x2, double y2) {
		Rectangle line = new Rectangle(x1, y1, x2, y2);
		line.setFill(Color.BLACK);
		return line;
	}
	
	void plotPiece(int x, int y) {
		Text figure = new Text();
		figure.setFill(Color.BLACK);
		figure.setFont(new Font((int)Math.min(xCell(), yCell())));
		figure.setText(game.get(x, y) == Symbol.X ? "X" : "O");
		figure.setX(xCell() * (x + 0.2));
		figure.setY(yCell() * (y + 0.85));
		board.getChildren().add(figure);
	}
	
	void heatMap() {
		HashTable<Coord,Double> portions = ai.getMovePortions(game.getCurrentPosition(), game.currentPlayer());
		double def = 1.0 / portions.allKeys().size();
		for (Coord c: game.allPositions()) {
			double level = portions.get(c).map(v -> v - def).orElse(0.0);
			if (level > 0) {
				double frac = level / (1 - def);
				heatMaps.get(c).get().setFill(Color.color(1.0 - frac, 1.0, 1.0 - frac));
			} else if (level < 0){
				double frac = level / -def;
				heatMaps.get(c).get().setFill(Color.color(1.0, 1.0 - frac, 1.0 - frac));
			} else {
				heatMaps.get(c).get().setFill(Color.color(1.0, 1.0, 1.0));
			}
		}
	}
}