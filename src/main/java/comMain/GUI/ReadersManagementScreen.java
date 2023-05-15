/**

 The ReadersManagementScreen class represents the graphical user interface for managing readers.
 It extends the JPanel class.
 */

package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comMain.client.InformationGUI;
import comMain.entities.ReadersEntity;


public class ReadersManagementScreen extends JPanel {
    private JLabel titleLabel;
    private JList<ReadersEntity> readerList;
    private JButton editReaderButton;
    private JButton deleteReaderButton;
    private JButton addReaderButton;
    private JTextField searchField;
    private JButton searchButton;

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
        deleteReaderButton = new JButton("Delete");
        addReaderButton = new JButton("Add");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

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
        readerListButtonPanel.add(addReaderButton);
        readerListButtonPanel.add(editReaderButton);
        readerListButtonPanel.add(deleteReaderButton);
        readerListButtonPanel.add(searchPanel);
        add(readerListButtonPanel, BorderLayout.SOUTH);

        // Add action listeners
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
                DefaultListModel<ReadersEntity> filteredListModel = new DefaultListModel<ReadersEntity>();
                for (int i = 0; i < readerListModel.getSize(); i++) {
                    ReadersEntity reader = readerListModel.getElementAt(i);
                    if (reader.getIDno().contains(query)) {
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
                renderer.setText(reader.getIDno());
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });
    }
}
