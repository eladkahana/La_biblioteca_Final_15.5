/**

 This class is responsible for creating the user interface for the screen
 that allows the user to add or edit book information.
 */

package comMain.GUI;

import comMain.client.InformationGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class AddEditBookScreen extends JPanel {

    private final JButton saveButton;
    private final JButton cancelButton;

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField editionField;
    private JTextField shelfmarkField;
    private JTextField pagesField;
    private JTextField yearField;
    private JComboBox<String> languageComboBox, publishersComoBox;
    private JTextField publisherField;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> authorComboBox;
    private JComboBox<String> seriesComboBox;
    private JComboBox<String> audienceComboBox;
    private JTextArea noteArea;
    private JButton uploadButton;
    private JLabel imageLabel;


    /**
     * Constructor for the AddEditBookScreen class. It sets the layout for the panel
     * and initializes all the components that will be displayed on the screen.
     */
    public AddEditBookScreen() {
        setLayout(new BorderLayout());

        // Create the input fields
        JPanel fieldsPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fieldsPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        fieldsPanel.add(isbnField);

        fieldsPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        fieldsPanel.add(titleField);

        fieldsPanel.add(new JLabel("Edition:"));
        editionField = new JTextField();
        fieldsPanel.add(editionField);

        fieldsPanel.add(new JLabel("Shelfmark:"));
        shelfmarkField = new JTextField();
        fieldsPanel.add(shelfmarkField);

        fieldsPanel.add(new JLabel("Number of Pages:"));
        pagesField = new JTextField();
        fieldsPanel.add(pagesField);

        fieldsPanel.add(new JLabel("Publish Year:"));
        yearField = new JTextField();
        fieldsPanel.add(yearField);

        fieldsPanel.add(new JLabel("Language:"));
        languageComboBox = InformationGUI.getAllLanguages();
        languageComboBox.setEditable(true);
        fieldsPanel.add(languageComboBox);

        fieldsPanel.add(new JLabel("Publisher:"));
        //publishersComoBox =
        publisherField = new JTextField();
        fieldsPanel.add(publisherField);



        fieldsPanel.add(new JLabel("Category:"));
        JPanel categoriesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        categoriesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel categoriesLabel = new JLabel("add categories:");
        categoriesPanel.add(categoriesLabel);

        JComboBox<String> categoryListOptions =InformationGUI.getAllCategories();
        categoryListOptions.setEditable(true);
        categoriesPanel.add(categoryListOptions);

        JComboBox<String> categoryListChose = new JComboBox<>();
        categoryListChose.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
        categoriesPanel.add(categoryListChose);

        JButton addCategoryButton = new JButton("add category");
        addCategoryButton.addActionListener(e -> {
            String selectedCategory = (String) categoryListOptions.getSelectedItem();
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                if (!containsItem(categoryListChose, selectedCategory)) {
                    categoryListChose.addItem(selectedCategory);
                }
            }
        });
        categoriesPanel.add(addCategoryButton);

        fieldsPanel.add(categoriesPanel);







        fieldsPanel.add(new JLabel("Author:"));
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        authorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel authorLabel = new JLabel("add authors:");
        authorPanel.add(authorLabel);

        JComboBox<String> authorListOptions = InformationGUI.getAllAuthors();
        authorListOptions.setEditable(true);
        authorPanel.add(authorListOptions);

        JComboBox<String> authorListChose = new JComboBox<>();
        authorListChose.setPrototypeDisplayValue("XXXXXXXXXXXXXX"); // set a larger width for the combo box
        authorListChose.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(value.toString());
                }
                return this;
            }
        });
        authorPanel.add(authorListChose);

        JButton addAuthorButton = new JButton("add author");
        addAuthorButton.addActionListener(e -> {
            String selectedAuthor = (String) authorListOptions.getSelectedItem();
            if (selectedAuthor != null && !selectedAuthor.isEmpty()) {
                if (!containsItem(authorListChose, selectedAuthor)) {
                    authorListChose.addItem(selectedAuthor);
                }
            }
        });
        authorPanel.add(addAuthorButton);

        fieldsPanel.add(authorPanel);





        fieldsPanel.add(new JLabel("Audience:"));
        JPanel audiencePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        audiencePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel audienceLabel = new JLabel("add audiences:");
        audiencePanel.add(audienceLabel);

        JComboBox<String> audienceListOptions = InformationGUI.getAllAudiences();
        audienceListOptions.setEditable(true);
        audiencePanel.add(audienceListOptions);

        JComboBox<String> audienceListChose = new JComboBox<>();
        audienceListChose.setPrototypeDisplayValue("XXXXXXXXXXXXXX"); // set a larger width for the combo box
        audienceListChose.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(value.toString());
                }
                return this;
            }
        });
        audiencePanel.add(audienceListChose);

        JButton addAudienceButton = new JButton("add audience");
        addAudienceButton.addActionListener(e -> {
            String selectedAudience = (String) audienceListOptions.getSelectedItem();
            if (selectedAudience != null && !selectedAudience.isEmpty()) {
                if (!containsItem(audienceListChose, selectedAudience)) {
                    audienceListChose.addItem(selectedAudience);
                }
            }
        });
        audiencePanel.add(addAudienceButton);

        fieldsPanel.add(audiencePanel);

        fieldsPanel.add(new JLabel("Note:"));
        noteArea = new JTextArea();
        noteArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(noteArea);
        fieldsPanel.add(scrollPane);


        // Create the image panel
        uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(selectedFile);
                    ImageIcon icon = new ImageIcon(image);
                    imageLabel.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });




        //series:
        fieldsPanel.add(new JLabel("Series:"));
        JPanel seriesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        seriesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        seriesComboBox = InformationGUI.getAllSeries();
        seriesComboBox.setEditable(true);
        seriesPanel.add(seriesComboBox);


        seriesPanel.add(new JLabel("Book Number:"));
        JTextField bookNumberTextField = new JTextField(10);
        seriesPanel.add(bookNumberTextField);

        JCheckBox enableSeriesCheckbox = new JCheckBox("Enable Series");
        seriesPanel.add(enableSeriesCheckbox);
        fieldsPanel.add(seriesPanel, BorderLayout.CENTER);

        enableSeriesCheckbox.addActionListener(e ->  {
                boolean isChecked = enableSeriesCheckbox.isSelected();
                seriesComboBox.setEnabled(isChecked);
                bookNumberTextField.setEnabled(isChecked);

        });

