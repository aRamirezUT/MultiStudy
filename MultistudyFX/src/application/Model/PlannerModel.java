package application.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PlannerModel {
	public static String setDay(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("Day of week in number: "+dayOfWeek.getValue());
        System.out.println("Day of week in text: "+dayOfWeek.toString());
		return dayOfWeek.toString();
	}
	//Reader Logic
	public static String printDay(String date, String string) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		boolean end = false;
		String str = "";
		
	try {
		FileReader fileReader = new FileReader(string);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = reader.readLine();

		while (line != null) {
			if (line.equals(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year))) {
				line=reader.readLine();
				str += line+"\n";
				while ( line != null && !(line.equals(""))) {
					line = reader.readLine();
					if (line == null || !(line.equals(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
						line = null;
					} else {
						line = reader.readLine();
						str += line + "\n";
					}
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();

	}
	return str;
}

	//Writer Logic
	public static void addEvent(String date, String string, String time, String details) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
	
		try {
			FileWriter fileWriter = new FileWriter(string, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
	
			FileReader fileReader = new FileReader(string);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			Boolean eventExistsAlready = false;
	
			String event = scheduleEvent(time, details);
			//Logic to place the schedule correctly in calendar.txt
			// assume that if the first and second line are null then the whole file is null
			if (line == null && reader.readLine() == null) {
				writer.write(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year));
				writer.newLine();
				writer.write(event);
				writer.newLine();
				writer.flush();
			}
			else {
				while (line != null && !(eventExistsAlready)) {
					line = reader.readLine();
					if (line ==  null){
						//if date is not found
						writer.write(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year));
						writer.newLine();
						writer.write(event);
						writer.newLine();
						writer.flush();
					}
					else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
						line = reader.readLine();
						if (line.equals(event)) {
							System.out.println("That event already exists!");
							eventExistsAlready = true;
						}
					}
				}
			}
			reader.close();
			writer.close();
	
			System.out.println();
			printDay(date, string);
		}
	
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
	
		}
	}
	
	public static void deleteEvent(String event, String date, String string) throws FileNotFoundException, IOException {
	//	 now write newLines to file
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		String fdate = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
		File inputFile = new File(string);
		File tempFile = new File("myTempFile.txt");
	
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String lineToRemove = event;
		String currentLine;
	
		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    String readNext = reader.readLine();
		    if(readNext.equals(lineToRemove) && trimmedLine.equals(fdate)) continue;{
		    	writer.write(currentLine + "\n" + readNext + System.getProperty("line.separator"));
		    }
		}
		writer.close(); 
		reader.close();
		inputFile.delete();
		boolean successful = tempFile.renameTo(inputFile);
	}
	
	public static String scheduleEvent(String time, String details) {
		String eventTime[] = time.split(":", 2);
		String eventDescription = details;
		String event = eventTime[0] + ":" + eventTime[1] + " " + eventDescription;
		return event;
	}
}
