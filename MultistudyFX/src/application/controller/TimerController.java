package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimerController {
    @FXML
    private BorderPane timerPane; 
    @FXML
    void homeHandle(ActionEvent event) throws IOException {
    	timerPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(timerPane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

}

