import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import homeScreen.Home;
import Authentication.LogInSignUp;
import java.awt.CardLayout;
import homeScreen.CalendarPanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Scheduling Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 700);
 
            Home home = new Home(); 

            LogInSignUp logInSignUp = new LogInSignUp(home);
            JPanel cardPanel = new JPanel(new CardLayout());
            cardPanel.add(logInSignUp, "login"); // login screen with name "login"
            cardPanel.add(home, "homeScreen"); // home screen with name "homeScreen"
            
            // Add CalendarPanel
            CalendarPanel calendarPanel = new CalendarPanel((CardLayout) cardPanel.getLayout(), cardPanel, home);
            cardPanel.add(calendarPanel, "calendar"); // calendar panel with name "calendar"

            home.setCardLayoutAndPanel((CardLayout) cardPanel.getLayout(), cardPanel);
            
            frame.add(cardPanel);
            
            frame.setVisible(true);
        });
    }
}
