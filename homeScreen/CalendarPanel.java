package homeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CalendarPanel extends JPanel {
    private JLabel monthLabel;
    private JLabel dayLabel;
    private JButton prevButton, nextButton;
    private JTable calendarTable;
    private DefaultTableModel tableModel;
    private LocalDate currentDate;

    public CalendarPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout());

        // Get the current date in the Philippines time zone
        ZoneId philippinesZoneId = ZoneId.of("Asia/Manila");
        currentDate = ZonedDateTime.now(philippinesZoneId).toLocalDate();

        JPanel topPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 24));
        prevButton = new JButton("<");
        nextButton = new JButton(">");

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        tableModel = new DefaultTableModel(null, columnNames);
        calendarTable = new JTable(tableModel);

        dayLabel = new JLabel("", SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(dayLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "homeScreen");
            }
        });
        bottomPanel.add(backButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(calendarTable), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
        });

        updateCalendar();
    }

    private void updateCalendar() {
        monthLabel.setText(currentDate.getMonth().toString() + " " + currentDate.getYear());
        dayLabel.setText("Today is: " + currentDate.getDayOfWeek().toString() + ", " + currentDate.getDayOfMonth());

        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int startDay = firstOfMonth.getDayOfWeek().getValue() % 7;
        int daysInMonth = yearMonth.lengthOfMonth();

        tableModel.setRowCount(0);
        tableModel.setRowCount(6);

        int row = 0;
        for (int day = 1; day <= daysInMonth; day++) {
            int column = (startDay + day - 1) % 7;
            if (column == 0 && day != 1) {
                row++;
            }
            tableModel.setValueAt(day, row, column);
        }
    }
}
