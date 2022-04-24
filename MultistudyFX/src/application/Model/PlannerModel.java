/**
 * @author tnu755
 * PlannerModel
 */
package application.Model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
/**
 * PlannerModel is the model class for PlannerController to call upon following VMC Guidelines
 *
 */
public class PlannerModel {
	/**
	 * method to return the day of week from calendar input
	 * @param date date passed from user selection
	 * @return the day of week to be returned ex. MONDAY
	 */
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
	/**
	 * printDay reads the "taskData.txt" file and returns a string of what is to be read from the user selected date
	 * @param date date to be used
	 * @param string file name
	 * @return string of tasks to be returned
	 */
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

	/**
	 * addEvent writes to "taskData.txt" based off user input task and time,
	 * and then calls printDay to refresh the task view for the newly added task
	 * @param date date passed from user 
	 * @param string file name
	 * @param time time the task should be done at
	 * @param details details of the task
	 */
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
	/**
	 * deleteEvent deletes and event from "taskData.txt" by reading over the given file and writing to a temporary file,
	 * before then deleting temp file and renaming it to the original file name
	 * @param event task in formatted string to be deleted
	 * @param date date on which the task will be deleted
	 * @param string file name
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
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
	/**
	 * scheduleEvent is a method addEvent calls upon to format the passed time and task details to be used in writing to "taskData.txt"
	 * @param time time from user
	 * @param details details of task from user
	 * @return return formatted version of the task event ex. '00:00 do stuff'
	 */
	public static String scheduleEvent(String time, String details) {
		String eventTime[] = time.split(":", 2);
		String eventDescription = details;
		String event = eventTime[0] + ":" + eventTime[1] + " " + eventDescription;
		return event;
	}
}
