package view.role;

import controller.role.RoleController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertRoleView extends JDialog {

    private final JTextField nameTextField;
    private final JComboBox<String> accessComboBox;
    private final RoleController roleController;

    public InsertRoleView(DefaultTableModel tableModel) {
        roleController = new RoleController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Add Role");
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 10, 10);
        this.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 10, 10, 20);
        this.add(nameTextField, gbc);

        JLabel accessLabel = new JLabel();
        accessLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accessLabel.setText("Access:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 20, 10, 10);
        this.add(accessLabel, gbc);

        accessComboBox = new JComboBox<>();
        accessComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        DefaultComboBoxModel<String> boxModel = (DefaultComboBoxModel<String>) accessComboBox.getModel();
        roleController.getPOSAccess(boxModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 10, 20);
        this.add(accessComboBox, gbc);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 20, 20, 20);
        this.add(submitButton, gbc);

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
        nameTextField.setText("");
        accessComboBox.setSelectedIndex(0);
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        String roleName = nameTextField.getText();
        if (roleName.isBlank() || accessComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = roleController.insertRole(roleName, (String) accessComboBox.getSelectedItem());
        if (result.contains("Insertion failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        roleController.getRoleTable(tableModel);
        formWindowClosing();
    }
}

