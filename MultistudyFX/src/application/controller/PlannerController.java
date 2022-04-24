/**
 * @author tnu755
 * Planner Controller, self explanitory
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
    /**
     * Takes user back to main page
     * @param event
     * @throws IOException
     */
    @FXML
    void homeHandle(ActionEvent event) throws IOException {
    	plannerPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(plannerPane);
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
    	window.setScene(scene);
    	window.show();
    }
    /**
     * displays the day of week based off what the user selects in the calendar
     * appends current tasks for the user picked day
     * @param event
     * @throws IOException
     */
    @FXML
    void datePicked(ActionEvent event) throws IOException{
        //add action listener to the date picker
        datePicker.setOnAction(action -> {
        selectedDay.setText(PlannerModel.setDay(datePicker.getValue().toString())+"'S Tasks");
        textArea.clear();
        textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
        });
    }
    /**
     * adds a task to the day the user picked from calendar,
     * refreshes task screen to display new task added
     * @param event
     * @throws IOException
     */
    @FXML
    void addTasks(ActionEvent event) throws IOException {
    	PlannerModel.addEvent(datePicker.getValue().toString(), "taskData.txt", time.getText().toString(), taskDetails.getText().toString());
    	textArea.clear();
    	textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
    	taskDetails.clear();
    	taskDetails.setText("Task Details");
    	time.clear();
    	time.setText("Enter Time 00:00");
    }
    /**
     * removes a task from the user picked day,
     * refreshes task screen to display tasks after deletion
     * @param event
     * @throws FileNotFoundException
     * @throws IOException
     */
    @FXML
    void removeTask(ActionEvent event) throws FileNotFoundException, IOException {
    	textArea.clear();
    	PlannerModel.deleteEvent(time.getText().toString()+ " " + taskDetails.getText().toString(),datePicker.getValue().toString(), "taskData.txt");
    	textArea.appendText(PlannerModel.printDay(datePicker.getValue().toString(),"taskData.txt"));
    }
    
}
