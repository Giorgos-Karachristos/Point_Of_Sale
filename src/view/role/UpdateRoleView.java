package view.role;

import controller.role.RoleController;
import entities.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UpdateRoleView extends JDialog {

    private final JComboBox<String> nameComboBox;
    private final JComboBox<String> accessComboBox;
    private final DefaultComboBoxModel<String> nameBoxModel;
    private final RoleController roleController;

    private List<Role> roleList;

    public UpdateRoleView(DefaultTableModel tableModel) {
        roleController = new RoleController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Role");
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

        nameComboBox = new JComboBox<>();
        nameComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameBoxModel = (DefaultComboBoxModel<String>) nameComboBox.getModel();
        populateComboBox();
        nameComboBox.addActionListener(e -> nameComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 10, 10, 20);
        this.add(nameComboBox, gbc);

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
        DefaultComboBoxModel<String> accessBoxModel = (DefaultComboBoxModel<String>) accessComboBox.getModel();
        roleController.getPOSAccess(accessBoxModel);
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
        nameComboBox.setSelectedIndex(0);
        accessComboBox.setSelectedIndex(0);
    }

    private void nameComboBoxActionPerformed() {
        for (Role role: roleList) {
            if (role.getName().equals(nameComboBox.getSelectedItem())) {
                String roleName = role.getPosAccess();
                for (int x = 0; x <= accessComboBox.getItemCount(); x++) {
                    if (roleName.equals(accessComboBox.getItemAt(x))) {
                        accessComboBox.setSelectedIndex(x);
                        break;
                    }
                }
                break;
            }
        }
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        if (nameComboBox.getSelectedIndex() == 0 || accessComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = roleController.updateRole((String) nameComboBox.getSelectedItem(), (String) accessComboBox.getSelectedItem());
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        roleController.getRoleTable(tableModel);
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
        roleList = roleController.getRoleList(nameBoxModel);
    }
}

