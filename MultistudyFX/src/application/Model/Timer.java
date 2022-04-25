package application.Model;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Timer implements Runnable {
	int hours;
	int mins;
	int secs;

	@FXML
	Button button;

	public Timer() {

		this.hours = 0;
		this.mins = 0;
		this.secs = 0;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
		System.out.println("button set: " + button.getText());

	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMins() {
		return mins;
	}

	public void setMins(int mins) {
		this.mins = mins;
	}

	public int getSecs() {
		return secs;
	}

	public void setSecs(int secs) {
		this.secs = secs;
	}

	private void check() {

		if (secs == 60) {
			secs = 0;
			mins++;
		}
		if (mins == 60) {
			mins = 0;
			hours++;
		}

		if (secs == -1) {
			mins--;
			secs = 59;
		}
		if (mins == -1) {
			hours--;
			mins = 59;
		}
		if (hours == -1) {
			hours = 0;
		}
	}

	public void resetTimer() {
		hours = 0;
		mins = 0;
		secs = 0;
		textUpdate();

	}

	public void hu() {
		hours++;
		check();
		textUpdate();
	}

	public void hd() {
		hours--;
		check();
		textUpdate();
	}

	public void mu() {
		mins++;
		check();
		textUpdate();
	}

	public void md() {
		mins--;
		check();
		textUpdate();
	}

	public void su() {
		secs++;
		check();
		textUpdate();
	}

	public void sd() {
		secs--;
		check();
		textUpdate();
	}

	public void textUpdate() {
		Platform.runLater(() -> {
			button.setText(this.toString());
		});
	}

	@Override
	public String toString() {
		String r = String.format("%02d : %02d : %02d", hours, mins, secs);
		return r;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				su();
				check();
				textUpdate();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
