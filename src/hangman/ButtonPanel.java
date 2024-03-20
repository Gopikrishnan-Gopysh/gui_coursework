package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int ALPHABET_SIZE = 26;
	private JButton[] letterButtons;
	private WordPanel wordPanel;
	private HealthPanel healthPanel;
	private MainWindow mainWindow;

	public ButtonPanel(WordPanel wordPanel, HealthPanel healthPanel, MainWindow mainWindow) {

		this.wordPanel = wordPanel;
		this.letterButtons = new JButton[ALPHABET_SIZE];
		this.mainWindow = mainWindow; 
		this.healthPanel = healthPanel;

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
				button.setEnabled(false); // Disable the button after it's clicked
				handleButtonClick(buttonText);
			}
		});
		return button;
	}

	private void handleButtonClick(String guessedLetter) {
		boolean correctGuess = wordPanel.guess(guessedLetter);
		if (!correctGuess) {
			healthPanel.removeLife(); // Remove a life if the guess is incorrect
			if (healthPanel.isGameOver()) {
				// Game over due to running out of lives
				System.out.println("Sorry, you ran out of lives. The correct word was: " + wordPanel.getSecretWord());
				disableAllButtons();
				mainWindow.endGame(false);
			} else {
				char incorrectLetter = guessedLetter.charAt(0);
				int remainingLives = healthPanel.getRemainingLives();
				System.out.println("The button " + incorrectLetter + " is incorrect. You have " + remainingLives + " of 7 lives left.");
			}
		} else if (wordPanel.isWordGuessed()) {
			// Word guessed correctly
			System.out.println("Congratulations! You guessed the entire word correctly.");
			disableAllButtons();
			mainWindow.endGame(true);
		}
	}

	private void disableAllButtons() {
		for (JButton button : letterButtons) {
			button.setEnabled(false);
		}
	}

	public void displayButtons() {
		for (JButton button : letterButtons) {
			button.setEnabled(true); // Enable all buttons at the beginning of each game
		}
	}

	public void resetButtons() {
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			letterButtons[i].setEnabled(true); // Enable all buttons
		}
	}
}

