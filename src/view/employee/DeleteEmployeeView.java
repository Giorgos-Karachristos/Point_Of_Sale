package view.employee;

import controller.employee.EmployeeController;
import entities.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DeleteEmployeeView extends JDialog {

    private final JComboBox<String> employeeComboBox;
    private final JLabel sqlNameLabel;
    private final JLabel sqlSurnameLabel;
    private final JLabel sqlPhoneLabel;
    private final JLabel sqlEmailLabel;
    private final JLabel sqlRoleLabel;
    private final JLabel sqlHireDateLabel;
    private final JLabel sqlSsnLabel;
    private final JLabel sqlIdnLabel;
    private final JLabel sqlUsernameLabel;
    private final JLabel sqlPasswordLabel;
    private final DefaultComboBoxModel<String> roleBoxModel;
    private final EmployeeController employeeController;

    private List<Employee> employeeList;

    public DeleteEmployeeView(DefaultTableModel tableModel) {
        employeeController = new EmployeeController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Delete Employee");
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel employeeLabel = new JLabel();
        employeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        employeeLabel.setText("Employee ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 5, 5);
        this.add(employeeLabel, gbc);

        employeeComboBox = new JComboBox<>();
        employeeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleBoxModel = (DefaultComboBoxModel<String>) employeeComboBox.getModel();
        populateComboBox();
        employeeComboBox.addActionListener(e -> employeeComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(employeeComboBox, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(deleteButton, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(nameLabel, gbc);

        sqlNameLabel = new JLabel();
        sqlNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlNameLabel, gbc);

        JLabel surnameLabel = new JLabel();
        surnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        surnameLabel.setText("Surname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(surnameLabel, gbc);

        sqlSurnameLabel = new JLabel();
        sqlSurnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlSurnameLabel, gbc);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneLabel.setText("Phone:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(phoneLabel, gbc);

        sqlPhoneLabel = new JLabel();
        sqlPhoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlPhoneLabel, gbc);

        JLabel emailLabel = new JLabel();
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("Email:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(emailLabel, gbc);

        sqlEmailLabel = new JLabel();
        sqlEmailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlEmailLabel, gbc);

        JLabel roleLabel = new JLabel();
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleLabel.setText("Role:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(roleLabel, gbc);

        sqlRoleLabel = new JLabel();
        sqlRoleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlRoleLabel, gbc);

        JLabel hireDateLabel = new JLabel();
        hireDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        hireDateLabel.setText("Hire Date:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(hireDateLabel, gbc);

        sqlHireDateLabel = new JLabel();
        sqlHireDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlHireDateLabel, gbc);

        JLabel ssnLabel = new JLabel();
        ssnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ssnLabel.setText("Social Security Number:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(ssnLabel, gbc);

        sqlSsnLabel = new JLabel();
        sqlSsnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlSsnLabel, gbc);

        JLabel idnLabel = new JLabel();
        idnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idnLabel.setText("Identification Number:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(idnLabel, gbc);

        sqlIdnLabel = new JLabel();
        sqlIdnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlIdnLabel, gbc);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameLabel.setText("Username:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 20, 5);
        this.add(usernameLabel, gbc);

        sqlUsernameLabel = new JLabel();
        sqlUsernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(sqlUsernameLabel, gbc);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Password:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(passwordLabel, gbc);

        sqlPasswordLabel = new JLabel();
        sqlPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 5, 20, 20);
        this.add(sqlPasswordLabel, gbc);

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
        employeeComboBox.setSelectedIndex(0);
        sqlNameLabel.setText("");
        sqlSurnameLabel.setText("");
        sqlPhoneLabel.setText("");
        sqlEmailLabel.setText("");
        sqlRoleLabel.setText("");
        sqlHireDateLabel.setText("");
        sqlSsnLabel.setText("");
        sqlIdnLabel.setText("");
        sqlUsernameLabel.setText("");
        sqlPasswordLabel.setText("");
    }

    private void employeeComboBoxActionPerformed() {
        for (Employee employee : employeeList) {
            if (employee.getEmployeeID().equals(employeeComboBox.getSelectedItem())) {
                sqlNameLabel.setText(employee.getName());
                sqlSurnameLabel.setText(employee.getSurname());
                sqlPhoneLabel.setText(employee.getPhone());
                sqlEmailLabel.setText(employee.getEmail());
                sqlRoleLabel.setText(employee.getRole());
                sqlSsnLabel.setText(employee.getSsn());
                sqlIdnLabel.setText(employee.getId());
                sqlHireDateLabel.setText(employee.getHireDate());
                sqlUsernameLabel.setText(employee.getUsername());
                sqlPasswordLabel.setText(employee.getPassword());
                //repaint
                this.pack();
                break;
            }
        }
    }

    private void deleteButtonActionPerformed(DefaultTableModel tableModel) {
        if (employeeComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String result = employeeController.deleteEmployee(Integer.parseInt((String) employeeComboBox.getSelectedItem()));
        if (result.contains("Delete failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        employeeController.getEmployeeTable(tableModel);
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
        employeeList = employeeController.getEmployeeList(roleBoxModel);
    }
}
