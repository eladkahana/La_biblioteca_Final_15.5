/**

 The MailBoxScreen class represents a GUI panel that displays the user's mailbox. It includes an inbox list, a read messages list, a message area to display the content of the selected message, and a respond button to respond to the selected message.
 */

package comMain.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MailBoxScreen extends JPanel {

    private JList<String> inboxList;
    private JList<String> readList;
    private JTextArea messageArea;
    private JButton respondButton;

    /**
     * Constructs a MailBoxScreen object.
     */
    public MailBoxScreen() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Mail Box");
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

// Create sample inbox messages and content
        ArrayList<String> inboxMessages = new ArrayList<String>();
        inboxMessages.add("Content of Message 1");
        inboxMessages.add("Content of Message 2");
        inboxMessages.add("Content of Message 3");
        inboxMessages.add("Content of Message 4");
        inboxMessages.add("Content of Message 5");
        inboxMessages.add("Content of Message 6");
        inboxMessages.add("Content of Message 7");
        inboxMessages.add("Content of Message 8");
        inboxMessages.add("Content of Message 9");
        inboxMessages.add("Content of Message 10");

// Create list of inbox messages
        String[] inboxArray = {"Message 1", "Message 2", "Message 3", "Message 4", "Message 5",
                "Message 6", "Message 7", "Message 8", "Message 9", "Message 10"};

        inboxList = new JList<String>(inboxArray);
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


        ArrayList<String> readMessages = new ArrayList<String>(); // Fixed initialization
        readMessages.add("Content of Message 1");
        readMessages.add("Content of Message 2");
        readMessages.add("Content of Message 3");

// Create list of read messages
        String[] readArray = {"Read Message 1", "Read Message 2", "Read Message 3"};
        readList = new JList<String>(readArray);
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
                // Create response window
                RespondingWindow respondWindow = new RespondingWindow();
                respondWindow.setVisible(true);
            }
        });
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageArea, BorderLayout.CENTER);
        messagePanel.add(respondButton, BorderLayout.SOUTH);
        add(messagePanel, BorderLayout.CENTER);



        // Add list selection listener to show message content and enable respond button
        inboxList.addListSelectionListener(new ListSelectionListener() {
            /**
             * display the message in the screen. in let the user to response
             * @param e the event that characterizes the change.
             */
            public void valueChanged(ListSelectionEvent e) {
                int index = inboxList.getSelectedIndex();
                if (index >= 0) {
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
             * @param e the event that characterizes the change.
             */
            public void valueChanged(ListSelectionEvent e) {
                int index = readList.getSelectedIndex();
                if (index >= 0) {
                    messageArea.setText(readMessages.get(index));
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



    }
}

