/**

 The HomeScreen class represents the main screen of the Library Management System.
 It contains a tabbed pane that displays different screens, including analytics, books management,
 readers management, settings, reservation management, reservation actions, and mailbox.
 */
package comMain.GUI;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;

public class HomeScreen{

    // The tabbed pane that displays the different screens
    public static JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * The main method creates an instance of the HomeScreen class and displays the JFrame containing
     * the tabbed pane with the different screens.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) throws JsonProcessingException {
        // Create the JFrame
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create an instance of the AnalyticsScreen and add it to the tabbed pane
        AnalyticsScreen analyticsScreen = new AnalyticsScreen();
        tabbedPane.addTab("Analytics", analyticsScreen);


        // Create an instance of the BooksManagementScreen and add it to the tabbed pane
        BooksManagementScreen bookManagementScreen = new BooksManagementScreen();
        tabbedPane.addTab("Books Management", bookManagementScreen);

        // Create an instance of the ReadersManagementScreen and add it to the tabbed pane
        ReadersManagementScreen readersManagementScreen = new ReadersManagementScreen();
        tabbedPane.addTab("Readers Management", readersManagementScreen);

        // Create an instance of the SettingsScreen and add it to the tabbed pane
        SettingsScreen settingsScreen = new SettingsScreen();
        tabbedPane.addTab("Settings", settingsScreen);

        // Create an instance of the ReservationManagementScreen and add it to the tabbed pane
        ReservationManagementScreen reservationManagementScreen = new ReservationManagementScreen();
        tabbedPane.addTab("Reservation Management", reservationManagementScreen);

        // Create an instance of the ReservesActionsScreen and add it to the tabbed pane
        ReservesActionsScreen reservesActionsScreen = new ReservesActionsScreen();
        tabbedPane.addTab("Reservation Actions", reservesActionsScreen);

        // Create an instance of the MailBoxScreen and add it to the tabbed pane
        MailBoxScreen mailbox = new MailBoxScreen();
        tabbedPane.addTab("Mail Box", mailbox);

        // Add the tabbed pane to the frame
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }



}


