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
    private JButton addSubjectBtn, removeSubjectBtn, updateSubjectBtn, resetBtn, deleteSubjectBtn;
    private JTextField subjectNameField;
    private JComboBox<String> dayComboBox, timeComboBox;
    private ArrayList<String> subjects = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable scheduleTable;

    public Home() {
        setLayout(new BorderLayout());
        initializeComponents();
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

        // Left Panel within Main Panel
        leftPanel = new JPanel(new GridLayout(5, 1));
        addSubjectBtn = new JButton("Add Subject");
        removeSubjectBtn = new JButton("Remove Subject");
        updateSubjectBtn = new JButton("Update Subject");
        resetBtn = new JButton("Reset Form");
        deleteSubjectBtn = new JButton("Delete Subject");
        leftPanel.add(addSubjectBtn);
        leftPanel.add(removeSubjectBtn);
        leftPanel.add(updateSubjectBtn);
        leftPanel.add(resetBtn);
        leftPanel.add(deleteSubjectBtn);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Right Panel within Main Panel
        String[] columnNames = {"Subject", "Day", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

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

        deleteSubjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSubject();
            }
        });
    }

    private void addSubject() {
        JFrame addSubjectFrame = new JFrame("Add Subject");
        addSubjectFrame.setSize(400, 200);
        addSubjectFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Subject Name:");
        subjectNameField = new JTextField();
        JLabel dayLabel = new JLabel("Day:");
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        JLabel timeLabel = new JLabel("Time:");
        String[] times = {"6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM",
                "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM",
                "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
                "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"};
        timeComboBox = new JComboBox<>(times);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String subjectName = subjectNameField.getText();
                String day = (String) dayComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                subjects.add(subjectName + " - " + day + " " + time);
                tableModel.addRow(new Object[]{subjectName, day, time});
                addSubjectFrame.dispose();
            }
        });

        addSubjectFrame.add(nameLabel);
        addSubjectFrame.add(subjectNameField);
        addSubjectFrame.add(dayLabel);
        addSubjectFrame.add(dayComboBox);
        addSubjectFrame.add(timeLabel);
        addSubjectFrame.add(timeComboBox);
        addSubjectFrame.add(addBtn);
        addSubjectFrame.setVisible(true);
    }

    private void removeSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject to remove.");
            return;
        }

        subjects.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    }

    private void updateSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject to update.");
            return;
        }

        String currentSubject = (String) tableModel.getValueAt(selectedRow, 0);
        String currentDay = (String) tableModel.getValueAt(selectedRow, 1);
        String currentTime = (String) tableModel.getValueAt(selectedRow, 2);

        JFrame updateSubjectFrame = new JFrame("Update Subject");
        updateSubjectFrame.setSize(400, 200);
        updateSubjectFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Subject Name:");
        subjectNameField = new JTextField(currentSubject);
        JLabel dayLabel = new JLabel("Day:");
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setSelectedItem(currentDay);
        JLabel timeLabel = new JLabel("Time:");
        String[] times = {"6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM",
                "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM",
                "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
                "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM"};
        timeComboBox = new JComboBox<>(times);
        timeComboBox.setSelectedItem(currentTime);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String updatedSubject = subjectNameField.getText();
                String updatedDay = (String) dayComboBox.getSelectedItem();
                String updatedTime = (String) timeComboBox.getSelectedItem();
                subjects.set(selectedRow, updatedSubject + " - " + updatedDay + " " + updatedTime);
                tableModel.setValueAt(updatedSubject, selectedRow, 0);
                tableModel.setValueAt(updatedDay, selectedRow, 1);
                tableModel.setValueAt(updatedTime, selectedRow, 2);
                updateSubjectFrame.dispose();
            }
        });

        updateSubjectFrame.add(nameLabel);
        updateSubjectFrame.add(subjectNameField);
        updateSubjectFrame.add(dayLabel);
        updateSubjectFrame.add(dayComboBox);
        updateSubjectFrame.add(timeLabel);
        updateSubjectFrame.add(timeComboBox);
        updateSubjectFrame.add(updateBtn);
        updateSubjectFrame.setVisible(true);
    }

    private void resetForm() {
        subjectNameField.setText("");
        dayComboBox.setSelectedIndex(0);
        timeComboBox.setSelectedIndex(0);
    }

    private void deleteSubject() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject to delete.");
            return;
        }

        subjects.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    }

    public void displayWelcomeMessage() {
        welcomeLabel.setText("Hello, Welcome Home");
    }
}
