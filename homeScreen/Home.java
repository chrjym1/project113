package homeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Home extends JPanel {
    private JPanel topPanel, leftPanel;
    private JLabel titleLabel;
    private JButton addSubjectBtn, removeSubjectBtn, updateSubjectBtn, resetBtn, calendarBtn;
    private JTextField subjectNameField, instructorField;
    private JComboBox<String> dayComboBox, timeComboBox;
    private ArrayList<String> subjects = new ArrayList<>();
    private JComboBox<Integer> dateComboBox;
    private DefaultTableModel tableModel;
    private JTable scheduleTable;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private ArrayList<ScheduleEntry> schedules = new ArrayList<>();
    // ...
    // ScheduleEntry inner class to hold schedule details
    class ScheduleEntry {
        String subject;
        String instructor;
        String day;
        String time;
        int date;
        
        ScheduleEntry(String subject, String instructor, String day, String time, int date) {
            this.subject = subject;
            this.instructor = instructor;
            this.day = day;
            this.time = time;
            this.date = date;
        }
    }

    public Home() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    public void setCardLayoutAndPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    private void initializeComponents() {

        // Main Panel for schedule management components
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Top Panel within Main Panel
        topPanel = new JPanel();
        titleLabel = new JLabel("Schedule Management");
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Bottom Panel within Main Panel
        leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addSubjectBtn = new JButton("Add Subject");
        removeSubjectBtn = new JButton("Remove Subject");
        updateSubjectBtn = new JButton("Update Subject");
        resetBtn = new JButton("Reset Form");
        calendarBtn = new JButton("View Calendar");
        leftPanel.add(addSubjectBtn);
        leftPanel.add(removeSubjectBtn);
        leftPanel.add(updateSubjectBtn);
        leftPanel.add(resetBtn);
        leftPanel.add(calendarBtn);
        mainPanel.add(leftPanel, BorderLayout.SOUTH);

        // Table Panel at the top
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Subject", "Day", "Time", "Instructor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.NORTH);

        //styling for the JTable 
        scheduleTable.setRowHeight(15);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < scheduleTable.getColumnCount(); i++) {
        scheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    
        // Set custom header renderer
        JTableHeader header = scheduleTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(0, 120, 215));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 20));
    
        // Set grid color and selection colors
        scheduleTable.setGridColor(Color.LIGHT_GRAY);
        scheduleTable.setSelectionBackground(new Color(184, 207, 229));
        scheduleTable.setSelectionForeground(Color.BLACK);

        // Input Fields Panel in the center
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        inputPanel.setBackground(new Color(240, 240, 240)); // Set background color
        JLabel nameLabel = new JLabel("Subject:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subjectNameField = new JTextField();
        subjectNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        subjectNameField.setToolTipText("Enter the subject name");

        JLabel instructorLabel = new JLabel("Instructor:");
        instructorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructorField = new JTextField();
        instructorField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        instructorField.setToolTipText("Enter the instructor name");

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setToolTipText("Select the day of the week");

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] times = {"6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM",
                "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM",
                "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
                "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"};
        timeComboBox = new JComboBox<>(times);
        timeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel dateLabel = new JLabel("Date:");
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

    // Get the current day of the week in full text
    LocalDate currentDate = LocalDate.now();
    DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();
    String currentDayName = currentDayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

    // Get the current date
    int currentDayOfMonth = currentDate.getDayOfMonth();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();

    // Get the number of days in the current month
    java.time.YearMonth currentYearMonth = java.time.YearMonth.of(currentYear, currentMonth);
    int daysInMonth = currentYearMonth.lengthOfMonth();

    // Initialize JComboBox with numbers from 1 to the number of days in the current month
    Integer[] daysOfMonth = new Integer[daysInMonth];
    for (int i = 0; i < daysInMonth; i++) {
        daysOfMonth[i] = i + 1;
    }

    // Initialize dateComboBox with current date as default selected date
    dateComboBox = new JComboBox<>(daysOfMonth);
    dateComboBox.setSelectedItem(currentDayOfMonth);
    dateComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

    dayComboBox.setSelectedItem(currentDayName);
    inputPanel.add(nameLabel);
    inputPanel.add(subjectNameField);
    inputPanel.add(instructorLabel);
    inputPanel.add(instructorField);
    inputPanel.add(dayLabel);
    inputPanel.add(dayComboBox);
    inputPanel.add(timeLabel);
    inputPanel.add(timeComboBox);
    inputPanel.add(dateLabel);
    inputPanel.add(dateComboBox);
    mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Buttons Action Listeners
        addSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSubject();
            }
        });

        //button styling 
        addSubjectBtn.setBackground(new Color(0, 120, 215));
        addSubjectBtn.setForeground(Color.WHITE);
        addSubjectBtn.setFocusPainted(false);
        addSubjectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addSubjectBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        removeSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSubject();
            }
        });

        removeSubjectBtn.setBackground(new Color(0, 120, 215));
        removeSubjectBtn.setForeground(Color.WHITE);
        removeSubjectBtn.setFocusPainted(false);
        removeSubjectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        removeSubjectBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        updateSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    updateSubject();
                }
            }
        });

        updateSubjectBtn.setBackground(new Color(0, 120, 215));
        updateSubjectBtn.setForeground(Color.WHITE);
        updateSubjectBtn.setFocusPainted(false);
        updateSubjectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        updateSubjectBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        resetBtn.setBackground(new Color(0, 120, 215));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        resetBtn.setFont(new Font("Arial", Font.BOLD, 14));
        resetBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        calendarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the calendar panel
                showCalendar();
            }
        });

        calendarBtn.setBackground(new Color(0, 120, 215));
        calendarBtn.setForeground(Color.WHITE);
        calendarBtn.setFocusPainted(false);
        calendarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        calendarBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
    }

    private void addSubject() {
        if (validateInputs()) {
            String subject = subjectNameField.getText();
            String instructor = instructorField.getText();
            String day = (String) dayComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            int date = (int) dateComboBox.getSelectedItem();
if (subjects.contains(subject)) {
    // Check for conflicting schedule for the same subject on the same day, time, and date
    for (ScheduleEntry entry : schedules) {
        if (entry.subject.equals(subject) && entry.day.equals(day) && entry.time.equals(time) && entry.date == date) {
            JOptionPane.showMessageDialog(this, "There is a conflicting schedule for the same subject at the same time on " + day + ". Please select a different time.");
            return;
        }
    }

    // Check if there's already a schedule for the same subject on the same date and day, but not the same time
    boolean scheduleExists = false;
    for (ScheduleEntry entry : schedules) {
        if (entry.subject.equals(subject) && entry.day.equals(day) && entry.date == date && !entry.time.equals(time)) {
            scheduleExists = true;
            break;
        }
    }
    if (scheduleExists) {
        JOptionPane.showMessageDialog(this, "There is already a schedule for the same subject on " + day + " " + date + " but at a different time. Adding new schedule.");
        // Allowing the addition to proceed in this case
    }

    // Check if the new schedule does not match the days of the week and the date
    boolean mismatchExists = false;
    for (ScheduleEntry entry : schedules) {
        if (entry.subject.equals(subject) && entry.date == date && (!entry.day.equals(day) || entry.time.equals(time))) {
            mismatchExists = true;
            break;
        }
    }
    if (mismatchExists) {
        JOptionPane.showMessageDialog(this, "The new schedule does not match the days of the week or the date of the existing schedules for the same subject.");
        return;
    }
}

// Add subject to the subjects set if it does not already exist
if (!subjects.contains(subject)) {
    subjects.add(subject);
}

// Add ScheduleEntry to schedules list
ScheduleEntry scheduleEntry = new ScheduleEntry(subject, instructor, day, time, date);
schedules.add(scheduleEntry);

// Add row to tableModel
tableModel.addRow(new Object[]{subject, day, time, instructor, date});
    }
}