// Set initial state
        //seriesComboBox.setEnabled(enableSeriesCheckbox.isSelected());
        bookNumberTextField.setEnabled(enableSeriesCheckbox.isSelected());




        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(uploadButton, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add the input fields and the image panel to the main panel
        add(fieldsPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.EAST);

        // Create the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);


        saveButton.addActionListener(e -> {
            // Create a new dialog
            JDialog dialog = new JDialog();
            dialog.setTitle("Confirmation Message");

            // Create a panel for the message and the image
            JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
            JLabel messageLabel = new JLabel("Are you sure you want to save this book?");
            messagePanel.add(messageLabel, BorderLayout.NORTH);
            messagePanel.add(imageLabel, BorderLayout.CENTER);

            // Create a panel for the print button
            JPanel printPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton printButton = new JButton("Print");
            printButton.addActionListener(printActionListener());
            printPanel.add(printButton);

            // Add the message panel and the print panel to the dialog
            dialog.add(messagePanel, BorderLayout.CENTER);
            dialog.add(printPanel, BorderLayout.SOUTH);

            // Set the size and show the dialog
            dialog.pack();
            dialog.setVisible(true);
        });

    }


    // Helper method to check if a JComboBox already contains an item
    private boolean containsItem(JComboBox<String> comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).equals(item)) {
                return true;
            }
        }
        return false;
    }

    // action for printing the image
    private ActionListener printActionListener() {
        return e -> {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex == 0) {
                        Graphics2D g2d = (Graphics2D) graphics;
                        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                        imageLabel.paint(g2d);
                        return Printable.PAGE_EXISTS;
                    } else {
                        return Printable.NO_SUCH_PAGE;
                    }
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

