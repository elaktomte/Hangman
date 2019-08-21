package controller;
import model.WordList;
import model.HighScore;

import view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CentralController {
	View view = new View();
	WordList list = new WordList();
	String word;
	int guesses = 0;
	boolean[] hidden;
	StringBuilder sb = new StringBuilder();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> HighScores = new ArrayList<String>();
	static HighScore hs;

	public CentralController() {

	}


	public static void main(String[] args) throws FileNotFoundException {
		CentralController controller;
		hs = new HighScore();
		controller = new CentralController();
		controller.Start();
		


	}

	public void Start() {
		//view.printHighScores(hs.getHighScores());
		view.StartMenu();
		String input = scanner.next();
		int selection = checkInputError(input);
		try {
			StartMenuSelection(selection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int checkInputError(String str) {
		try {
			int input = Integer.parseInt(str);
			return input;
		}
		catch (NumberFormatException e) {
			System.err.println("Faulty Input, It was not an integer");
		}
		return -1;
	}
	public void NewGame() {
		view.printString("Good Luck! enter 00 to go back to main menu.");
		view.Gallows(0);
		word = list.randomWord();
		hidden = new boolean[word.length()];
		view.printString(BuildCorrectString(hidden));
		guesses = 0;
	}
	public void Victory() {
		System.out.println("You won!");
	}
	public String BuildCorrectString(boolean[] hiddn) {
		sb= new StringBuilder();
		for (int i = 0; i < hiddn.length; i++) {
			if (hiddn[i] == true) {
				sb.append(word.charAt(i));
			}
			else {
				sb.append("_");
			}
		}
		return sb.toString();
	}
	public void StartGame() throws IOException {
		int totalFound = 0;
		Long timer = System.currentTimeMillis();
		while (guesses <8) {
			String input = scanner.next();
			if (input.equalsIgnoreCase("00")) {
				Start();
				break;
			} else {
				Guess(input);

				totalFound = 0;
				for (int i = 0; i < hidden.length; i++ ) {
					if (hidden[i] == true) {
						totalFound++;
					}
				}
				if (totalFound == hidden.length) {
					//Victory
					break;
				}
			}
			
		}
		if (guesses < 8) {
			int Score = (int)(System.currentTimeMillis() - timer)/1000;
			System.out.println(Score);
			int position = hs.checkForNewHighscore(Score);
			if(position <5) {
				view.printString("New Highscore! Please enter your name.");
				String name = scanner.next();
				//System.out.println("Trying to score at position : "+position);
				hs.AddEntry(name, Score, position);
			}
			view.printWin();
			String nextInput = scanner.next();
			if (nextInput.equals("1")) {
				Start();
			}
			else {
				System.exit(0);
			}
		}
		else {
			view.printLoss();
			String nextInput = scanner.next();
			if (nextInput.equals("1")) {
				Start();
			}
			else {
				System.exit(0);
			}
		}

	}
	public void StartMenuSelection (int i) throws IOException {
		if (i == 1) {
			NewGame();
			StartGame();
		}
		if (i == 2) {
			view.printHighScores(hs.getHighScores());
		}
		if (i == 3) { 
			System.exit(0);
		}
		else {

		}
	}
	public void Guess(String w) {
		if(w.length() == 0) {
			System.err.println("You must enter at least one character!");
		}
		else if (w.length() > 1) { //guessing the whole word.
			if (w.equalsIgnoreCase(word)) {
				for (int i = 0; i < hidden.length; i++) {
					hidden[i] = true;
				}
			}
			else {
				guesses++;
				view.Gallows(guesses);
				view.printString(BuildCorrectString(hidden));
			}
		}
		else {
			char letter = w.charAt(0);
			boolean found = false;
			for (int i = 0; i < word.length(); i++) {
				if (letter == word.charAt(i)) {
					hidden[i] = true;
					found = true;
				}
			}
			if (!found) {
				guesses++;
				view.Gallows(guesses);
				view.printString(BuildCorrectString(hidden));
			}
			else if (found) {
				view.Gallows(guesses);
				view.printString(BuildCorrectString(hidden));
			}
		}

	}


}
