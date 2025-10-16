package view.restaurant;

import controller.restaurant.RestaurantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RestaurantView extends JPanel {

    private final JTextField nameTextField;
    private final JTextField addressTextField;
    private final JTextField phoneTextField;
    private final JTextField tablesTextField;
    private final RestaurantController restaurantController;

    public RestaurantView() {
        restaurantController = new RestaurantController();
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(250, 188, 63));

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(nameLabel, gbc);

        String[] res = restaurantController.getRestaurant();

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameTextField.setText(res[0]);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(nameTextField, gbc);

        JLabel addressLabel = new JLabel();
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        addressLabel.setText("Address:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(addressLabel, gbc);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addressTextField.setText(res[1]);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(addressTextField, gbc);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        phoneLabel.setText("Phone:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(phoneLabel, gbc);

        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneTextField.setText(res[2]);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(phoneTextField, gbc);

        JLabel tableLabel = new JLabel();
        tableLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tableLabel.setText("Tables:");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(tableLabel, gbc);

        tablesTextField = new JTextField();
        tablesTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tablesTextField.setText(res[3]);
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(tablesTextField, gbc);

        JButton saveButton = new JButton();
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setBackground(new Color(107, 203, 119));
        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        saveButton.setText("Save");
        saveButton.addActionListener((ActionEvent evt) -> saveButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(saveButton, gbc);
    }

    private void saveButtonActionPerformed() {
        if (!nameTextField.getText().isBlank()
                && !addressTextField.getText().isBlank()
                && !phoneTextField.getText().isBlank()
                && !tablesTextField.getText().isBlank()) {

            String result = restaurantController.updateRestaurant(
                    nameTextField.getText(),
                    addressTextField.getText(),
                    phoneTextField.getText(),
                    Integer.parseInt(tablesTextField.getText()));
            if (result.contains("Update failed: ")) {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

