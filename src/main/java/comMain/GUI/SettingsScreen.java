/**

 The SettingsScreen class represents a JPanel that displays settings options for a library.
 It contains input fields for the library name and address, and a checkbox to enable or disable notifications.
 The panel also includes a save button to save the settings.
 */
package comMain.GUI;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JPanel {
    private JTextField libraryNameField, libraryAddressField;
    private JCheckBox enableNotificationsCheckbox;

    /**
     * Constructs a new SettingsScreen with the default settings.
     */
    public SettingsScreen() {
        setLayout(new BorderLayout());

        // create the input fields and labels
        libraryNameField = new JTextField();
        libraryAddressField = new JTextField();
        enableNotificationsCheckbox = new JCheckBox("Enable notifications");

        // create a panel for the input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Library name:"));
        inputPanel.add(libraryNameField);
        inputPanel.add(new JLabel("Library address:"));
        inputPanel.add(libraryAddressField);
        inputPanel.add(enableNotificationsCheckbox);

        // create a panel for the save button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Save"));

        // add the input and button panels to the screen
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

