package application.controller;

import java.io.IOException;
import application.Main;
import application.Model.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimerController {
	@FXML
	private BorderPane timerPane;

	@FXML
	void homeHandle(ActionEvent event) throws IOException {
		timerPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
		Scene scene = new Scene(timerPane);// pane you are GOING TO show
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
		window.setScene(scene);
		window.show();
	}

	Timer t;
	Thread thread;
	@FXML
	Button timer;

	public TimerController() {
		super();
		System.out.println("start");
		t = Main.t;
	}

	@FXML
	public void initialize() {
		System.out.println("init");
		t.setButton(timer);
		thread = new Thread(t);
		thread.start();
		thread.suspend();

	}

	@FXML
	protected void pause(ActionEvent event) {
		System.out.println("pause");
		thread.suspend();
	}

	@FXML
	protected void start(ActionEvent event) {
		System.out.println("start");
		thread.resume();
	}

	@FXML
	protected void reset(ActionEvent event) {
		System.out.println("reset");
		thread.suspend();
		t.resetTimer();
	}

	@FXML
	protected void hu(ActionEvent event) {
		t.hu();
	}

	@FXML
	protected void hd(ActionEvent event) {
		System.out.println("hd");
		t.hd();
	}

	@FXML
	protected void mu(ActionEvent event) {
		System.out.println("mu");
		t.mu();
	}

	@FXML
	protected void md(ActionEvent event) {
		System.out.println("md");
		t.md();
	}

	@FXML
	protected void su(ActionEvent event) {
		System.out.println("su");
		t.su();
	}

	@FXML
	protected void sd(ActionEvent event) {
		System.out.println("sd");
		t.sd();
	}
}
