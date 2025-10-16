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

public class UpdateInventoryView extends JDialog {

    private final JComboBox<String>itemComboBox;
    private final JLabel sqlCodeLabel;
    private final JLabel sqlNameLabel;
    private final JTextField locationTextField;
    private final JTextField quantityTextField;
    private final JTextField minQuantityTextField;
    private final JTextField maxQuantityTextField;
    private final DefaultComboBoxModel<String> itemBoxModel;
    private final InventoryController inventoryController;

    private List<InventoryItem> inventoryItemList;

    public UpdateInventoryView(DefaultTableModel tableModel) {
        inventoryController = new InventoryController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Inventory");
        this.setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel itemLabel = new JLabel();
        itemLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemLabel.setText("Item ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(itemLabel, gbc);

        itemComboBox = new JComboBox<>();
        itemComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemBoxModel = (DefaultComboBoxModel<String>) itemComboBox.getModel();
        populateComboBox();
        itemComboBox.addActionListener(e -> itemComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(itemComboBox, gbc);

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        codeLabel.setText("Code:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(codeLabel, gbc);

        sqlCodeLabel = new JLabel();
        sqlCodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlCodeLabel, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(nameLabel, gbc);

        sqlNameLabel = new JLabel();
        sqlNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlNameLabel, gbc);

        JLabel locationLabel = new JLabel();
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationLabel.setText("Location:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(locationLabel, gbc);

        locationTextField = new JTextField();
        locationTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(locationTextField, gbc);

        JLabel quantityLabel = new JLabel();
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        quantityLabel.setText("Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(quantityLabel, gbc);

        quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(quantityTextField, gbc);

        JLabel minQuantityLabel = new JLabel();
        minQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        minQuantityLabel.setText("Min Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(minQuantityLabel, gbc);

        minQuantityTextField = new JTextField();
        minQuantityTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(minQuantityTextField, gbc);

        JLabel maxQuantityLabel = new JLabel();
        maxQuantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        maxQuantityLabel.setText("Max Quantity:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(maxQuantityLabel, gbc);

        maxQuantityTextField = new JTextField();
        maxQuantityTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(maxQuantityTextField, gbc);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(submitButton, gbc);

        this.addWindowListener(new WindowAdapter() {
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
        locationTextField.setText("");
        quantityTextField.setText("");
        minQuantityTextField.setText("");
        maxQuantityTextField.setText("");
    }

    private void itemComboBoxActionPerformed() {
        for (InventoryItem  inventoryItem : inventoryItemList) {
            if (inventoryItem.getItemId().equals(itemComboBox.getSelectedItem())) {
                sqlCodeLabel.setText(inventoryItem.getItemCode());
                sqlNameLabel.setText(inventoryItem.getName());
                locationTextField.setText(inventoryItem.getLocation());
                quantityTextField.setText(inventoryItem.getQuantity());
                minQuantityTextField.setText(inventoryItem.getMinQuantity());
                maxQuantityTextField.setText(inventoryItem.getMaxQuantity());
                this.pack();
                break;
            }
        }
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        String location = locationTextField.getText();
        String quantityText = quantityTextField.getText();
        String minQuantityText = minQuantityTextField.getText();
        String maxQuantityText = maxQuantityTextField.getText();

        if (itemComboBox.getSelectedIndex() == 0 || location.isBlank() || quantityText.isBlank() || minQuantityText.isBlank() || maxQuantityText.isBlank()) {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!isValid(quantityText, "Quantity") || !isValid(minQuantityText, "Min Quantity") || !isValid(maxQuantityText, "Max Quantity")) {
            return;
        }
        int quantity = Integer.parseInt(quantityText);
        int minQuantity = Integer.parseInt(minQuantityText);
        int maxQuantity = Integer.parseInt(maxQuantityText);

        if (minQuantity > maxQuantity) {
            JOptionPane.showMessageDialog(this, "Min quantity cannot be greater than max quantity", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (quantity > maxQuantity) {
            JOptionPane.showMessageDialog(this, "Quantity can not pass the maximum quantity", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String result = inventoryController.updateInventory(Integer.parseInt((String) itemComboBox.getSelectedItem()),
                location, quantity, minQuantity, maxQuantity);
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        inventoryController.getInventoryTable(tableModel);
        populateComboBox();
        formWindowClosing();
    }

    private boolean isValid(String price, String type) {
        try {
            int number = Integer.parseInt(price);
            if (number < 0) {
                JOptionPane.showMessageDialog(this, type + " cannot be negative", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, type + " must be number", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
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

