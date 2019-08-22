package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.WordList;

public class WordListTest {

	@Test
	public void testRandomWord() {
		/*
		 * This test verifies that we can get random words. 
		 * In this test we want to make sure that we can get the same word multiple times.
		 */
		WordList wl = new WordList();
		String word = wl.randomWord();
		String randomWord = wl.randomWord();
		for (int i = 0; i < 1000; i++) {
			randomWord = wl.randomWord();
			if (randomWord.equalsIgnoreCase(word)) {
			break;
			}
		}
		
		assertTrue(randomWord.equalsIgnoreCase(word));
	}
	
	@Test
	public void testRandomWord2() {
		/*
		 * This test is to verify that we can get random words and 
		 * not just the same word all over.
		 */
		WordList wl = new WordList();
		String firstWord = wl.randomWord();
		String secondWord = wl.randomWord();
		
		System.out.println("The first word is : "+firstWord);
		
		
		boolean answer = true;
		for (int i = 0; i < 100; i++) {
			if( !firstWord.equalsIgnoreCase(secondWord)) {
				answer = false;
				break;
			}
			secondWord = wl.randomWord();
		}
		System.out.println("The second word is : "+secondWord);
		
		assertFalse(answer);
	}
	
	@Test
	public void testAddWord() {
		/*
		 * This test is to ensure that we can add words to our wordlist
		 * and have the chance to get them as a random word.
		 * This test could fail due to the word being random however,
		 *  the chances are very slim that this will occur with the low amount of possible words
		 * and the amount of attempts to get the new word.
		 */
		WordList wl = new WordList();
		wl.addWord("testWord");
		String randomWord = wl.randomWord();
		for (int i = 0; i < 1000; i++) {
			randomWord = wl.randomWord();
			if (randomWord.equalsIgnoreCase("testword")) {
			break;
			}
		}
		
		assertTrue(randomWord.equalsIgnoreCase("testword"));
	}
	
	@Test
	public void testAddWordPersistance() {
		/*
		 * This test is to ensure that the words we add stay
		 *  even after we shut down the application.
		 */
		WordList wl = new WordList();
		wl.addWord("testWord");
		
		wl = new WordList(); // re-initializing the WordList.
		
		String randomWord = wl.randomWord();
		for (int i = 0; i < 1000; i++) {
			randomWord = wl.randomWord();
			if (randomWord.equalsIgnoreCase("testword")) {
			break;
			}
		}
		assertTrue(randomWord.equalsIgnoreCase("testword"));
		
	}

}
