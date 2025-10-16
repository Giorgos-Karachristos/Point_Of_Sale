package view.transaction;

import controller.inventory.InventoryController;
import controller.transaction.TransactionController;
import entities.InventoryItem;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class InsertTransactionView extends JDialog {

    private final JComboBox<String> typeComboBox;
    private final JTable jTable;
    private final DefaultTableModel tableModel;
    private final JComboBox<String> itemComboBox;
    private final DefaultComboBoxModel<String> itemBoxModel;
    private final InventoryController inventoryController;
    private final TransactionController transactionController;

    private List<InventoryItem> inventoryItemList;

    public InsertTransactionView(DefaultTableModel backgroundTableModel) {
        inventoryController = new InventoryController();
        transactionController = new TransactionController();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Add Transaction");
        this.setPreferredSize(new Dimension(950, 435));
        this.setResizable(false);

        JPanel topPanel = new JPanel();

        JLabel typeLabel = new JLabel();
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        typeLabel.setText("Type:");
        topPanel.add(typeLabel);

        typeComboBox = new JComboBox<>();
        typeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        typeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Add", "Delete"}));
        topPanel.add(typeComboBox);

        JButton addItemButton = new JButton();
        addItemButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addItemButton.setText("Add Item");
        addItemButton.addActionListener(evt1 -> addItemButtonActionPerformed());
        topPanel.add(addItemButton);

        JButton deleteItemButton = new JButton();
        deleteItemButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteItemButton.setText("Delete Item");
        deleteItemButton.addActionListener(e -> deleteItemButtonActionPerformed());
        topPanel.add(deleteItemButton);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(backgroundTableModel));
        topPanel.add(submitButton);

        this.add(topPanel, BorderLayout.PAGE_START);

        jTable = new JTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTable.setRowHeight(40);
        jTable.setGridColor(new Color(0, 0, 0));
        jTable.setShowGrid(true);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Item ID", "Code", "Name", "In Stock", "Quantity", "New Quantity", "Max Quantity"}) {
            final boolean[] canEdit = new boolean[]{true, false, false, false, true, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        jTable.setModel(tableModel);

        itemComboBox = new JComboBox<>();
        itemBoxModel = (DefaultComboBoxModel<String>) itemComboBox.getModel();
        populateComboBox();

        itemComboBox.addActionListener(evt -> comboBoxActionPerformed());
        jTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(itemComboBox));

        tableModel.addTableModelListener(this::tableChanged);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);
        this.add(jScrollPane, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                formWindowClosing();
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void addItemButtonActionPerformed() {
        tableModel.addRow(new Object[]{});
        int lastRow = jTable.getRowCount() - 1;
        jTable.setRowSelectionInterval(lastRow, lastRow);
        typeComboBox.setEnabled(false);
    }

    private void deleteItemButtonActionPerformed() {
        int row = jTable.getSelectedRow();
        if (row != -1) {
            tableModel.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        if (tableModel.getRowCount() == 0) {
            typeComboBox.setEnabled(true);
        }
    }

    private static final int ITEM_ID_COL = 0;
    private static final int CODE_COL = 1;
    private static final int NAME_COL = 2;
    private static final int IN_STOCK_COL = 3;
    private static final int QUANTITY_COL = 4;
    private static final int NEW_QUANTITY_COL = 5;
    private static final int MAX_QUANTITY_COL = 6;

    private void submitButtonActionPerformed(DefaultTableModel backgroundTableModel) {
        int rowCount = tableModel.getRowCount();
        boolean allValid = true;

        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Please add items into the transaction", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (int row = 0; row < rowCount; row++) {
            String quantityText = (String) tableModel.getValueAt(row, QUANTITY_COL);
            if (tableModel.getValueAt(row, ITEM_ID_COL) == null || quantityText == null || tableModel.getValueAt(row, NEW_QUANTITY_COL) == null) {
                allValid = false;
                JOptionPane.showMessageDialog(this, "All fields are required for row " + (row + 1), "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            allValid = isValid(quantityText, row);
        }

        if (allValid) {
            processAllTransactions(rowCount);
            transactionController.getTransactionTable(backgroundTableModel);
            populateComboBox();
            formWindowClosing();
        }
    }

    private void processAllTransactions(int rowCount) {
        int failed = 0;
        String result;

        for (int row = 0; row < rowCount; row++) {
            result = transactionController.insertTransaction(Integer.parseInt(tableModel.getValueAt(row, ITEM_ID_COL).toString()), Integer.parseInt(tableModel.getValueAt(row, IN_STOCK_COL).toString()), String.valueOf(typeComboBox.getSelectedItem()), Integer.parseInt(tableModel.getValueAt(row, QUANTITY_COL).toString()), Integer.parseInt(tableModel.getValueAt(row, NEW_QUANTITY_COL).toString()));

            if (result.contains("Insertion failed: ")) {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                failed++;
            }
        }
        JOptionPane.showMessageDialog(this, rowCount - failed + " transactions inserted successfully.\n" + failed + " transactions failed.", "Transaction Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private void comboBoxActionPerformed() {
        int row = jTable.getSelectedRow();
        for (InventoryItem inventoryItem : inventoryItemList) {
            if (inventoryItem.getItemId().equals(itemComboBox.getSelectedItem())) {
                tableModel.setValueAt(inventoryItem.getItemCode(), row, CODE_COL);
                tableModel.setValueAt(inventoryItem.getName(), row, NAME_COL);
                tableModel.setValueAt(Integer.valueOf(inventoryItem.getQuantity()), row, IN_STOCK_COL);
                tableModel.setValueAt(Integer.valueOf(inventoryItem.getMaxQuantity()), row, MAX_QUANTITY_COL);
                break;
            }
        }
    }

    private void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (column == ITEM_ID_COL) {
            tableModel.setValueAt(null, row, QUANTITY_COL);
        }

        if (itemComboBox.getSelectedIndex() != 0 && column == QUANTITY_COL) {//User Input column changed
            Object stock = tableModel.getValueAt(row, IN_STOCK_COL);
            if (stock != null) {
                String quantityText = (String) tableModel.getValueAt(row, QUANTITY_COL);
                if (!isValid(quantityText, row)) {
                    return;
                }
                int quantity = Integer.parseInt(quantityText);
                int inStock = (int) stock;
                int newQuantity;
                if ("Add".equals(typeComboBox.getSelectedItem())) {
                    newQuantity = inStock + quantity;
                } else {
                    newQuantity = inStock - quantity;
                }

                if (newQuantity < 0) {
                    JOptionPane.showMessageDialog(this, "New quantity cannot be negative for row " + (row + 1), "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int maxQuantity = Integer.parseInt(tableModel.getValueAt(row, MAX_QUANTITY_COL).toString());
                if (newQuantity > maxQuantity) {
                    JOptionPane.showMessageDialog(this, "New quantity cannot pass the maximum quantity for row " + (row + 1), "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tableModel.setValueAt(newQuantity, row, NEW_QUANTITY_COL);
            }
        }
    }

    private boolean isValid(String price, int row) {
        try {
            int number = Integer.parseInt(price);
            if (number <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero for row " + (row + 1), "Validation Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Quantity must be number for row " + (row + 1), "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void formWindowClosing() {
        tableModel.setRowCount(0);
        typeComboBox.setEnabled(true);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            populateComboBox();
        }
        super.setVisible(visible);
    }

    private void populateComboBox() {
        inventoryItemList = inventoryController.getInventoryList(itemBoxModel);
    }
}
