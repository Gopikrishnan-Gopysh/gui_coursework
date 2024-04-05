package hangman;

import javax.swing.*;

public class Run {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow mainWindow = new MainWindow();
			mainWindow.setTitle("Hangman Game");
			mainWindow.setSize(800, 600);
			mainWindow.setLocationRelativeTo(null);
			mainWindow.setVisible(true);
		});
	}
}