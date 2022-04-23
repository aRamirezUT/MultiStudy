/**
 * @author tnu755
 */
package application.controller;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import java.io.IOException;
import java.util.Optional;
import application.Model.PlannerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlannerController {
    @FXML
    private TextField taskName;
    @FXML
    private TextField taskDetails;
    @FXML
    private ListView<String> taskList;
    @FXML
    private TextField selectedDay;
    @FXML
    private DatePicker datePicker;
    @FXML
    private BorderPane plannerPane;  
    
    @FXML
    void homeHandle(ActionEvent event) throws IOException {
    	plannerPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(plannerPane);// pane you are GOING TO show
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;// pane you are ON
    	window.setScene(scene);
    	window.show();
    }
    
    @FXML
    void datePicked(ActionEvent event) {
        //add action listener to the date picker
        datePicker.setOnAction(action -> {
//            System.out.println("Date Picked: "+datePicker.getValue());
        selectedDay.setText(PlannerModel.setDay(datePicker.getValue().toString())+"'S Tasks");
        taskList.getItems().clear();
        taskList.getItems().add(PlannerModel.returnTasks());
        });
    }
    
    @FXML
    void addTasks(ActionEvent event) throws IOException {
    	String key = taskName.getText().toString();
    	String quantity = taskDetails.getText().toString();
    	PlannerModel.addTask(key, quantity);
    	taskList.getItems().clear();
    	taskList.getItems().add(PlannerModel.returnTasks());
    	taskName.clear();
    	taskDetails.clear();
    }
    
    @FXML
    void removeTask(ActionEvent event) {
    	TextInputDialog textInput = new TextInputDialog();
    	textInput.setTitle("Remove Task");
    	textInput.getDialogPane().setContentText("Enter Task Name to be Removed");
    	Optional<String> result = textInput.showAndWait();
    	TextField input = textInput.getEditor();
    	if(input.getText() != null && input.getText().toString().length() != 0) {
    		System.out.println(input.getText().toString());
    		taskList.getItems().add(PlannerModel.returnTasks());
    	}
    }
    
}
