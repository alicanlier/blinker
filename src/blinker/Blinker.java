package blinker;

import java.sql.Connection;

import javafx.scene.control.TextArea;

public class Blinker {

	private String word;
	private String mean;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	
	@Override
	public String toString() {
		return "Blinker [word=" + word + ", mean=" + mean + "]";
	}
	public TextArea getString(String string, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