private void removeSubject() {
    int selectedRow = scheduleTable.getSelectedRow();
    if (selectedRow != -1) {
        // Remove the subject from both lists
        String subjectToRemove = (String) tableModel.getValueAt(selectedRow, 0);
        subjects.remove(subjectToRemove);
        
        // Remove all occurrences of the subject from the schedules list
        schedules.removeIf(entry -> entry.subject.equals(subjectToRemove));

        // Remove the row from the table model
        tableModel.removeRow(selectedRow);
    } else {
        JOptionPane.showMessageDialog(this, "Please select a subject to remove.");
    }
}



    private void updateSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the selected ScheduleEntry
            ScheduleEntry selectedEntry = schedules.get(selectedRow);
    
            // Options for the user to select which fields to update
            String[] options = {"Subject", "Instructor", "Day", "Time", "Date"};
            String selectedOption = (String) JOptionPane.showInputDialog(this,
                    "Select the field to update:", "Update Subject",
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    
            // Process based on the selected option
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Subject":
                        String newSubject = JOptionPane.showInputDialog(this, "Enter new subject:");
                        if (newSubject != null && !newSubject.isEmpty()) {
                            selectedEntry.subject = newSubject;
                            tableModel.setValueAt(newSubject, selectedRow, 0);
                            JOptionPane.showMessageDialog(this, "Subject updated successfully.");
                        }
                        break;
                    case "Instructor":
                        String newInstructor = JOptionPane.showInputDialog(this, "Enter new instructor:");
                        if (newInstructor != null && !newInstructor.isEmpty()) {
                            selectedEntry.instructor = newInstructor;
                            tableModel.setValueAt(newInstructor, selectedRow, 3);
                            JOptionPane.showMessageDialog(this, "Instructor updated successfully.");
                        }
                        break;
                    case "Day":
                        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                        String newDay = (String) JOptionPane.showInputDialog(this,
                                "Select new day:", "Update Day",
                                JOptionPane.PLAIN_MESSAGE, null, days, days[0]);
                        if (newDay != null) {
                            selectedEntry.day = newDay;
                            tableModel.setValueAt(newDay, selectedRow, 1);
                            JOptionPane.showMessageDialog(this, "Day updated successfully.");
                        }
                        break;
                    case "Time":
                        String[] times = {"6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM",
                                "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM",
                                "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
                                "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"};
                        String newTime = (String) JOptionPane.showInputDialog(this,
                                "Select new time:", "Update Time",
                                JOptionPane.PLAIN_MESSAGE, null, times, times[0]);
                        if (newTime != null) {
                            selectedEntry.time = newTime;
                            tableModel.setValueAt(newTime, selectedRow, 2);
                            JOptionPane.showMessageDialog(this, "Time updated successfully.");
                        }
                        break;
                    case "Date":
                        Integer[] daysOfMonth = new Integer[31];
                        for (int i = 0; i < 31; i++) {
                            daysOfMonth[i] = i + 1;
                        }
                        Integer newDate = (Integer) JOptionPane.showInputDialog(this,
                                "Select new date:", "Update Date",
                                JOptionPane.PLAIN_MESSAGE, null, daysOfMonth, selectedEntry.date);
                        if (newDate != null) {
                            selectedEntry.date = newDate;
                            tableModel.setValueAt(newDate, selectedRow, 4);
                            JOptionPane.showMessageDialog(this, "Date updated successfully.");
                        }
                        break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a subject to update.");
        }
    }
    
    private void resetForm() {
        subjectNameField.setText("");
        instructorField.setText("");
        dayComboBox.setSelectedIndex(0);
        timeComboBox.setSelectedIndex(0);
    }

    private void showCalendar() {
        cardLayout.show(cardPanel, "calendar");
    }

    private boolean validateInputs() {
        String subject = subjectNameField.getText().trim();
        String instructor = instructorField.getText().trim();
        
        
        if (subject.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject name cannot be empty.");
            return false;
        }

        if (instructor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Instructor name cannot be empty.");
            return false;
        }

        if (dayComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a day.");
            return false;
        }

        if (timeComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a time.");
            return false;
        }

        if (dateComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a date.");
            return false;
        }

        return true;
    }
    
    
  // Method to fetch schedule entries for a specific date
  public ArrayList<ScheduleEntry> getScheduleForDate(int date) {
    ArrayList<ScheduleEntry> scheduleForDate = new ArrayList<>();
    for (ScheduleEntry entry : schedules) {
        if (entry.date == date) {
            scheduleForDate.add(entry);
        }
    }
    return scheduleForDate;
}

// Method to display schedule for a specific date
public void displayScheduleForDate(int date) {
    ArrayList<ScheduleEntry> scheduleForDate = getScheduleForDate(date);
    StringBuilder scheduleDetails = new StringBuilder();
    for (ScheduleEntry entry : scheduleForDate) {
        scheduleDetails.append("Subject: ").append(entry.subject).append("\n");
        scheduleDetails.append("Instructor: ").append(entry.instructor).append("\n");
        scheduleDetails.append("Day: ").append(entry.day).append("\n");
        scheduleDetails.append("Time: ").append(entry.time).append("\n\n");
    }

    if (scheduleDetails.length() == 0) {
        scheduleDetails.append("No schedule for this date.");
    }

    JOptionPane.showMessageDialog(this, scheduleDetails.toString(), "Schedule for Date: " + date, JOptionPane.INFORMATION_MESSAGE);
}
}