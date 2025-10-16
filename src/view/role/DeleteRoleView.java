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

public class DeleteRoleView extends JDialog {

    private final JComboBox<String> nameComboBox;
    private final JLabel sqlAccessLabel;
    private final JTextArea jTextArea;
    private final DefaultComboBoxModel<String> nameBoxModel;
    private final RoleController roleController;

    private List<Role> roleList;

    public DeleteRoleView(DefaultTableModel tableModel) {
        roleController = new RoleController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Delete Role");
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0};
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
        nameComboBox.addActionListener(evt1 -> nameComboBoxActionPerformed());
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

        sqlAccessLabel = new JLabel();
        sqlAccessLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(sqlAccessLabel, gbc);

        jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setOpaque(false);
        jTextArea.setEditable(false);
        jTextArea.setFocusable(false);
        jTextArea.setBorder(null);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);
        this.add(jTextArea, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 20, 20, 20);
        this.add(deleteButton, gbc);

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
        sqlAccessLabel.setText("");
        jTextArea.setText("");
    }

    private void nameComboBoxActionPerformed() {
        for (Role role: roleList) {
            if (role.getName().equals(nameComboBox.getSelectedItem())) {
                sqlAccessLabel.setText(role.getPosAccess());
                if (Integer.parseInt(role.getCountEmployees()) > 0) {
                    jTextArea.setText("Deleting this role will affect " + role.getCountEmployees() + " employees. Their roles will be set to empty.");
                } else {
                    jTextArea.setText("");
                }
                this.pack();
                break;
            }
        }
    }

    private void deleteButtonActionPerformed(DefaultTableModel tableModel) {
        if (nameComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a role to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = roleController.deleteRole((String) nameComboBox.getSelectedItem());
        if (result.contains("Delete failed: ")) {
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
