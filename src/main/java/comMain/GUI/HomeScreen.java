/**

 The HomeScreen class represents the main screen of the Library Management System.
 It contains a tabbed pane that displays different screens, including analytics, books management,
 readers management, settings, reservation management, reservation actions, and mailbox.
 */
package comMain.GUI;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class HomeScreen{

//    private final static String trustStorePath = "D:\\new\\La_biblioteca_Final_5.5-master\\src\\main\\resources\\truststore.jks";
//    private final static char[] trustStorePassword = "password".toCharArray();


    // The tabbed pane that displays the different screens
    public static JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * The main method creates an instance of the HomeScreen class and displays the JFrame containing
     * the tabbed pane with the different screens.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {


//        //importCertificate("D:\\new\\La_biblioteca_Final_5.5-master\\src\\main\\resources\\clientHttps.crt", "server");
//
//        System.setProperty("javax.net.ssl.trustStore", trustStorePath);
//        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword.toString());


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


        tabbedPane.addChangeListener(e -> {
            String selectedTab = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
            if (selectedTab.equals("Analytics")) {

                try {
                    analyticsScreen.refersh();
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (selectedTab.equals("Books Management")) {
                bookManagementScreen.refresh();
            } else if (selectedTab.equals("Readers Management")) {
                readersManagementScreen.refresh();
            }
            else if (selectedTab.equals("Reservation Management")) {
                reservationManagementScreen.refresh();
            }
        });
    }

//    private static void importCertificate(String certFilePath, String alias) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
//        // Load the certificate from file
//        FileInputStream fileInputStream = new FileInputStream(certFilePath);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//        Certificate certificate = certificateFactory.generateCertificate(bufferedInputStream);
//
//        // Add the certificate to the truststore
//        KeyStore trustStore = KeyStore.getInstance("JKS");
//        trustStore.load(new FileInputStream(trustStorePath), trustStorePassword);
//        trustStore.setCertificateEntry(alias, certificate);
//
//        // Save the modified truststore
//        FileOutputStream fileOutputStream = new FileOutputStream(trustStorePath);
//        trustStore.store(fileOutputStream, trustStorePassword);
//
//        // Close streams
//        bufferedInputStream.close();
//        fileInputStream.close();
//        fileOutputStream.close();
//    }



}


