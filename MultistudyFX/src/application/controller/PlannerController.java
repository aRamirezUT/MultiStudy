/**
 * @author tnu755
 */
package application.controller;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import application.Model.PlannerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlannerController {
	File file = new File("taskData.txt");
    @FXML
    private TextField taskDetails;
    @FXML
    private TextField selectedDay;
    @FXML
    private TextArea textArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private BorderPane plannerPane;  
    @FXML
    private TextField time;
    
    @FXML
    void homeHandle(ActionEvent event) throws IOException {
    	plannerPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(plannerPane);
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
    	window.setScene(scene);
    	window.show();
    }
    
    @FXML
    void datePicked(ActionEvent event) throws IOException{
        //add action listener to the date picker
        datePicker.setOnAction(action -> {
        selectedDay.setText(PlannerModel.setDay(datePicker.getValue().toString())+"'S Tasks");
        textArea.clear();
        textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
        });
    }
    
    @FXML
    void addTasks(ActionEvent event) throws IOException {
//    	textArea.clear();
    	PlannerModel.addEvent(datePicker.getValue().toString(), "taskData.txt", time.getText().toString(), taskDetails.getText().toString());
    	textArea.clear();
    	textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
//    	System.out.println(CalendarLogic.printDay(datePicker.getValue().toString(),"taskData.txt"));
    	taskDetails.clear();
    	taskDetails.setText("Task Details");
    	time.clear();
    	time.setText("Enter Time 00:00");
    }
    
    @FXML
    void removeTask(ActionEvent event) throws FileNotFoundException, IOException {
//    	System.out.println("event to remove " + time.getText().toString()+ " " + taskDetails.getText().toString());
    	textArea.clear();
    	PlannerModel.deleteEvent(time.getText().toString()+ " " + taskDetails.getText().toString(),datePicker.getValue().toString(), "taskData.txt");
    	textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
    }
    
}
