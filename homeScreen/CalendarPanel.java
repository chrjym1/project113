package homeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarPanel extends JPanel {
    public CalendarPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Calendar View", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "homeScreen");
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
