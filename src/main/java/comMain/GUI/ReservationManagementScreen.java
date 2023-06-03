/**

 This class represents the GUI panel for managing reservations.
 */

package comMain.GUI;

import comMain.SwingClient.InformationGUI;
import comMain.SwingClient.ReservationClient;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationManagementScreen extends JPanel {
    private JButton reminderButton;
    private JLabel titleLabel;
    private JTable reserveTable;
    private JButton deleteReserveButton;
    private JTextField searchField;
    private JButton searchButton;

    private ReservationTableModel tableModel;
    private TableRowSorter<ReservationTableModel> tableRowSorter;

    public ReservationManagementScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        titleLabel = new JLabel("Reserves Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        reminderButton = new JButton("Reminder");
        deleteReserveButton = new JButton("Delete");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        tableModel = new ReservationTableModel();
        reserveTable = new JTable(tableModel);
        tableRowSorter = new TableRowSorter<>(tableModel);
        reserveTable.setRowSorter(tableRowSorter);
        reserveTable.setDefaultRenderer(Object.class, new RightAlignedCellRenderer());

        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JScrollPane reserveTableScrollPane = new JScrollPane(reserveTable);
        add(reserveTableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteReserveButton);
        buttonPanel.add(reminderButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(searchPanel, BorderLayout.EAST);
        bottomPanel.add(buttonPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);




        // Add action listeners




        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                filterTable(query);
            }
        });

        reminderButton.addActionListener(e -> {
            ReservationClient.reminder();
            JOptionPane.showMessageDialog(null, "send the messages.");
        });
    }

    public void refresh() {
        tableModel.updateData();
        searchField.setText(""); // Clear the search field
        filterTable(""); // Reset the table filter
    }


    public void filterTable(String query) {
        RowFilter<ReservationTableModel, Object> rowFilter = RowFilter.regexFilter(query);
        tableRowSorter.setRowFilter(rowFilter);
    }

    private class ReservationTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Book", "Reader", "From", "To"};
        private List<Object[]> reservations;
        private List<Object[]> filteredReservations;

        public ReservationTableModel() {
            reservations = InformationGUI.getAllReserves();
            filteredReservations = new ArrayList<>(reservations);
        }



        @Override
        public int getRowCount() {
            return filteredReservations.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object[] reservation = filteredReservations.get(rowIndex);
            return reservation[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }


        public void updateData() {
            reservations = InformationGUI.getAllReserves();
            filteredReservations = new ArrayList<>(reservations);
            fireTableDataChanged(); // Notify the table that the data has changed
        }

    }

    private class RightAlignedCellRenderer extends DefaultTableCellRenderer {
        public RightAlignedCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
}
