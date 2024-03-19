package hangman;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	private HealthPanel healthPanel;
	private ButtonPanel buttonPanel;
	private WordPanel wordPanel;
	private String secretWord;

	public MainWindow() {
		
		healthPanel = new HealthPanel(7); // 7 incorrect guesses allowed
		secretWord = getRandomWordFromFile("wordlist.txt");	// Load the list of words from a file and select a random word
		wordPanel = new WordPanel(secretWord);
		buttonPanel = new ButtonPanel(wordPanel);

		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		add(healthPanel, BorderLayout.NORTH);
		add(wordPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null); // Center the window
		setVisible(true);
	}

	private String getRandomWordFromFile(String filename) {
		String[] words = {"cat", "cab", "car", "cap"};
		return words[(int) (Math.random() * words.length)];
	}

	public void startGame() {
		System.out.println("Welcome to Hangman!");
		displayGame();
		endGame();
	}

	private void displayGame() {
		healthPanel.displayHealth();
		System.out.println("Word: " + wordPanel.getWordDisplay());
		buttonPanel.displayButtons();
	}

	private void endGame() {
		if (wordPanel.isWordGuessed()) {
			System.out.println("Congratulations! You guessed the word: " + secretWord);
		} else {
			System.out.println("Sorry, you ran out of attempts. The correct word was: " + secretWord);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainWindow().startGame();
		});
	}
}