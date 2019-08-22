package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import model.HighScore;
public class HighScoreTest {


	@Test
	public void test() throws IOException { 

		/*
		 * This test is designed to test the AddEntry function. Here we add a new score to the high score, and we make sure that the List
		 * should be updated accordingly.
		 */
		try {
			System.out.println("Starting first test");
			HighScore hs = new HighScore();
			String[] strings = hs.getHighScores();
			System.out.println("------  Old highScores");
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}
			//assertEquals("fisk", strings[0]);
			hs.AddEntry("TestGuy", 5, 4);
			hs = new HighScore();
			strings = hs.getHighScores();

			System.out.println("----- New highScores");
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}

			assertTrue(strings[4].equalsIgnoreCase("TestGuy : 5"));


		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void test2() throws IOException {
		/*
		 * This test will try to add an entry at the top, and we will try to push all the other entries on the list.
		 * We start up by manually setting the highscore to what we wish.
		 */
		try {
			System.out.println("Starting Second test");
			HighScore hs = new HighScore();


			for (int i = 0; i < 5; i++) {
				hs.AddEntry("player "+i, 90+i, i);
			}
			String[] strings = hs.getHighScores();
			System.out.println("------  Old highScores");
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}

			hs.AddEntry("TestGuy", 85, 0);
			System.out.println("----- New highScores");
			hs = new HighScore();
			strings = hs.getHighScores();
			for (int i = 0; i < strings.length; i++) {
				System.out.println(strings[i]);
			}

			assertTrue(strings[4].equalsIgnoreCase("player 3 : 93"));

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
