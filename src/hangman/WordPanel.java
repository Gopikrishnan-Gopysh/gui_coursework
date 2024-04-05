package hangman;

import javax.swing.*;
import java.awt.*;

// The panel displaying the word to be guessed in the Hangman game.
public class WordPanel extends JPanel{

  
    private static final long serialVersionUID = 1L;
	private String secretWord;
    private JLabel wordLabel;
    Font font=new Font("Arial",Font.PLAIN,25);

    // Constructor for the WordPanel.
    public WordPanel(String secretWord) {
        this.secretWord = secretWord;
        this.wordLabel = new JLabel(getInitialDisplay());
        this.wordLabel.setFont(font);

        setLayout(new FlowLayout());
        add(wordLabel);
    }

    // Generates the initial display of the word with underscores.
    private String getInitialDisplay() {
    
        return "_ ".repeat(secretWord.length()).trim();
    }

    // Guesses a letter in the word.
    public boolean guess(String letter) {
        boolean correctGuess = false;
        this.setFont(font);
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.substring(i, i + 1).equalsIgnoreCase(letter)) {
            	this.setFont(font);
                // Replace underscore with the guessed letter
                StringBuilder updatedDisplay = new StringBuilder(wordLabel.getText());
                updatedDisplay.setCharAt(i * 2, secretWord.charAt(i));
                wordLabel.setText(updatedDisplay.toString());
                correctGuess = true;
            }
        }

        return correctGuess;
    }

    // Checks if the word has been fully guessed.
    public boolean isWordGuessed() {
        return !wordLabel.getText().contains("_");
    }

    // Retrieves the current display of the word.
    public String getWordDisplay() {
        return wordLabel.getText();
    }

    // Retrieves the remaining lives (not used in this class).
    public int getRemainingLives() {
        return 0;
    }

    // Retrieves the secret word.
    public String getSecretWord() {
        return secretWord;
    }

    // Displays the initial word with underscores.
    public void displayWord() {
    	this.setFont(font);
        StringBuilder initialDisplay = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            initialDisplay.append("_ ");
        }
        wordLabel.setText(initialDisplay.toString().trim());
    }

    // Resets the word panel with a new secret word.
    public void resetWord(String secretWord) {
        this.secretWord = secretWord;
        StringBuilder initialDisplay = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            initialDisplay.append("_ ");
        }
        wordLabel.setText(initialDisplay.toString().trim());
    }
}
