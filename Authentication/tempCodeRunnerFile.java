package Authentication;

import javax.swing.*;
import java.awt.*;

public class LogInSignUp extends JPanel {
    public LogInSignUp() {
        initializePanels();
    }

    private void initializePanels() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240)); // Set background color

        // Create a panel for the card-like container
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE); // Set background color to white
        cardPanel.setPreferredSize(new Dimension(500, 500)); // Set preferred size
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // Position on the right
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 0, 5, 70);
        add(cardPanel, gbc);

        // Add components to the cardPanel
        JLabel titleLabel = new JLabel("Welcome to Design School");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy++;
        cardPanel.add(titleLabel, gbc);

        // Create a panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        inputGbc.anchor = GridBagConstraints.WEST;
        inputGbc.insets = new Insets(5, 5, 5, 5);

        JLabel emailLabel = new JLabel("Email:");
        inputGbc.insets = new Insets(10, 0, 0, 0);
        inputPanel.add(emailLabel, inputGbc);

        JTextField emailField = new JTextField(15);
        inputGbc.gridx++;
        inputGbc.insets = new Insets(0, 0, 0, 0);
        inputPanel.add(emailField, inputGbc);

        JLabel passwordLabel = new JLabel("Password:");
        inputGbc.gridx = 0;
        inputGbc.insets = new Insets(0, 0, 0, 0);
        inputGbc.gridy++;
        inputPanel.add(passwordLabel, inputGbc);

        JPasswordField passwordField = new JPasswordField(15);
        inputGbc.gridx++;
        inputPanel.add(passwordField, inputGbc);

        gbc.gridy++;
        cardPanel.add(inputPanel, gbc);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton googleButton = new JButton("Sign in with Google");
        googleButton.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(googleButton);

        JButton facebookButton = new JButton("Login with Facebook");
        facebookButton.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(facebookButton);

        gbc.gridy++;
        cardPanel.add(buttonPanel, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridy++;
        cardPanel.add(loginButton, gbc);

        JLabel registerLabel = new JLabel("Don't have an account? Register");
        gbc.gridy++;
        cardPanel.add(registerLabel, gbc);

        // Add some space between the card and the photo
        gbc.gridx = 0; // Position on the left
        gbc.weightx = 1.0; // Fill available space
        add(Box.createHorizontalStrut(20), gbc);
    }
}
