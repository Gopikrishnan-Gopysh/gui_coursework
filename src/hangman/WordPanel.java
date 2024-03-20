package hangman;

import javax.swing.*;
import java.awt.*;

public class WordPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private String secretWord;
	private JLabel wordLabel;

	public WordPanel(String secretWord) {

		this.secretWord = secretWord;
		this.wordLabel = new JLabel(getInitialDisplay());

		setLayout(new FlowLayout());
		add(wordLabel);
	}

	private String getInitialDisplay() {
		return "_ ".repeat(secretWord.length()).trim();
	}

	public boolean guess(String letter) {
		boolean correctGuess = false;

		for (int i = 0; i < secretWord.length(); i++) {
			if (secretWord.substring(i, i + 1).equalsIgnoreCase(letter)) {
				// Replace underscore with the guessed letter
				StringBuilder updatedDisplay = new StringBuilder(wordLabel.getText());
				updatedDisplay.setCharAt(i * 2, secretWord.charAt(i));
				wordLabel.setText(updatedDisplay.toString());
				correctGuess = true;
			}
		}

		return correctGuess;
	}

	public boolean isWordGuessed() {
		return !wordLabel.getText().contains("_");
	}

	public String getWordDisplay() {
		return wordLabel.getText();
	}

	public int getRemainingLives() {
		return 0;
	}

	public String getSecretWord() {
		return secretWord;
	}

	public void displayWord() {
		StringBuilder initialDisplay = new StringBuilder();
		for (int i = 0; i < secretWord.length(); i++) {
			initialDisplay.append("_ ");
		}
		wordLabel.setText(initialDisplay.toString().trim());
	}

	public void resetWord(String secretWord2) {
		this.secretWord = secretWord2;
		StringBuilder initialDisplay = new StringBuilder();
		// Iterate over each character in the secret word
		for (int i = 0; i < secretWord2.length(); i++) {
			// Append an underscore followed by a space to the initial display
			initialDisplay.append("_ ");
		}
		// Set the text of the word label to the initial display
		wordLabel.setText(initialDisplay.toString().trim());
	}
}