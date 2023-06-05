/**

 Represents a responding window for a message.
 */

package comMain.GUI;

import comMain.SwingClient.InformationGUI;
import comMain.entities.ReadersEntity;
import comMain.entities.RequestsEntity;

import javax.swing.*;
import java.awt.*;

public class RespondingWindow extends JFrame {

    private JTextArea messageTextArea;
    private JButton sendButton;

    /**
     * Constructs a responding window.
     * Initializes the message text area, send button, and button panel, and sets the window size and layout.
     */
    public RespondingWindow(RequestsEntity request) {
        setTitle("Respond to Message");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        // Message text area
        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        add(messageScrollPane, BorderLayout.CENTER);

        // Send button
        sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            InformationGUI.responseEmail(request, messageTextArea.getText());
            JOptionPane.showMessageDialog(null, "send the messages.");
            dispose();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(sendButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

}
