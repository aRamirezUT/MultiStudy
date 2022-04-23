package application.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PlannerModel {
	private static Map<String, String> taskMap = new HashMap<String, String>();
	
	public static String setDay(String date) {
//		converting datePicker's date format to seperate values for LocalDate
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);
        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek.toString();	//day of week in text
	}
	
	public static String returnTasks(){
		String vals = ""; 
		for (String key: taskMap.keySet()) {
			vals += key + "\t" + taskMap.get(key)+"\n";
		}
		System.out.println(vals);
		return vals;
		
	}
	
	public static void addTask(String key, String quantity) throws FileNotFoundException, IOException {
		if (key==null || key.isEmpty()) {					// if user input is null throw error
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Missing Information");
			error.setContentText("Please, try again!");
			error.showAndWait();
		}
//		else if (taskMap.containsKey(key)) {					// if item already exists add X. amount to key and save data to file 
//			int addVal = Integer.parseInt(taskMap.get(key)) + Integer.parseInt(quantity);
//			taskMap.replace(key, String.valueOf(addVal));
//			properties.putAll(taskMap);
//			File file = new File("data.properties");
//			FileOutputStream writer = new FileOutputStream(file, true);
//			properties.store(writer, null);
//		}
		
		else {												// add new item to hashmap and save data to file
//		saveData();	
		taskMap.put(key, quantity);
//		properties.putAll(taskMap);
		System.out.println(taskMap);
//		File file = new File("data.properties");
//		FileOutputStream writer = new FileOutputStream(file, true);
//		properties.store(writer, null);
		}
	}
	
	public static void removeTask(String key, String quantity) throws FileNotFoundException, IOException{
		
	}
}
