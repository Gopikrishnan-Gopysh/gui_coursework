package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonPanel extends JPanel {
	private static final int ALPHABET_SIZE = 26;
	private JButton[] letterButtons;
	private WordPanel wordPanel;

	public ButtonPanel(WordPanel wordPanel) {
		
		this.wordPanel = wordPanel;
		this.letterButtons = new JButton[ALPHABET_SIZE];

		setLayout(new GridLayout(2, 13)); 

		for (int i = 0; i < ALPHABET_SIZE; i++) {
			char letter = (char) ('A' + i);
			letterButtons[i] = createButton(String.valueOf(letter));
			add(letterButtons[i]);
		}
	}

	private JButton createButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleButtonClick(buttonText);
				button.setEnabled(false); // Disable the button after it's clicked
			}
		});
		return button;
	}

	private void handleButtonClick(String guessedLetter) {
		boolean correctGuess = wordPanel.guess(guessedLetter);
		if (correctGuess && wordPanel.isWordGuessed()) {
			System.out.println("Congratulations! You guessed the entire word correctly.");
		} else if (!correctGuess && wordPanel.getRemainingLives() == 0) {
			System.out.println("Sorry, you ran out of lives. The correct word was: " + wordPanel.getSecretWord());
		}
	}

	public void displayButtons() {
		for (JButton button : letterButtons) {
			button.setEnabled(true); // Enable all buttons at the beginning of each game
		}
	}
}

