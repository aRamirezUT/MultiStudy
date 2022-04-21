package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
    @FXML
    private AnchorPane homePane;
    
    @FXML
    void swtchCalculator(ActionEvent event)throws IOException {
    	homePane = FXMLLoader.load(getClass().getResource("/application/view/Calculator.fxml"));// going to classifieds Pane
    	Scene scene = new Scene(homePane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void swtchTimer(ActionEvent event)throws IOException {
    	homePane = FXMLLoader.load(getClass().getResource("/application/view/Timer.fxml"));// going to classifieds Pane
    	Scene scene = new Scene(homePane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void swtchPlanner(ActionEvent event)throws IOException {
    	homePane = FXMLLoader.load(getClass().getResource("/application/view/Planner.fxml"));// going to classifieds Pane
    	Scene scene = new Scene(homePane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void swtchNotepad(ActionEvent event)throws IOException {
    	homePane = FXMLLoader.load(getClass().getResource("/application/view/Notepad.fxml"));// going to classifieds Pane
    	Scene scene = new Scene(homePane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }

}
