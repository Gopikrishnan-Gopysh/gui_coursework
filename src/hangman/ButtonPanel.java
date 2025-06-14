package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel containing the letter buttons for the Hangman game.
public class ButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int ALPHABET_SIZE = 26;
    private JButton[] letterButtons;
    private WordPanel wordPanel;
    private HealthPanel healthPanel;
    private MainWindow mainWindow;

    // Constructor for the ButtonPanel.
    public ButtonPanel(WordPanel wordPanel, HealthPanel healthPanel, MainWindow mainWindow) {
        this.wordPanel = wordPanel;
        this.letterButtons = new JButton[ALPHABET_SIZE];
        this.mainWindow = mainWindow;
        this.healthPanel = healthPanel;

        setLayout(new GridLayout(2, 13));

        // Create letter buttons
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            char letter = (char) ('A' + i);
            letterButtons[i] = createButton(String.valueOf(letter));
            add(letterButtons[i]);
        }
    }

    // Creates a letter button.
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

    // Handles button clicks.
    private void handleButtonClick(String guessedLetter) {
        boolean correctGuess = wordPanel.guess(guessedLetter);
        if (!correctGuess) {
            healthPanel.removeLife(); // Remove a life if the guess is incorrect // Low coupling (Interacts with HealthPanel through method call)
            if (healthPanel.isGameOver()) {
                // Game over due to running out of lives
                System.out.println("Sorry, you ran out of lives. The correct word was: " + wordPanel.getSecretWord());
                disableAllButtons();
                mainWindow.endGame(false); // Low coupling (Interacts with MainWindow through method call)
            } else {
                char incorrectLetter = guessedLetter.charAt(0);
                int remainingLives = healthPanel.getRemainingLives();
                System.out.println("The button " + incorrectLetter + " is incorrect. You have " + remainingLives + " of 7 lives left.");
            }
        } else if (wordPanel.isWordGuessed()) {
            // Word guessed correctly
            System.out.println("Congratulations! You guessed the entire word correctly.");
            disableAllButtons();
            mainWindow.endGame(true); // Low coupling (Interacts with MainWindow through method call)
        }
    }

    // Disables all letter buttons.
    private void disableAllButtons() {
        for (JButton button : letterButtons) {
            button.setEnabled(false);
        }
    }

    // Enables all letter buttons.
    public void displayButtons() {
        for (JButton button : letterButtons) {
            button.setEnabled(true); // Enable all buttons at the beginning of each game // Low coupling (Interacts with JButton objects)
        }
    }

    // Resets all letter buttons to enabled state.
    public void resetButtons() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            letterButtons[i].setEnabled(true); // Enable all buttons // Low coupling (Interacts with JButton objects)
        }
    }
}
