/**

 The MailBoxScreen class represents a GUI panel that displays the user's mailbox. It includes an inbox list, a read messages list, a message area to display the content of the selected message, and a respond button to respond to the selected message.
 */

package comMain.GUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import comMain.SwingClient.InformationGUI;
import comMain.entities.RequestsEntity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MailBoxScreen extends JPanel {

    private final ArrayList<String> inboxMessages;
    private final ArrayList<String> inboxArray;
    private final ArrayList<String> readMessages;
    private final ArrayList<String> readArray;
    private JList<String> inboxList;
    private JList<String> readList;
    private JTextArea messageArea;
    private JButton respondButton;
    private JButton refreshButton;

    private List<RequestsEntity> unCheckedList, CheckedList;

    /**
     * Constructs a MailBoxScreen object.
     */
    public MailBoxScreen() throws JsonProcessingException {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Mail Box");
        topPanel.add(titleLabel);


        refreshButton = new JButton("refresh");
        refreshButton.setPreferredSize(new Dimension(100, 30));


        topPanel.add(refreshButton);


        add(topPanel, BorderLayout.NORTH);


        inboxMessages = new ArrayList<>();
        inboxArray = new ArrayList<>();
        unCheckedList = InformationGUI.getUnCheckedRequests();

        for (RequestsEntity request : unCheckedList) {
            // Create sample inbox messages and content
            inboxMessages.add(request.getContactContent());
            inboxArray.add(InformationGUI.getMailDetails(request));
        }

// Create list of inbox messages
        DefaultListModel<String> inboxListModel = new DefaultListModel<>();
        for (String message : inboxArray) {
            inboxListModel.addElement(message);
        }

        inboxList = new JList<>(inboxListModel);


        inboxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inboxList.setFixedCellHeight(50); // Increase height of each cell in the list
        JScrollPane inboxScrollPane = new JScrollPane(inboxList);
        inboxScrollPane.setPreferredSize(new Dimension(200, 0)); // Set width of inbox list
        JLabel inboxTitle = new JLabel("Inbox");
        inboxTitle.setHorizontalAlignment(JLabel.CENTER);
        JPanel inboxPanel = new JPanel(new BorderLayout());
        inboxPanel.add(inboxTitle, BorderLayout.NORTH);
        inboxPanel.add(inboxScrollPane, BorderLayout.CENTER);
        add(inboxPanel, BorderLayout.WEST);


        readMessages = new ArrayList<>();
        readArray = new ArrayList<>();
        CheckedList = InformationGUI.getCheckedRequests();

        for (RequestsEntity request : CheckedList) {
            // Create sample inbox messages and content
            readMessages.add(request.getContactContent());
            readArray.add(InformationGUI.getMailDetails(request));
        }

// Create list of inbox messages
        DefaultListModel<String> readListModel = new DefaultListModel<>();
        for (String message : readArray) {
            readListModel.addElement(message);
        }

        readList = new JList<>(readListModel);


        readList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        readList.setFixedCellHeight(50); // Increase height of each cell in the list
        JScrollPane readScrollPane = new JScrollPane(readList);
        readScrollPane.setPreferredSize(new Dimension(200, 0)); // Set width of read list
        JLabel readTitle = new JLabel("Read Messages");
        readTitle.setHorizontalAlignment(JLabel.CENTER);
        JPanel readPanel = new JPanel(new BorderLayout());
        readPanel.add(readTitle, BorderLayout.NORTH);
        readPanel.add(readScrollPane, BorderLayout.CENTER);
        add(readPanel, BorderLayout.EAST);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        add(messageArea, BorderLayout.CENTER);


        respondButton = new JButton("Respond");
        respondButton.setEnabled(false);
        respondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RequestsEntity request;
                // Create response window
                if (inboxList.getSelectedIndex() > readList.getSelectedIndex()) {
                    request = unCheckedList.get(inboxList.getSelectedIndex());
                } else {
                    request = CheckedList.get(readList.getSelectedIndex());
                }
                RespondingWindow respondWindow = new RespondingWindow(request);
                respondWindow.setVisible(true);
            }
        });
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageArea, BorderLayout.CENTER);
        messagePanel.add(respondButton, BorderLayout.SOUTH);
        add(messagePanel, BorderLayout.CENTER);


        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshMailbox();

            }
        });


        // Add list selection listener to show message content and enable respond button
        inboxList.addListSelectionListener(new ListSelectionListener() {
            /**
             * display the message in the screen. in let the user to response
             *
             * @param e the event that characterizes the change.
             */
            public void valueChanged(ListSelectionEvent e) {
                int index = inboxList.getSelectedIndex();
                if (index >= 0) {
                    InformationGUI.requestChecked(unCheckedList.get(index).getId());
                    messageArea.setText(inboxMessages.get(index));
                    messageArea.setFont(new Font("Serif", Font.PLAIN, 24)); // Increase font size of message area
                    respondButton.setVisible(true); // Show respond button
                    respondButton.setEnabled(true);
                } else {
                    messageArea.setText("");
                    messageArea.setFont(messageArea.getFont().deriveFont(Font.PLAIN));
                    respondButton.setVisible(false); // Hide respond button
                }
            }
        });

        readList.addListSelectionListener(new ListSelectionListener() {
            /**
             * display the message in the screen. in let the user to response
             *
             * @param e the event that characterizes the change.
             */
            public void valueChanged(ListSelectionEvent e) {
                int index = readList.getSelectedIndex();
                if (index >= 0) {
                    messageArea.setText(readMessages.get(index));
                    messageArea.setFont(new Font("Serif", Font.PLAIN, 24));
                    messageArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    respondButton.setVisible(true); // Show respond button
                    respondButton.setEnabled(true);
                } else {
                    messageArea.setText("");
                    messageArea.setFont(messageArea.getFont().deriveFont(Font.PLAIN));
                    respondButton.setVisible(false); // Hide respond button
                }
            }
        });


    }

    private void refreshMailbox() {
        try {
            // Clear lists
            DefaultListModel<String> inboxListModel = (DefaultListModel<String>) inboxList.getModel();
            inboxListModel.clear();
            inboxArray.clear();
            inboxMessages.clear();

            DefaultListModel<String> readListModel = (DefaultListModel<String>) readList.getModel();
            readListModel.clear();
            readArray.clear();
            readMessages.clear();

            // Reload mailbox information
            unCheckedList = InformationGUI.getUnCheckedRequests();
            for (RequestsEntity request : unCheckedList) {
                // Create sample inbox messages and content
                inboxMessages.add(request.getContactContent());
                inboxArray.add(InformationGUI.getMailDetails(request));
            }
            for (String message : inboxArray) {
                inboxListModel.addElement(message);
            }

            CheckedList = InformationGUI.getCheckedRequests();
            for (RequestsEntity request : CheckedList) {
                // Create sample inbox messages and content
                readMessages.add(request.getContactContent());
                readArray.add(InformationGUI.getMailDetails(request));
            }
            for (String message : readArray) {
                readListModel.addElement(message);
            }

            // Clear message area and disable respond button
            messageArea.setText("");
            messageArea.setFont(messageArea.getFont().deriveFont(Font.PLAIN));
            respondButton.setEnabled(false);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }




}

