/**

 This class represents the Books Management screen, which displays a list of books and
 allows the user to add, edit, delete, and search for books.
 */

package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comMain.client.InformationGUI;
import comMain.entities.BookEntity;


public class BooksManagementScreen extends JPanel {
    private JLabel titleLabel;
    private JList<BookEntity> bookList;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;
    private JTextField searchField;
    private JButton searchButton;

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

        searchButton.addActionListener(new ActionListener() {
            /**
             * Called when the Search button is clicked. Filters the book list by title.
             * @param e the action event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                DefaultListModel<BookEntity> filteredListModel = new DefaultListModel<BookEntity>();
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

    }
}
