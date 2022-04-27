package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("NOTEPAD");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
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
