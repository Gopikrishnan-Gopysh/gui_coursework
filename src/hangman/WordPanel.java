package hangman;

import javax.swing.*;
import java.awt.*;

public class WordPanel extends JPanel{

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
}