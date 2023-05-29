/**

 The ReadersManagementScreen class represents the graphical user interface for managing readers.
 It extends the JPanel class.
 */

package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comMain.SwingClient.InformationGUI;
import comMain.SwingClient.ReaderManagerClient;
import comMain.entities.ReadersEntity;


public class ReadersManagementScreen extends JPanel {
    private JLabel titleLabel;
    private JList<ReadersEntity> readerList;
    private JButton editReaderButton;
    private JButton addReaderButton;
    private JButton changePasswordButton;
    private JTextField searchField;
    private JButton searchButton;

    DefaultListModel<ReadersEntity> filteredListModel;


    /**
     * Constructs a new ReadersManagementScreen object.
     */
    public ReadersManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Readers Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        readerList = new JList<ReadersEntity>();
        editReaderButton = new JButton("Edit");
        addReaderButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        changePasswordButton = new JButton("change password");

        // Set reader list model
        DefaultListModel<ReadersEntity> readerListModel =InformationGUI.getAllReaders();
        readerList.setModel(readerListModel);


        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        JScrollPane readerListScrollPane = new JScrollPane(readerList);
        add(readerListScrollPane, BorderLayout.CENTER);

        JPanel readerListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        readerListButtonPanel.add(changePasswordButton);
        readerListButtonPanel.add(addReaderButton);
        readerListButtonPanel.add(editReaderButton);
        readerListButtonPanel.add(searchPanel);
        add(readerListButtonPanel, BorderLayout.SOUTH);


        editReaderButton.addActionListener(new ActionListener() {
            /**
             * Called when the Edit button is clicked. Opens the AddEditBookScreen.
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditBookScreen when the Edit button is clicked
                AddEditUserScreen addEditUserScreen = new AddEditUserScreen();
                addEditUserScreen.setPreferredSize(new Dimension(1200, 600));
                if(filteredListModel != null) {
                    addEditUserScreen.Edit(filteredListModel.getElementAt(readerList.getSelectedIndex()));
                }
                else{
                    addEditUserScreen.Edit(readerListModel.getElementAt(readerList.getSelectedIndex()));
                }
                JFrame newFrame = new JFrame();
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setContentPane(addEditUserScreen);
                newFrame.pack();
                newFrame.setVisible(true);




            }
        });


        // Add action listeners
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Change Password");

                // Create a panel for the components
                JPanel contentPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Create the components
                JLabel newPasswordLabel = new JLabel("New Password:");
                JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

                JPasswordField newPasswordField = new JPasswordField(20);
                JPasswordField confirmPasswordField = new JPasswordField(20);

                JButton changeButton = new JButton("Change");
                JButton cancelButton = new JButton("Cancel");

                // Set the layout



                gbc.gridx = 0;
                gbc.gridy = 1;
                contentPanel.add(newPasswordLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                contentPanel.add(newPasswordField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                contentPanel.add(confirmPasswordLabel, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                contentPanel.add(confirmPasswordField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                contentPanel.add(changeButton, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                contentPanel.add(cancelButton, gbc);

                // Add action listeners
                changeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String newPassword = new String(newPasswordField.getPassword());
                        String confirmPassword = new String(confirmPasswordField.getPassword());

                        // Validate the passwords
                        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (!newPassword.equals(confirmPassword)) {
                            JOptionPane.showMessageDialog(dialog, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if(filteredListModel != null) {
                                ReadersEntity chosen = filteredListModel.getElementAt(readerList.getSelectedIndex());
                                ReaderManagerClient.changePassword(chosen.getId(),newPassword);
                            }
                            else{
                                ReadersEntity chosen = readerListModel.getElementAt(readerList.getSelectedIndex());
                                ReaderManagerClient.changePassword(chosen.getId(),newPassword);
                            }

                            // Reset the fields
                            newPasswordField.setText("");
                            confirmPasswordField.setText("");

                            JOptionPane.showMessageDialog(dialog, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                            dialog.dispose();
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                dialog.add(contentPanel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });



        addReaderButton.addActionListener(new ActionListener() {
            /**
             * Creates a new instance of the AddEditReaderScreen when the Add button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditReaderScreen when the Add button is clicked
                AddEditUserScreen addEditUserScreen = new AddEditUserScreen();
                addEditUserScreen.setPreferredSize(new Dimension(800, 600));
                JFrame newFrame = new JFrame();
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setContentPane(addEditUserScreen);
                newFrame.pack();
                newFrame.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            /**
             * Filters the reader list based on the search query.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                filteredListModel = new DefaultListModel<ReadersEntity>();
                for (int i = 0; i < readerListModel.getSize(); i++) {
                    ReadersEntity reader = readerListModel.getElementAt(i);
                    if (InformationGUI.getName(reader.getFirstName(),reader.getLastName()).contains(query)) {
                        filteredListModel.addElement(reader);
                    }
                }
                readerList.setModel(filteredListModel);
            }
        });


        readerList.setCellRenderer(new DefaultListCellRenderer() {
            /**
             * display the id on the list and align to the right
             */
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ReadersEntity reader = (ReadersEntity) value;
                renderer.setText(InformationGUI.getName(reader.getFirstName(),reader.getLastName()));
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });
    }
}
