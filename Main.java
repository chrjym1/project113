import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import homeScreen.Home;
import Authentication.LogInSignUp;
import homeScreen.Home;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Scheduling Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 700);
 
            Home home = new Home(); 

            LogInSignUp logInSignUp = new LogInSignUp(home);
            frame.add(logInSignUp);
            
            

            frame.setVisible(true);

        });
    }
}

