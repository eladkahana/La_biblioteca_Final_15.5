/**

 This class represents the GUI panel for managing reservations.
 */

package comMain.GUI;

import com.toedter.calendar.JDateChooser;
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
import java.util.Date;
import java.util.List;

public class ReservationManagementScreen extends JPanel {
    private JButton reminderButton;
    private JLabel titleLabel;
    private JTable reserveTable;
    private JButton extensionReserveButton;
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
        extensionReserveButton = new JButton("Extension");
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
        buttonPanel.add(extensionReserveButton);
        buttonPanel.add(reminderButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(searchPanel, BorderLayout.EAST);
        bottomPanel.add(buttonPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);




        // Add action listeners




        extensionReserveButton.addActionListener(e -> {


            JDialog dialog = new JDialog();
            dialog.setTitle("Extension Reserve Window");
            dialog.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel(new BorderLayout(10, 10));


            JLabel dueToLabel = new JLabel("new return date:");
            JDateChooser dueToChooser = new JDateChooser();
            dueToChooser.setDateFormatString("dd/MM/yyyy"); // Set the date format
            contentPanel.add(dueToLabel, BorderLayout.WEST);
            contentPanel.add(dueToChooser, BorderLayout.CENTER);

            JPanel extensionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
                    long milliseconds = dueToChooser.getDate().getTime();
                    java.sql.Date sqlDate = new java.sql.Date(milliseconds);

                    int tableRow = reserveTable.getSelectedRow();
                    int reserveID = (int) tableModel.getSelectLine(tableRow)[4];

                    ReservationClient.addExtension(reserveID,sqlDate);
                    // Perform any necessary actions with the entered ID and due date
                    dialog.dispose();
                }
            });
            extensionButtonPanel.add(cancelButton);
            extensionButtonPanel.add(confirmButton);

            dialog.add(contentPanel, BorderLayout.CENTER);
            dialog.add(extensionButtonPanel, BorderLayout.SOUTH);
            dialog.setSize(300, 150);
            dialog.setVisible(true);
        });

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
            fireTableDataChanged();
        }

        public Object[] getSelectLine(int selectedLine){
            return this.reservations.get(selectedLine);
        }

    }

    private class RightAlignedCellRenderer extends DefaultTableCellRenderer {
        public RightAlignedCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
}
