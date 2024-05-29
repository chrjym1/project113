import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import homeScreen.Home;
import Authentication.LogInSignUp;
import java.awt.CardLayout;
import homeScreen.CalendarPanel;

public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure the GUI creation is done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create a new JFrame instance with a title
            JFrame frame = new JFrame("Student Scheduling Management System");
            // Set the default close operation to exit on close
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the size of the JFrame
            frame.setSize(1280, 700);
 
            // Create an instance of the Home class (main scheduling functionalities)
            Home home = new Home(); 

            // Create an instance of the LogInSignUp class and pass the home instance (user authentication)
            LogInSignUp logInSignUp = new LogInSignUp(home);
            // Create a JPanel with CardLayout to manage different screens
            JPanel cardPanel = new JPanel(new CardLayout());
            // Add the login screen to the cardPanel with the name "login"
            cardPanel.add(logInSignUp, "login");
            // Add the home screen to the cardPanel with the name "homeScreen"
            cardPanel.add(home, "homeScreen");
            
            // Create an instance of CalendarPanel, passing the CardLayout, cardPanel, and home instances (calendar functionalities)
            CalendarPanel calendarPanel = new CalendarPanel((CardLayout) cardPanel.getLayout(), cardPanel, home);
            // Add the calendar panel to the cardPanel with the name "calendar"
            cardPanel.add(calendarPanel, "calendar");

            // Set the CardLayout and cardPanel to the home instance for navigation control
            home.setCardLayoutAndPanel((CardLayout) cardPanel.getLayout(), cardPanel);
            
            // Add the cardPanel to the JFrame
            frame.add(cardPanel);
            
            // Set the JFrame to be visible
            frame.setVisible(true);
        });
    }
}
