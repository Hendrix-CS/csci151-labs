package rpn;

import rpn.model.RPNCalc;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TextField input;
	@FXML
	private ScrollPane pendingControl;
	@FXML
	private TextField pending;
	@FXML
	private TextField output;
	
	private RPNCalc calculator = new RPNCalc();
	
	@FXML
	private void initialize() {
		input.setOnAction(event -> {
			try {
				handleInput();
			} catch (Exception exc) {
				input.setText(exc.getMessage());
			}
		});

		pending = new TextField();
		pendingControl.setContent(pending);
		
		output.setEditable(false);
		pending.setEditable(false);
	}
	
	private void handleInput() {
		calculator.evaluate(input.getText());
		input.setText("");
		String values = calculator.toString();
		pending.setPrefColumnCount(values.length());
		pending.setText(values);
		output.setText(Integer.toString(calculator.getLastValue()));		
	}
}
