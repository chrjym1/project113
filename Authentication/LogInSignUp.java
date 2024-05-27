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
        addEnterKeyListener();
    }

    private void initializePanels() {
        setLayout(new BorderLayout()); // Use BorderLayout for easier component placement
        setBackground(new Color(240, 240, 240)); // Set background color

        // Create a panel for the card-like container
        JPanel cardPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for internal alignment
        cardPanel.setBackground(Color.WHITE); // Set background color to white
        cardPanel.setPreferredSize(new Dimension(600, 600)); // Set preferred size
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // Position on the left
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.insets = new Insets(10, 10, 10, 10); // Add consistent spacing
        add(cardPanel, BorderLayout.WEST);

        // Add components to the cardPanel
        JLabel titleLabel = new JLabel("Welcome!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        gbc.gridx--;//move left 
        gbc.gridwidth = 0; // Span across two columns
        cardPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        JButton googleButton = new JButton("Sign in with Google");
        googleButton.setPreferredSize(new Dimension(200, 30));
        googleButton.setBackground(new Color(66, 133, 244)); // Google Blue
        googleButton.setForeground(Color.WHITE);
        googleButton.setFocusPainted(false);
        cardPanel.add(googleButton, gbc);
        cardPanel.add(googleButton, gbc);

        gbc.gridx = 1;
        JButton facebookButton = new JButton("Login with Facebook");
        facebookButton.setPreferredSize(new Dimension(200, 30));
        facebookButton.setBackground(new Color(24, 119, 242)); // Facebook Blue
        facebookButton.setForeground(Color.WHITE);
        facebookButton.setFocusPainted(false);
        cardPanel.add(facebookButton, gbc);
        gbc.gridwidth = 2; // span into two columns
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        cardPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87), 2), // Green border
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        
        cardPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        cardPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 139, 87), 2), // Green border
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding
        cardPanel.add(passwordField, gbc);
        

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.setBackground(new Color(46, 139, 87)); // Green color
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
            handleLogin();
           } 
        });
        cardPanel.add(loginButton, gbc);


        gbc.gridx = 1;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100, 30));
        signUpButton.setBackground(new Color(255, 69, 0)); // Red color
        signUpButton.setForeground(Color.WHITE); // White text
        signUpButton.setFocusPainted(false); 
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

    private void addEnterKeyListener() {
        // Create an action for the Enter key
        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        };
    
        // Get the input and action maps of the panel
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
    
        // Bind the Enter key to the action
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterAction");
        actionMap.put("enterAction", enterAction);
    }
}
    
    
    
    


     

