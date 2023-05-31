/**

 This class represents the GUI panel for managing reservations.
 */

package comMain.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import comMain.SwingClient.InformationGUI;
import comMain.SwingClient.ReservationClient;
import comMain.entities.ReserveEntity;


public class ReservationManagementScreen extends JPanel {
    private final JButton reminderButton;
    private JLabel titleLabel;
    private JList<ReserveEntity> reserveList;
    private JButton deleteReserveButton;
    private JButton addReserveButton;
    private JTextField searchField;
    private JButton searchButton;


    /**
     * Constructs a new ReservationManagementScreen object.
     * Sets layout and initializes components.
     */
    public ReservationManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Reserves Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        reserveList = new JList<ReserveEntity>();
        reminderButton = new JButton("reminder");
        deleteReserveButton = new JButton("Delete");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Set reserve list model
        DefaultListModel<ReserveEntity> reserveListModel = InformationGUI.getAllReserves();
        reserveList.setModel(reserveListModel);


        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        JScrollPane reserveListScrollPane = new JScrollPane(reserveList);
        add(reserveListScrollPane, BorderLayout.CENTER);

        JPanel reserveListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        reserveListButtonPanel.add(deleteReserveButton);
        reserveListButtonPanel.add(reminderButton);

        reserveListButtonPanel.add(searchPanel);
        add(reserveListButtonPanel, BorderLayout.SOUTH);






        // Add action listeners



        searchButton.addActionListener(new ActionListener() {
            /*
                filter the list by the search key
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                DefaultListModel<ReserveEntity> filteredListModel = new DefaultListModel<ReserveEntity>();
                for (int i = 0; i < reserveListModel.getSize(); i++) {
                    ReserveEntity reserve = reserveListModel.getElementAt(i);
                    if (InformationGUI.reservationTitle(reserve).contains(query)) {
                        filteredListModel.addElement(reserve);
                    }
                }
                reserveList.setModel(filteredListModel);
            }
        });


        reserveList.setCellRenderer(new DefaultListCellRenderer() {
            /*
               display the properties of the reserves in the list
             */
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ReserveEntity reserve = (ReserveEntity) value;
                renderer.setText(InformationGUI.reservationTitle(reserve));
                renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                return renderer;
            }
        });


        reminderButton.addActionListener(e -> {
            ReservationClient.reminder();
            JOptionPane.showMessageDialog(null, "send the messages.");
        });
    }
}
