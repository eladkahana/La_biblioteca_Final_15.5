/**

 The AddEditUserScreen class creates a JPanel for adding and editing user information.
 */
package comMain.GUI;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JDateChooser;
import comMain.SwingClient.AddEditReaderClient;
import comMain.SwingClient.InformationGUI;
import comMain.entities.ReadersEntity;

public class AddEditUserScreen extends JPanel {
    private final JPanel buttonPanel;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel birthDateLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel phoneNoLabel;
    private JLabel emailLabel;
    private JTextField idTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JDateChooser birthDateChooser;
    private JComboBox<String> genderComboBox;
    private JTextField addressTextField;
    private JTextField phoneNoTextField;
    private JTextField emailTextField;
    private JButton saveButton;
    private JButton cancelButton;

    /**
     * Constructs an AddEditUserScreen object with a BorderLayout layout.
     * Initializes the title label, form fields, and buttons.
     */
    public AddEditUserScreen() {
        setLayout(new BorderLayout());

        // Create the title label
        titleLabel = new JLabel("Add User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Create the form fields
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        idLabel = new JLabel("ID:");
        idTextField = new JTextField();
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField();
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField();
        birthDateLabel = new JLabel("Birth Date:");
        birthDateChooser = new JDateChooser();
        birthDateChooser.setDateFormatString("dd/MM/yyyy"); // Set the date format
        genderLabel = new JLabel("Gender:");
        String[] genders = {"זכר", "נקבה"};
        genderComboBox = new JComboBox<>(genders);
        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        phoneNoLabel = new JLabel("Phone Number:");
        phoneNoTextField = new JTextField();
        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idTextField);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameTextField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameTextField);
        formPanel.add(birthDateLabel);
        formPanel.add(birthDateChooser);
        formPanel.add(genderLabel);
        formPanel.add(genderComboBox);
        formPanel.add(addressLabel);
        formPanel.add(addressTextField);
        formPanel.add(phoneNoLabel);
        formPanel.add(phoneNoTextField);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);
        add(formPanel, BorderLayout.CENTER);



        // Create the buttons
         buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(e -> {


            // Convert java.util.Date to java.sql.Date
            long milliseconds = birthDateChooser.getDate().getTime();
            java.sql.Date sqlDate = new java.sql.Date(milliseconds);

            AddEditReaderClient.addReader(idTextField.getText(),addressTextField.getText(),phoneNoTextField.getText(),
                    firstNameTextField.getText(), lastNameTextField.getText()
                    , sqlDate,genderComboBox.getItemAt(genderComboBox.getSelectedIndex()),emailTextField.getText());

            closePanel();
        });
        cancelButton.addActionListener(e -> {
            closePanel();
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Closes the panel by removing it from its parent container.
     */
    private void closePanel() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }



    public void Edit(ReadersEntity reader) {
        this.idTextField.setText(reader.getIDno());
        String name = InformationGUI.getName(reader.getFirstName(),reader.getLastName());
        this.firstNameTextField.setText(name.substring(0, name.indexOf(',')));
        this.lastNameTextField.setText(name.substring(name.indexOf(',') + 2));
        this.birthDateChooser.setDate(reader.getBirthDate());
        this.genderComboBox.setSelectedItem(InformationGUI.getGenderByID(reader.getGenderId()));
        this.addressTextField.setText(reader.getAdress());
        this.phoneNoTextField.setText(reader.getPhoneNo());
        this.emailTextField.setText(reader.getEmail());


        saveButton.setVisible(false);
        JButton updatebutton = new JButton("update");
        this.buttonPanel.add(updatebutton);
        updatebutton.addActionListener(new ActionListener() {

            long milliseconds = birthDateChooser.getDate().getTime();
            java.sql.Date sqlDate = new java.sql.Date(milliseconds);

            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditReaderClient.EditRedaer(idTextField.getText(), addressTextField.getText(),
                        phoneNoTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),
                        sqlDate, genderComboBox.getItemAt(genderComboBox.getSelectedIndex()),
                        emailTextField.getText(),reader.getId());

            }
        });

    }
}
