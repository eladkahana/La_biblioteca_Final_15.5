/**

 The SettingsScreen class represents a JPanel that displays settings options for a library.
 It contains input fields for the library name and address, and a checkbox to enable or disable notifications.
 The panel also includes a save button to save the settings.
 */
package comMain.GUI;

import comMain.SwingClient.InformationGUI;
import comMain.SwingClient.ReservationClient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsScreen extends JPanel {
    private JTextField libraryNameField, libraryAddressField;
    private JCheckBox enableNotificationsCheckbox;

    /**
     * Constructs a new SettingsScreen with the default settings.
     */
    public SettingsScreen() {
        setLayout(new GridLayout(3, 1, 10, 10)); // Use GridLayout for the main container

        List<Object> days =  new ArrayList<>();


// Create a panel for the reminder settings
        JPanel reminderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        reminderPanel.setBorder(BorderFactory.createTitledBorder("Reminder Settings")); // Add a titled border

        JLabel daysLabel = new JLabel("Add days:");
        reminderPanel.add(daysLabel);

        JTextField daysField = new JTextField();
        daysField.setColumns(10); // Set preferred width
        reminderPanel.add(daysField);

        JComboBox<String> daysComboBox = new JComboBox<>();
        daysComboBox.setPrototypeDisplayValue("XXXXXXXX"); // Set a larger width for the combo box
        reminderPanel.add(daysComboBox);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            String day = (String) daysField.getText();
            if (day != null && !day.isEmpty()) {
                daysComboBox.addItem(day);
                days.add(day);
            }
        });
        reminderPanel.add(addButton);

        JButton removeButton = new JButton("-");
        removeButton.addActionListener(e -> {
            String chosenDay = (String) daysComboBox.getSelectedItem();
            if (chosenDay != null && !chosenDay.isEmpty()) {
                daysComboBox.removeItem(chosenDay);
                days.remove(chosenDay);
            }
        });
        reminderPanel.add(removeButton);

// Create a panel for the save button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        JButton saveButton = new JButton("save");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            ReservationClient.changeDays(days);
            JOptionPane.showMessageDialog(null, "Changes saved successfully.");
        });



// Add the panels to the screen
        add(reminderPanel);
        add(new JPanel()); // Empty panel for spacing
        add(buttonPanel);


    }
}

