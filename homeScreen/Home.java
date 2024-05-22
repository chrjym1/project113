package homeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


import javax.swing.table.DefaultTableModel;

public class Home extends JPanel {
    private JLabel welcomeLabel;
    private JPanel topPanel, leftPanel;
    private JLabel titleLabel;
    private JButton addSubjectBtn, removeSubjectBtn, updateSubjectBtn, resetBtn, calendarBtn;
    private JTextField subjectNameField, instructorField;
    private JComboBox<String> dayComboBox, timeComboBox;
    private ArrayList<String> subjects = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable scheduleTable;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Home() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    public void setCardLayoutAndPanel(CardLayout cardLayout, JPanel cardPanel) {
    this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
}


    private void initializeComponents() {
        // Welcome Label
        welcomeLabel = new JLabel("Welcome, Home");
        add(welcomeLabel, BorderLayout.NORTH);

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

        // Input Fields Panel in the center
     JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
     inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
     JLabel nameLabel = new JLabel("Subject:");
     nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     subjectNameField = new JTextField();
     subjectNameField.setFont(new Font("Arial", Font.PLAIN, 14));
     JLabel instructorLabel = new JLabel("Instructor:");
     instructorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     instructorField = new JTextField(); // Initialize instructorField
     instructorField.setFont(new Font("Arial", Font.PLAIN, 14));
     JLabel dayLabel = new JLabel("Day:");
     dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
     dayComboBox = new JComboBox<>(days);
     dayComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
     JLabel timeLabel = new JLabel("Time:");
     timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     String[] times = {"6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM",
             "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM",
             "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
             "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"};
     timeComboBox = new JComboBox<>(times);
     timeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

     
     inputPanel.add(nameLabel);
     inputPanel.add(subjectNameField);
     inputPanel.add(instructorLabel);
     inputPanel.add(instructorField);
     inputPanel.add(dayLabel);
     inputPanel.add(dayComboBox);
     inputPanel.add(timeLabel);
     inputPanel.add(timeComboBox);
     mainPanel.add(inputPanel, BorderLayout.CENTER);

     
     
 



        // Buttons Action Listeners
        addSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSubject();
            }
        });

        removeSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSubject();
            }
        });

        updateSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            updateSubject();
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();

            }
        });


        calendarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the calendar panel
                showCalendar();
            }
        });
    }

    private void addSubject() {
    String subject = subjectNameField.getText();
    String instructor = instructorField.getText();
    String day = (String) dayComboBox.getSelectedItem();
    String time = (String) timeComboBox.getSelectedItem();

    // Add subject to subjects list
    subjects.add(subject);

    // Add row to tableModel
    tableModel.addRow(new Object[]{subject, day, time, instructor});
        
    }

    private void removeSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
    if (selectedRow != -1) {
        subjects.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    } else {
        JOptionPane.showMessageDialog(this, "Please select a subject to remove.");
    }
    }

    private void updateSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
    if (selectedRow != -1) {
        String subject = subjectNameField.getText();
        String instructor = instructorField.getText();
        String day = (String) dayComboBox.getSelectedItem();
        String time = (String) timeComboBox.getSelectedItem();

        subjects.set(selectedRow, subject);

        // Update row in tableModel
        tableModel.setValueAt(subject, selectedRow, 0);
        tableModel.setValueAt(day, selectedRow, 1);
        tableModel.setValueAt(time, selectedRow, 2);
        tableModel.setValueAt(instructor, selectedRow, 3);
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
    

    public void displayWelcomeMessage() {
        welcomeLabel.setText("Hello, Welcome Home");
    }
}
