/**

 The ReservesActionsScreen class represents a JPanel for handling reserve actions, such as making a reservation or returning a book.
 */

package comMain.GUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class ReservesActionsScreen extends JPanel {
    private JLabel readerIDLabel, bookIDLabel, dueToLabel, reservationTypeLabel;
    private JTextField readerIDField, bookIDField;
    private JDateChooser dueToChooser;
    private JButton confirmButton;
    private JComboBox<String> reservationTypeComboBox;

    /**
     * Constructs a ReservesActionsScreen object with default settings.
     */
    public ReservesActionsScreen() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        reservationTypeLabel = new JLabel("Reservation Type:");
        reservationTypeComboBox = new JComboBox<String>(new String[]{"ReserveEntity", "Return"});

        readerIDLabel = new JLabel("Reader ID:");
        readerIDField = new JTextField();

        bookIDLabel = new JLabel("Book ID:");
        bookIDField = new JTextField();

        dueToLabel = new JLabel("Due Date:");
        dueToChooser = new JDateChooser();

        // Initialize star rating panel
        JPanel ratingPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Rating"));
        JToggleButton[] stars = new JToggleButton[5];

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JToggleButton(Integer.toString(i+1));
            stars[i].setPreferredSize(new Dimension(40, 40)); // Set preferred size for better visibility
            ratingPanel.add(stars[i]);
        }
        ratingPanel.setVisible(false);


        confirmButton = new JButton("Confirm");

        // Add components to panel
        add(reservationTypeLabel);
        add(reservationTypeComboBox);
        add(readerIDLabel);
        add(readerIDField);
        add(bookIDLabel);
        add(bookIDField);
        add(dueToLabel);
        add(dueToChooser);
        add(new JLabel()); // Empty label for spacing
        add(ratingPanel);
        add(confirmButton);

        // Set default reservation type to "ReserveEntity"
        reservationTypeComboBox.setSelectedIndex(0);

        // Set default due date to two weeks from the current date for reservation
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 14);
        dueToChooser.setDate(cal.getTime());




        // Add action listeners
        reservationTypeComboBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) reservationTypeComboBox.getSelectedItem();
                if (selectedType.equals("Return")) {
                    dueToChooser.setVisible(false);
                    dueToLabel.setVisible(false);
                    ratingPanel.setVisible(true);
                } else {
                    dueToChooser.setVisible(true);
                    dueToLabel.setVisible(true);
                    ratingPanel.setVisible(false);
                }
            }
        });

        // Add action listeners
        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 14);
                dueToChooser.setDate(cal.getTime());
            }
        });



// Add action listener to star buttons
        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j <= index; j++) {
                        stars[j].setBackground(Color.yellow);
                    }
                    for (int j = index + 1; j < stars.length; j++) {
                        stars[j].setBackground(null);
                    }
                }
            });

        }


    }
}
