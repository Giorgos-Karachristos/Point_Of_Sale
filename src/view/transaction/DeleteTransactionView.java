package view.transaction;

import controller.transaction.TransactionController;
import entities.TransactionItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DeleteTransactionView extends JDialog {

    private final JComboBox<String>transactionIDComboBox;
    private final JLabel sqlItemIDLabel;
    private final JLabel sqlCodeLabel;
    private final JLabel sqlNameLabel;
    private final JLabel sqlOldQuantityLabel;
    private final JLabel sqlTypeLabel;
    private final JLabel sqlTransactionQuantityLabel;
    private final JLabel sqlDateLabel;
    private final JLabel sqlStatusLabel;
    private final DefaultComboBoxModel<String> transactionBoxModel;
    private final TransactionController transactionController;

    private List<TransactionItem> rowData;

    public DeleteTransactionView(DefaultTableModel tableModel) {
        transactionController = new TransactionController();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Delete Transaction");
        this.setResizable(false);

        GridBagConstraints gbc;
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        JLabel transactionIDLabel = new JLabel();
        transactionIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        transactionIDLabel.setText("Transaction ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(20, 20, 10, 10);
        getContentPane().add(transactionIDLabel, gbc);

        transactionIDComboBox = new JComboBox<>();
        transactionIDComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        transactionBoxModel = (DefaultComboBoxModel<String>) transactionIDComboBox.getModel();
        populateComboBox();
        transactionIDComboBox.addActionListener(evt1 -> comboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 10, 10, 10);
        getContentPane().add(transactionIDComboBox, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 10, 10, 20);
        getContentPane().add(deleteButton, gbc);

        JLabel itemIDLabel = new JLabel();
        itemIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemIDLabel.setText("Item ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 20, 10, 10);
        getContentPane().add(itemIDLabel, gbc);

        sqlItemIDLabel = new JLabel();
        sqlItemIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(sqlItemIDLabel, gbc);

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        codeLabel.setText("Item Code:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(codeLabel, gbc);

        sqlCodeLabel = new JLabel();
        sqlCodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(sqlCodeLabel, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(nameLabel, gbc);

        sqlNameLabel = new JLabel();
        sqlNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 20);
        getContentPane().add(sqlNameLabel, gbc);

        JLabel oldQuantityLabel = new JLabel();
        oldQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        oldQuantityLabel.setText("Old Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 20, 10, 10);
        getContentPane().add(oldQuantityLabel, gbc);

        sqlOldQuantityLabel = new JLabel();
        sqlOldQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(sqlOldQuantityLabel, gbc);

        JLabel typeLabel = new JLabel();
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        typeLabel.setText("Type:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(typeLabel, gbc);

        sqlTypeLabel = new JLabel();
        sqlTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(sqlTypeLabel, gbc);

        JLabel transactionQuantityLabel = new JLabel();
        transactionQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        transactionQuantityLabel.setText("Transaction Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(transactionQuantityLabel, gbc);

        sqlTransactionQuantityLabel = new JLabel();
        sqlTransactionQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 20);
        getContentPane().add(sqlTransactionQuantityLabel, gbc);

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dateLabel.setText("Transaction Date:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 20, 20, 10);
        getContentPane().add(dateLabel, gbc);

        sqlDateLabel = new JLabel();
        sqlDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 20, 10);
        getContentPane().add(sqlDateLabel, gbc);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        statusLabel.setText("Status:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 20, 10);
        getContentPane().add(statusLabel, gbc);

        sqlStatusLabel = new JLabel();
        sqlStatusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 20, 20);
        getContentPane().add(sqlStatusLabel, gbc);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                formWindowClosing();
            }
        });
        pack();
        setLocationRelativeTo(null);
    }

    private void formWindowClosing() {
        transactionIDComboBox.setSelectedIndex(0);
        sqlItemIDLabel.setText("");
        sqlCodeLabel.setText("");
        sqlNameLabel.setText("");
        sqlOldQuantityLabel.setText("");
        sqlTypeLabel.setText("");
        sqlTransactionQuantityLabel.setText("");
        sqlDateLabel.setText("");
        sqlStatusLabel.setText("");
    }

    private void comboBoxActionPerformed() {
        for (TransactionItem transactionItem : rowData) {
            if (transactionItem.getTransactionId().equals(transactionIDComboBox.getSelectedItem())) {
                sqlItemIDLabel.setText(transactionItem.getItemId());
                sqlCodeLabel.setText(transactionItem.getItemCode());
                sqlNameLabel.setText(transactionItem.getName());
                sqlOldQuantityLabel.setText(transactionItem.getOldQuantity());
                sqlTypeLabel.setText(transactionItem.getType());
                sqlTransactionQuantityLabel.setText(transactionItem.getTransactionQuantity());
                sqlDateLabel.setText(transactionItem.getTransactionDate());
                sqlStatusLabel.setText(transactionItem.getStatus());
                this.pack();
                break;
            }
        }
    }

    private void deleteButtonActionPerformed(DefaultTableModel tableModel) {
        if (transactionIDComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a transaction to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String result = transactionController.deleteInventory(Integer.parseInt((String) transactionIDComboBox.getSelectedItem()));
        if (result.contains("Delete failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        transactionController.getTransactionTable(tableModel);
        populateComboBox();
        formWindowClosing();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            populateComboBox();
        }
        super.setVisible(visible);
    }

    private void populateComboBox() {
        rowData = transactionController.getTransactionID(transactionBoxModel);
    }
}
