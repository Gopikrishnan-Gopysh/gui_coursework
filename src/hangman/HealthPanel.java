package hangman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Panel displaying the health (lives) of the player in the Hangman game.
public class HealthPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int lives;
    private List<JLabel> lifeLabels;

    // Constructs a HealthPanel with the specified initial number of lives.
    public HealthPanel(int initialLives) {
        this.lives = initialLives;
        this.lifeLabels = new ArrayList<>();

        setLayout(new FlowLayout());
        initializeLifeLabels();
        displayHealth();
    }

    // Initializes the life labels representing the player's lives.
    private void initializeLifeLabels() {
        for (int i = 0; i < lives; i++) {
            JLabel lifeLabel = new JLabel("\u2764 "); // Heart character
            lifeLabel.setForeground(Color.GREEN);
            lifeLabels.add(0, lifeLabel);
            add(lifeLabel);
        }
    }

    // Displays the health (lives) by updating the color of life labels.
    public void displayHealth() {
        for (int i = 0; i < lifeLabels.size(); i++) {
            if (i < lives) {
                lifeLabels.get(i).setForeground(Color.GREEN);
            } else {
                lifeLabels.get(i).setForeground(Color.RED);
            }
        }
    }

    // Removes one life from the health panel.
    public void removeLife() {
        if (lives > 0) {
            lives--;
            displayHealth();
        }
    }

    // Checks if the game is over (no remaining lives).
    public boolean isGameOver() {
        return lives == 0;
    }

    // Gets the number of remaining lives.
    public int getRemainingLives() {
        return lives;
    }

    // Resets the health panel with the specified initial number of lives.
    public void resetHealth(int initialLives) {
        // Update the number of lives
        this.lives = initialLives;

        // Clear the existing life labels from the panel
        removeAll();
        lifeLabels.clear();

        // Reinitialize the life labels based on the updated number of lives
        initializeLifeLabels();

        // Redisplay the health
        displayHealth();

        // Repaint the panel to reflect the changes
        revalidate();
        repaint();
    }
}
