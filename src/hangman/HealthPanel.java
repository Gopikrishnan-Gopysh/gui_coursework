package hangman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HealthPanel extends JPanel {

	private int lives;
	private List<JLabel>  lifeLabels;

	public HealthPanel(int initialLives) {
		
		this.lives = initialLives;
		this.lifeLabels = new ArrayList<>();

		setLayout(new FlowLayout());
		initializeLifeLabels();
		displayHealth();
	}

	private void initializeLifeLabels() {
		for (int i = 0; i < lives; i++) {
			JLabel lifeLabel = new JLabel("\u25A0 "); // Square character representing a life block
			lifeLabel.setForeground(Color.GREEN);
			lifeLabels.add(lifeLabel);
			add(lifeLabel);
		}
	}

	public void displayHealth() {
		for (int i = 0; i < lifeLabels.size(); i++) {
			if (i < lives) {
				lifeLabels.get(i).setForeground(Color.GREEN);
			} else {
				lifeLabels.get(i).setForeground(Color.RED);
			}
		}
	}

	// Method to update the HealthPanel when an incorrect guess is made
	public void removeLife() {
		if (lives > 0) {
			lives--;
			displayHealth();
		}
	}

	public boolean isGameOver() {
		return lives == 0;
	}
}