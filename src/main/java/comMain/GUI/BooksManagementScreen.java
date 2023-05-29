/**

 This class represents the Books Management screen, which displays a list of books and
 allows the user to add, edit, delete, and search for books.
 */

package comMain.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import comMain.SwingClient.BookManagerClient;
import comMain.SwingClient.InformationGUI;
import comMain.entities.BookEntity;


public class BooksManagementScreen extends JPanel {
    private final JButton createCopyButton;
    private JLabel titleLabel;
    private JList<BookEntity> bookList;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;
    private JTextField searchField;
    private JButton searchButton;

    DefaultListModel<BookEntity> filteredListModel;

    /**
     * Constructor that initializes the Books Management screen with its components.
     */
    public BooksManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Books Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bookList = new JList<BookEntity>();
        editBookButton = new JButton("Edit");
        deleteBookButton = new JButton("Delete");
        createCopyButton = new JButton("create Copy");
        addBookButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set book list model
        DefaultListModel<BookEntity> bookListModel  = InformationGUI.getAllBooks();
        bookList.setModel(bookListModel);


        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        JScrollPane bookListScrollPane = new JScrollPane(bookList);
        add(bookListScrollPane, BorderLayout.CENTER);

        JPanel bookListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookListButtonPanel.add(createCopyButton);

        bookListButtonPanel.add(addBookButton);
        bookListButtonPanel.add(editBookButton);
        bookListButtonPanel.add(deleteBookButton);

        bookListButtonPanel.add(searchPanel);
        add(bookListButtonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addBookButton.addActionListener(new ActionListener() {
            /**
             * Called when the Add button is clicked. Opens the AddEditBookScreen.
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditBookScreen when the Add button is clicked
                AddEditBookScreen addEditBookScreen = new AddEditBookScreen();
                addEditBookScreen.setPreferredSize(new Dimension(1200, 600));
                JFrame newFrame = new JFrame();
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setContentPane(addEditBookScreen);
                newFrame.pack();
                newFrame.setVisible(true);




            }
        });


        editBookButton.addActionListener(new ActionListener() {
            /**
             * Called when the Edit button is clicked. Opens the AddEditBookScreen.
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the AddEditBookScreen when the Edit button is clicked
                AddEditBookScreen addEditBookScreen = new AddEditBookScreen();
                addEditBookScreen.setPreferredSize(new Dimension(1200, 600));
                if(filteredListModel != null){
                    addEditBookScreen.Edit(filteredListModel.getElementAt(bookList.getSelectedIndex()));

                }
                else {
                    addEditBookScreen.Edit(bookListModel.getElementAt(bookList.getSelectedIndex()));
                }

                JFrame newFrame = new JFrame();
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setContentPane(addEditBookScreen);
                newFrame.pack();
                newFrame.setVisible(true);




            }
        });


        deleteBookButton.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setTitle("delete Window");
            dialog.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel(new BorderLayout(10, 10));

            JLabel IDLabel = new JLabel("ID:");
            JTextField IDField = new JTextField(20);
            contentPanel.add(IDLabel, BorderLayout.WEST);
            contentPanel.add(IDField, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ID = IDField.getText();
                    // Perform any necessary actions with the entered name
                    BookManagerClient.deleteCopy(Integer.parseInt(ID));
                    dialog.dispose();
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(confirmButton);

            dialog.add(contentPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setSize(300, 150);
            dialog.setVisible(true);

        });

        searchButton.addActionListener(new ActionListener() {
            /**
             * Called when the Search button is clicked. Filters the book list by title.
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                filteredListModel = new DefaultListModel<BookEntity>();
                for (int i = 0; i < bookListModel.getSize(); i++) {
                    BookEntity book = bookListModel.getElementAt(i);
                    if (book.getTitle().contains(query)) {
                        filteredListModel.addElement(book);
                    }
                }
                bookList.setModel(filteredListModel);
            }
        });


        bookList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                /**
                 * Renders the book title in the book list
                 */
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                BookEntity book = (BookEntity) value;
                renderer.setText(book.getTitle());
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });




        createCopyButton.addActionListener(e ->
        {
            int newId;
            if(filteredListModel != null){
                 newId = BookManagerClient.createCopy(filteredListModel.get(bookList.getSelectedIndex()).getId());

            }
            else {
                 newId = BookManagerClient.createCopy(bookListModel.get(bookList.getSelectedIndex()).getId());
            }

            JDialog dialog = new JDialog();
            dialog.setTitle("Confirmation Message");

// Create a panel for the message and the image
            JPanel contentPanel = new JPanel(new BorderLayout(10, 10));

// Create a label for the message
            JLabel messageLabel = new JLabel("The book has been successfully saved. Here is the book's code:");
            contentPanel.add(messageLabel, BorderLayout.NORTH);

// Create a panel for the barcode
            JPanel barcodePanel = new JPanel(new BorderLayout(10, 10));

// Generate the barcode image as a byte array
            byte[] barcodeData = InformationGUI.getBarcode(Integer.toString(newId));
            ImageIcon imageForPrint = null;
            try {
                // Create an InputStream from the barcodeData
                InputStream in = new ByteArrayInputStream(barcodeData);

                // Read the barcode image from the InputStream
                BufferedImage barcodeImage = ImageIO.read(in);
                imageForPrint = new ImageIcon(barcodeImage);
                // Scale down the barcode image
                int targetWidth = 350;
                int targetHeight = 200;
                Image scaledImage = barcodeImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

                // Create an ImageIcon from the scaled barcode image
                ImageIcon barcodeIcon = new ImageIcon(scaledImage);

                // Create a JLabel with the barcodeIcon
                JLabel barcodeLabel = new JLabel(barcodeIcon);

                // Add the barcodeLabel to the barcode panel
                barcodePanel.add(barcodeLabel, BorderLayout.CENTER);
            } catch (IOException ex) {
                ex.printStackTrace();
            }


            JPanel ButtonstPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton printButton = new JButton("Print");

            printButton.addActionListener(printActionListener(imageForPrint));
            ButtonstPanel.add(printButton);

// Create a panel for the exit button
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Perform action when Exit button is clicked
                    dialog.dispose(); // Close the dialog
                }
            });
            ButtonstPanel.add(exitButton);




// Add the barcode panel, print panel, and exit panel to the content panel
            contentPanel.add(barcodePanel, BorderLayout.CENTER);
            contentPanel.add(ButtonstPanel, BorderLayout.SOUTH);

// Add the content panel to the dialog
            dialog.add(contentPanel);

// Set the size of the dialog
            dialog.setSize(400, 400);

// Center the dialog on the screen
            dialog.setLocationRelativeTo(null);

// Show the dialog
            dialog.setVisible(true);

        });




    }


    private ActionListener printActionListener(ImageIcon barcodeIcon) {
        return e -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex == 0) {
                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                    barcodeIcon.paintIcon(null, g2d, 0, 0);
                    return Printable.PAGE_EXISTS;
                } else {
                    return Printable.NO_SUCH_PAGE;
                }
            });
            boolean doPrint = job.printDialog();
            if (doPrint) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
}
