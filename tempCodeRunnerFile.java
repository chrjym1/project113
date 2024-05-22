import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import homeScreen.Home;
import Authentication.LogInSignUp;
import homeScreen.CalendarPanel;
import java.awt.CardLayout;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Scheduling Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 700);
 
             // Create a CardLayout and a JPanel to hold the cards
             CardLayout cardLayout = new CardLayout();
             JPanel cardPanel2 = new JPanel(cardLayout);

            Home home = new Home(); 
             home.setCardLayoutAndPanel(cardLayout, cardPanel2);

            LogInSignUp logInSignUp = new LogInSignUp(home);
            JPanel cardPanel = new JPanel(new CardLayout());
            CalendarPanel calendarPanel = new CalendarPanel();
            cardPanel.add(logInSignUp, "login"); //  login screen with name "login"
            cardPanel.add(home, "homeScreen"); //  home screen with name "homeScreen"
            cardPanel.add(calendarPanel, "calendar");
            frame.add(cardPanel);
            
            

            frame.setVisible(true);

        });
    }
}

