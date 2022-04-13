package binarytree.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import binarytree.core.BinarySearchTree;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
	
	BinarySearchTree<String> tree = new BinarySearchTree<>();
	
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
			tree = new BinarySearchTree<>();
			redrawTree();
		});
	
		ToggleGroup changeGroup = new ToggleGroup();
		changeGroup.getToggles().add(remove);
		changeGroup.getToggles().add(leftRotate);
		changeGroup.getToggles().add(rightRotate);
	}
	
	@FXML
	void insert() {
		if (insertValue.getText().length() > 0) {
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
		ArrayList<ArrayList<ArrayList<String>>> levels = tree.levelOrder();
		double yOffset = visualTree.getHeight() / levels.size();
		for (int level = levels.size() - 1; level >= 0; level -= 1) {
			double xOffset = visualTree.getWidth() / (1 + Math.pow(2, level));
			for (int column = 0; column < levels.get(level).size(); column++) {
				placeNodeAt(xOffset, yOffset, level, column, levels.get(level).get(column), allLabels, string2circle);
			}
		}
		
		for (Circle c: string2circle.values()) {
			visualTree.getChildren().add(c);
		}
		for (Text t: allLabels) {
			visualTree.getChildren().add(t);
		}
		
		size.setText("" + tree.size());
		setOptional(min, tree.getMin());
		setOptional(max, tree.getMax());
		setOptional(height, tree.height());
		tree.getMin().ifPresent(m -> min.setText("" + m));
		tree.getMax().ifPresent(m -> max.setText("" + m));
		tree.height().ifPresent(h -> height.setText("" + h));
	}
	
	private <T> void setOptional(TextField field, Optional<T> value) {
		field.setText(value.isPresent() ? value.get().toString() : "");
	}
	
	private void placeNodeAt(double xOffset, double yOffset, int level, int column, ArrayList<String> nodeAndKids, ArrayList<Text> allLabels, HashMap<String,Circle> string2circle) {
		if (nodeAndKids.size() > 0) {
			double x = xOffset * (1 + column);
			double y = yOffset * (0.5 + level);
			Circle c = new Circle(x, y, 12, Color.WHITE);
			Text nodeText = new Text(nodeAndKids.get(0));
			string2circle.put(nodeText.getText(), c);
			for (int kid = 1; kid < nodeAndKids.size(); kid++) {
				connect(c, string2circle.get(nodeAndKids.get(kid)));
			}
			nodeText.setX(x-3);
			nodeText.setY(y+3);
			allLabels.add(nodeText);
			c.setOnMouseClicked(event -> {
				String t = nodeText.getText();
				if (remove.isSelected()) tree.remove(t);
				if (leftRotate.isSelected()) tree.leftRotateAt(t);
				if (rightRotate.isSelected()) tree.rightRotateAt(t);
				redrawTree();
			});
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
	
	@FXML
	void preOrder() {
		orderedString.setText(tree.preorderString());
	}
	
	@FXML
	void inOrder() {
		orderedString.setText(tree.inorderString());
	}
	
	@FXML
	void postOrder() {
		orderedString.setText(tree.postorderString());
	}
}
