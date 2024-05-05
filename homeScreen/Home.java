package homeScreen;

import javax.swing.*;

public class Home extends JPanel {
    private JLabel welcomeLabel;

    public Home() {
        initializeComponents();
    }

    private void initializeComponents() {
        welcomeLabel = new JLabel("Welcome, Home");
        add(welcomeLabel);
    }

    public void displayWelcomeMessage() {
        welcomeLabel.setText("Hello, Welcome Home");
    }
}
