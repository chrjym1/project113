package Authentication;
import javax.swing.*;

import homeScreen.Home;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInSignUp extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private Home home;

    public LogInSignUp(Home home) {
        this.home = home;
        initializePanels();
    }

    private void initializePanels() {
        setLayout(new BorderLayout()); // Use BorderLayout for easier component placement
        setBackground(new Color(240, 240, 240)); // Set background color

        // Create a panel for the card-like container
        JPanel cardPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for internal alignment
        cardPanel.setBackground(Color.WHITE); // Set background color to white
        cardPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Position on the left
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.insets = new Insets(10, 10, 10, 10); // Add consistent spacing
        add(cardPanel, BorderLayout.WEST);

        // Add components to the cardPanel
        JLabel titleLabel = new JLabel("Welcome!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        cardPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1; // Reset grid width

        JButton googleButton = new JButton("Sign in with Google");
        googleButton.setPreferredSize(new Dimension(200, 30));
        cardPanel.add(googleButton, gbc);

        gbc.gridy++;
        JButton facebookButton = new JButton("Login with Facebook");
        facebookButton.setPreferredSize(new Dimension(200, 30));
        cardPanel.add(facebookButton, gbc);

        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        cardPanel.add(emailLabel, gbc);
        gbc.gridy++;
        emailField = new JTextField(15);
        cardPanel.add(emailField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        cardPanel.add(passwordLabel, gbc);
        gbc.gridy++;
        passwordField = new JPasswordField(15);
        cardPanel.add(passwordField, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
            handleLogin();
           } 
        });
        cardPanel.add(loginButton, gbc);


        gbc.gridy++;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100, 30));
        cardPanel.add(signUpButton, gbc);

        ImageIcon imageIcon = new ImageIcon("scr/20547283_6310507-removebg-preview.png"); 
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });
        
    }

    public void handleLogin() {
        String username = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (isValidLoginCredentials(username, password)) {
            // logic ( navigate to the next screen)
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "homeScreen");
            home.displayWelcomeMessage();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleSignUp() {
        String username = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (isValidSignUp(username, password)) {
            // Perform signup 
            JOptionPane.showMessageDialog(this, "Signup successful!"); // Placeholder message
        } else {
            JOptionPane.showMessageDialog(this, "Invalid signup details. Try again.", "Signup Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private boolean isValidLoginCredentials(String username, String password) {
    return username.equals("admin") && password.equals("admin");
}


    private boolean isValidSignUp(String username, String password) {

        return !username.isEmpty() && password.length() >= 8;
    }
}
    
    
    
    


     

