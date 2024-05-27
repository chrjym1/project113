package homeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Home homePanel;

    public CalendarPanel(CardLayout cardLayout, JPanel cardPanel, Home homePanel) {
        this.homePanel = homePanel;
        setLayout(new BorderLayout());

        ZoneId philippinesZoneId = ZoneId.of("Asia/Manila");
        currentDate = ZonedDateTime.now(philippinesZoneId).toLocalDate();

        JPanel topPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 24));
        monthLabel.setForeground(new Color(0x2E8B57));
        prevButton = createStyledButton("<");
        nextButton = createStyledButton(">");

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        tableModel = new DefaultTableModel(null, columnNames);
        calendarTable = new JTable(tableModel);
        calendarTable.setRowSelectionAllowed(false);
        calendarTable.setCellSelectionEnabled(true);
        calendarTable.setFont(new Font("Arial", Font.PLAIN, 18));
        calendarTable.setRowHeight(40);
        calendarTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        calendarTable.getTableHeader().setBackground(new Color(0x8FBC8F));
        calendarTable.getTableHeader().setForeground(Color.WHITE);
        calendarTable.setGridColor(Color.LIGHT_GRAY);

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.rowAtPoint(e.getPoint());
                int col = calendarTable.columnAtPoint(e.getPoint());
                Object value = tableModel.getValueAt(row, col);
                if (value != null) {
                    int day = (Integer) value;
                    homePanel.displayScheduleForDate(day);
                }
            }
        });

        calendarTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cellLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cellLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                if (value != null) {
                    cellLabel.setForeground(Color.BLACK);
                    cellLabel.setBackground(Color.WHITE);
                } else {
                    cellLabel.setForeground(Color.LIGHT_GRAY);
                    cellLabel.setBackground(Color.WHITE);
                }
                if (row % 2 == 0) {
                    cellLabel.setBackground(new Color(0xF5F5F5));
                }
                if (isSelected) {
                    cellLabel.setBackground(new Color(0x87CEEB));
                    cellLabel.setForeground(Color.WHITE);
                }
                return cellLabel;
            }
        });

        dayLabel = new JLabel("", SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dayLabel.setForeground(new Color(0x2E8B57));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(dayLabel, BorderLayout.NORTH);

        JButton backButton = createStyledButton("Back");
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

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(0x2E8B57));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
}
