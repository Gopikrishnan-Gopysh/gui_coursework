package hangman;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	private HealthPanel healthPanel;
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create an instance of HealthPanel
		healthPanel = new HealthPanel(7); // Assuming 7 incorrect guesses allowed
		add(healthPanel, BorderLayout.NORTH);
		setLocationRelativeTo(null); // Center the window
	}
}