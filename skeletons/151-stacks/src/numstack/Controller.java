package numstack;

import numstack.model.ArrayIntStack;
import numstack.model.IntStack;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TextField input;
	@FXML
	private TextField output;
	@FXML
	private Button pop;
	@FXML
	private Button reset;
	
	IntStack nums = new ArrayIntStack();
	
	@FXML
	private void initialize() {
		input.setOnAction(event -> {
			try {
				handleInput();
			} catch (Exception exc) {
				input.setText(exc.getMessage());
			}
		});
		output.setEditable(false);
	}
	
	private void handleInput() {
		nums.push(Integer.parseInt(input.getText()));
		input.setText("");
		refresh();
	}
	
	private void refresh() {
		String values = nums.toString();
		output.setPrefColumnCount(values.length());
		output.setText(values);
	}
	
	@FXML
	private void pop() {
		try {
			nums.pop();
			refresh();
		} catch (Exception exc) {
			input.setText(exc.getMessage());
		}
	}
	
	@FXML
	private void reset() {
		nums = new ArrayIntStack();
		refresh();
	}
}
