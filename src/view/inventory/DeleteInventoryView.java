package view.inventory;

import controller.inventory.InventoryController;
import entities.InventoryItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DeleteInventoryView extends JDialog {

    private final JComboBox<String>itemComboBox;
    private final JLabel sqlCodeLabel;
    private final JLabel sqlNameLabel;
    private final JLabel sqlLocationLabel;
    private final JLabel sqlQuantityLabel;
    private final JLabel sqlMinQuantityLabel;
    private final JLabel sqlMaxQuantityLabel;
    private final JLabel sqlUpdateLabel;
    private final DefaultComboBoxModel<String> itemBoxModel;
    private final InventoryController inventoryController;

    private List<InventoryItem> rowData;

    public DeleteInventoryView(DefaultTableModel tableModel) {
        inventoryController = new InventoryController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Delete Inventory");
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel itemLabel = new JLabel();
        itemLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemLabel.setText("Item ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 20, 5, 5);
        this.add(itemLabel, gbc);

        itemComboBox = new JComboBox<>();
        itemComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemBoxModel = (DefaultComboBoxModel<String>) itemComboBox.getModel();
        populateComboBox();
        itemComboBox.addActionListener(evt1 -> itemComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(itemComboBox, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(deleteButton, gbc);

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        codeLabel.setText("Code:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(codeLabel, gbc);

        sqlCodeLabel = new JLabel();
        sqlCodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlCodeLabel, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(nameLabel, gbc);

        sqlNameLabel = new JLabel();
        sqlNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlNameLabel, gbc);

        JLabel locationLabel = new JLabel();
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationLabel.setText("Location:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(locationLabel, gbc);

        sqlLocationLabel = new JLabel();
        sqlLocationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlLocationLabel, gbc);

        JLabel quantityLabel = new JLabel();
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        quantityLabel.setText("Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(quantityLabel, gbc);

        sqlQuantityLabel = new JLabel();
        sqlQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlQuantityLabel, gbc);

        JLabel minQuantityLabel = new JLabel();
        minQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        minQuantityLabel.setText("Min Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(minQuantityLabel, gbc);

        sqlMinQuantityLabel = new JLabel();
        sqlMinQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlMinQuantityLabel, gbc);

        JLabel maxQuantityLabel = new JLabel();
        maxQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        maxQuantityLabel.setText("Max Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(maxQuantityLabel, gbc);

        sqlMaxQuantityLabel = new JLabel();
        sqlMaxQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlMaxQuantityLabel, gbc);

        JLabel updateLabel = new JLabel();
        updateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        updateLabel.setText("Last Update at:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 20, 20, 5);
        this.add(updateLabel, gbc);

        sqlUpdateLabel = new JLabel();
        sqlUpdateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(sqlUpdateLabel, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                formWindowClosing();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void formWindowClosing() {
        itemComboBox.setSelectedIndex(0);
        sqlCodeLabel.setText("");
        sqlNameLabel.setText("");
        sqlLocationLabel.setText("");
        sqlQuantityLabel.setText("");
        sqlMinQuantityLabel.setText("");
        sqlMaxQuantityLabel.setText("");
        sqlUpdateLabel.setText("");
    }

    private void itemComboBoxActionPerformed() {
        for (InventoryItem  inventoryItem : rowData) {
            if (inventoryItem.getItemId().equals(itemComboBox.getSelectedItem())) {
                sqlCodeLabel.setText(inventoryItem.getItemCode());
                sqlNameLabel.setText(inventoryItem.getName());
                sqlLocationLabel.setText(inventoryItem.getLocation());
                sqlQuantityLabel.setText(inventoryItem.getQuantity());
                sqlMinQuantityLabel.setText(inventoryItem.getMinQuantity());
                sqlMaxQuantityLabel.setText(inventoryItem.getMaxQuantity());
                sqlUpdateLabel.setText(inventoryItem.getLastUpdated());
                this.pack();
                break;
            }
        }
    }

    private void deleteButtonActionPerformed(DefaultTableModel tableModel) {
        if (itemComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String result = inventoryController.deleteInventory(Integer.parseInt((String) itemComboBox.getSelectedItem()));
        if (result.contains("Delete failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        inventoryController.getInventoryTable(tableModel);
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
        rowData = inventoryController.getInventoryList(itemBoxModel);
    }
}

