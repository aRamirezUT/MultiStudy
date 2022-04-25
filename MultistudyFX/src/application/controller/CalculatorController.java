package application.controller;

import java.io.IOException;

import application.Model.CalculatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class CalculatorController {

	@FXML
	private float num1 = 0;

	private float num2 = 0;

	private String operator = "";

	private boolean start = true;

	private boolean isDecimal = false;

	@FXML
    	private AnchorPane calculatorPane;

	@FXML
	private TextField numberOutput;

	private CalculatorModel model = new CalculatorModel();
	
	@FXML
	void Home(ActionEvent event) throws IOException {
    	calculatorPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(calculatorPane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

	@FXML
	private void numberInput(ActionEvent event) {
		if (start) {
			numberOutput.setText("");
			start = false;
		}

		String val = ((Button) event.getSource()).getText();
		numberOutput.setText(numberOutput.getText() + val);
	}

	@FXML
	private void binaryOperate(ActionEvent event) {
		String val = ((Button) event.getSource()).getText();

		if (!"=".equals(val)) {
			if (!operator.isEmpty())
				return;

			operator = val;
			num1 = Float.parseFloat(numberOutput.getText());
			numberOutput.setText("");
		} else {
			if (operator.isEmpty())
				return;

			num2 = Float.parseFloat(numberOutput.getText());
			numberOutput.setText(String.valueOf(model.calculateBinary(num1, num2, operator)));
			operator = "";
			start = true;
		}
	}

	@FXML
	private void unaryOperate(ActionEvent event) {
		String val = ((Button) event.getSource()).getText();

		if (!operator.isEmpty())
			return;

		operator = val;
		num1 = Float.parseFloat(numberOutput.getText());
		numberOutput.setText("");

		numberOutput.setText(String.valueOf(model.calculateUnary(num1, operator)));
		operator = "";
		start = true;
	}

	@FXML
	private void numberClear(ActionEvent event) {
		operator = "";
		start = true;
		numberOutput.setText("");
	}

	@FXML
	private void inputDecimal(ActionEvent event) {
		if (!isDecimal) {
			numberOutput.setText(numberOutput.getText() + ".");
			isDecimal = true;
		}

	}

	@FXML
	private void switchSign(ActionEvent event) {
		String value = numberOutput.getText();
		if (!value.contains("-")) {
			numberOutput.setText('-' + value);
		} else {
			try {
				numberOutput.setText(value.split("-")[1]);
			} catch (IndexOutOfBoundsException e) {

				numberOutput.setText("");
			}
		}
	}
}

