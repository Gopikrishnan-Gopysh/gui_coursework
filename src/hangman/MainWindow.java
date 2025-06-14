package hangman;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private HealthPanel healthPanel; // Low coupling
    private ButtonPanel buttonPanel; // Low coupling
    private WordPanel wordPanel; // Low coupling
    private String secretWord;

    // Constructor
    public MainWindow() {
        // Initialize components (All of them have low coupling)
        healthPanel = new HealthPanel(7); // 7 incorrect guesses allowed
        secretWord = getRandomWordFromFile("wordlist.txt"); // Load the list of words from a file and select a random word
        wordPanel = new WordPanel(secretWord); 
        buttonPanel = new ButtonPanel(wordPanel, healthPanel, this); 

        // Set up JFrame
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));


        // Add components to JFrame
        add(healthPanel, BorderLayout.NORTH);
        add(wordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Center the JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to get a random word from a file
    private String getRandomWordFromFile(String filename) {
        List<String> words = readWordsFromFile(filename); // Low coupling (Uses method to read words)
        if (words.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    // Method to read words from a file and store them in a list
    private List<String> readWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading words from file: " + e.getMessage());
        }
        return words;
    }

    // Method to start the game
    public void startGame() {
        System.out.println("Welcome to Hangman!");
        wordPanel.displayWord(); // Functional cohesion (Displays word, closely related to game start)
    }

    // Method to end the game and display a message dialog
    public void endGame(boolean isWordGuessed) {
        String message;
        String title;
        int messageType;

        if (isWordGuessed) {
            message = "Congratulations! You guessed the word: " + secretWord + "\n\nDo you want to play again?";
            title = "Game Over";
            messageType = JOptionPane.INFORMATION_MESSAGE;
        } else {
            message = "Sorry, you ran out of attempts. The correct word was: " + secretWord + "\n\nDo you want to play again?";
            title = "Game Over";
            messageType = JOptionPane.ERROR_MESSAGE;
        }

        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, messageType);

        if (option == JOptionPane.YES_OPTION) {
            // Reset the game
            resetGame();
        } else {
            // Exit the game
            System.exit(0);
        }
    }

    // Method to reset the game
    private void resetGame() {
        // Generate a new random word
        secretWord = getRandomWordFromFile("wordlist.txt");
        // Reset the word panel with the new word
        wordPanel.resetWord(secretWord);
        // Reset the health panel with the initial number of lives
        healthPanel.resetHealth(7);
        // Reset buttons
        buttonPanel.resetButtons(); // Reset all buttons
        // Start the new game
        startGame();
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().startGame();
        });
    }
}
