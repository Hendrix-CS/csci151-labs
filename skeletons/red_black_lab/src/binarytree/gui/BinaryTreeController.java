package binarytree.gui;

import binarytree.core.NodeColor;
import binarytree.core.RedBlack;
import binarytree.core.RedBlackTree;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeController {
	
	@FXML
	TextField insertValue;
	
	@FXML
	Pane visualTree;
	
	@FXML
	Button clear;
	
	@FXML
	RadioButton remove;
	
	@FXML
	RadioButton leftRotate;
	
	@FXML
	RadioButton rightRotate;
	
	RedBlackTree<String> tree = new RedBlackTree<>();
	
	@FXML
	TextArea orderedString;
	
	@FXML
	TextField size;
	@FXML
	TextField min;
	@FXML
	TextField max;
	@FXML
	TextField height;
	
	@FXML
	void initialize() {
		clear.setOnAction(event -> {
			tree = new RedBlackTree<>();
			redrawTree();
		});
	}
	
	@FXML
	void insert() {
		if (!insertValue.getText().isEmpty()) {
			Platform.runLater(() -> {
				tree.insert(insertValue.getText());
				insertValue.setText("");
				redrawTree();
			});
		}
	}
	
	void redrawTree() {
		HashMap<String,Circle> string2circle = new HashMap<>();
		ArrayList<Text> allLabels = new ArrayList<>();
		visualTree.getChildren().clear();
		ArrayList<ArrayList<ArrayList<NodeColor<String>>>> levels = tree.levelOrder();
		double yOffset = visualTree.getHeight() / levels.size();
		for (int level = levels.size() - 1; level >= 0; level -= 1) {
			double xOffset = visualTree.getWidth() / (1 + Math.pow(2, level));
			for (int column = 0; column < levels.get(level).size(); column++) {
				placeNodeAt(xOffset, yOffset, levels.size() - 1 - level, column, levels.get(level).get(column), allLabels, string2circle);
			}
		}

        for (Map.Entry<String, Circle> entry: string2circle.entrySet()) {
            if (entry.getKey().length() > 0) {
                visualTree.getChildren().add(entry.getValue());
            }
        }

		for (Text t: allLabels) {
			visualTree.getChildren().add(t);
		}
		
		size.setText("" + tree.size());
		tree.getMin().ifPresent(m -> min.setText("" + m));
		tree.getMax().ifPresent(m -> max.setText("" + m));
        height.setText("" + tree.height());
	}
	
	private void placeNodeAt(double xOffset, double yOffset, int level, int column, ArrayList<NodeColor<String>> nodeAndKids, ArrayList<Text> allLabels, HashMap<String,Circle> string2circle) {
		if (!nodeAndKids.isEmpty() && nodeAndKids.get(0) != null) {
			double x = xOffset * (1 + column);
			double y = yOffset * (0.5 + level);
			Circle c = new Circle(x, y, 12, nodeAndKids.get(0).color == RedBlack.RED ? Color.RED : Color.BLACK);
			Text nodeText = new Text(nodeAndKids.get(0).key);
            nodeText.setFill(Color.WHITE);
			string2circle.put(nodeText.getText(), c);
            for (int kid = 1; kid < nodeAndKids.size(); kid++) {
                if (string2circle.containsKey(nodeAndKids.get(kid).key)) {
                    connect(c, string2circle.get(nodeAndKids.get(kid).key));
                }
            }
            nodeText.setX(x - 3);
            nodeText.setY(y + 3);
            allLabels.add(nodeText);
            nodeText.setOnMouseClicked(c.getOnMouseClicked());
		}
	}
	
	static <T> int maxWidth(ArrayList<ArrayList<T>> levels) {
		int max = 0;
		for (ArrayList<T> level: levels) {
			max = Math.max(max, level.size());
		}
		return max;
	}
	
	void connect(Circle one, Circle two) {
		Line connection = new Line(one.getCenterX(), one.getCenterY(), two.getCenterX(), two.getCenterY());
		visualTree.getChildren().add(connection);
	}
}
