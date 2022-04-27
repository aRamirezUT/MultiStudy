package application.controller;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NotepadController implements Initializable {
	ObservableList<String> savedNotes = FXCollections.observableArrayList();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now();
	
    @FXML
    private Button add;
    @FXML
    private Text date;
    @FXML
    private TextArea note;
    @FXML
    private ListView<String> notes;
    @FXML
    private BorderPane notepadPane;

    @FXML
    void home(ActionEvent event) throws IOException {
    	notepadPane = FXMLLoader.load(getClass().getResource("/application/view/Main.fxml"));// going to Main Pane
    	Scene scene = new Scene(notepadPane);
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
    	window.setScene(scene);
    	window.show();
    }
    
	@FXML
	private void addNote(ActionEvent event) {
		if(note.getText().length() > 0) {
			savedNotes.add(note.getText().toString());
			notes.getItems().add(note.getText());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.print(savedNotes);
		date.setText(dtf.format(now));
		
		
	}
}
