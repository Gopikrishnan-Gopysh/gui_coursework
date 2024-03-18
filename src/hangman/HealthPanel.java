package hangman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HealthPanel extends JPanel {
	
	private int maxIncorrectGuesses;
	private int incorrectGuesses;
	private List<JLabel> healthLabels;
	public HealthPanel(int maxIncorrectGuesses) {
		this.maxIncorrectGuesses = maxIncorrectGuesses;
		this.incorrectGuesses = 0;
		this.healthLabels = new ArrayList<>();
		setLayout(new FlowLayout());
		// Initialize the panel with green labels
		for (int i = 0; i < maxIncorrectGuesses; i++) {
			JLabel label = new JLabel(" ");
			label.setOpaque(true);
			label.setBackground(Color.GREEN);
			add(label);
			healthLabels.add(label);
		}
	}
	// Method to update the HealthPanel when an incorrect guess is made
	public boolean removeLife() {
		if (incorrectGuesses < maxIncorrectGuesses) {
			JLabel label = healthLabels.get(incorrectGuesses);
			label.setBackground(Color.RED);
			incorrectGuesses++;
			// Return true if the player has run out of incorrect guesses
			return (incorrectGuesses == maxIncorrectGuesses);
		}
		return false;
	}
}