package application;

import java.awt.TextField;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SampleController implements Initializable {
	ObservableList<String> savedNotes = FXCollections.observableArrayList();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now(); 
	 @FXML
	ListView notes ;
	@FXML
	Button addNote;
	@FXML
	TextArea note;
	@FXML
	Text date;
	
	
	@FXML
	private void addNote(Event e) {
		
		if(note.getText().length()>0) {
			
			
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
