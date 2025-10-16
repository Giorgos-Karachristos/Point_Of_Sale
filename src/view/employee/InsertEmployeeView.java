package view.employee;

import controller.employee.EmployeeController;
import controller.role.RoleController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertEmployeeView extends JDialog {

    private final JTextField nameTextField;
    private final JTextField surnameTextField;
    private final JTextField phoneTextField;
    private final JTextField emailTextField;
    private final JTextField ssnTextField;
    private final JComboBox<String> roleComboBox;
    private final JTextField idnTextField;
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;
    private final DefaultComboBoxModel<String> roleBoxModel;
    private final EmployeeController employeeController;
    private final RoleController roleController;

    public InsertEmployeeView(DefaultTableModel tableModel) {
        employeeController = new EmployeeController();
        roleController = new RoleController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Add Employee");
        this.setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 0);
        this.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 0, 0, 0);
        this.add(nameTextField, gbc);

        JLabel surnameLabel = new JLabel();
        surnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        surnameLabel.setText("Surname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        this.add(surnameLabel, gbc);

        surnameTextField = new JTextField();
        surnameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 0, 0, 20);
        this.add(surnameTextField, gbc);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneLabel.setText("Phone:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.add(phoneLabel, gbc);

        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        this.add(phoneTextField, gbc);

        JLabel emailLabel = new JLabel();
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("Email:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 2;
        this.add(emailLabel, gbc);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 2;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 0, 20);
        this.add(emailTextField, gbc);

        JLabel roleLabel = new JLabel();
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleLabel.setText("Role:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        this.add(roleLabel, gbc);

        roleComboBox = new JComboBox<>();
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        roleBoxModel = (DefaultComboBoxModel<String>) roleComboBox.getModel();
        populateComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        this.add(roleComboBox, gbc);

        JLabel ssnLabel = new JLabel();
        ssnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ssnLabel.setText("Social Security Number:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.add(ssnLabel, gbc);

        ssnTextField = new JTextField();
        ssnTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        this.add(ssnTextField, gbc);

        JLabel idnLabel = new JLabel();
        idnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idnLabel.setText("Identification Number:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 6;
        this.add(idnLabel, gbc);

        idnTextField = new JTextField();
        idnTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 6;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 0, 20);
        this.add(idnTextField, gbc);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameLabel.setText("Username:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.add(usernameLabel, gbc);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        this.add(usernameTextField, gbc);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Password:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 8;
        this.add(passwordLabel, gbc);

        passwordTextField = new JTextField();
        passwordTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 8;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 0, 20);
        this.add(passwordTextField, gbc);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 0, 20, 0);
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
        surnameTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        roleComboBox.setSelectedIndex(0);
        ssnTextField.setText("");
        idnTextField.setText("");
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        String name = nameTextField.getText().trim();
        String surname = surnameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String ssn = ssnTextField.getText().trim();
        String idn = idnTextField.getText().trim();
        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        if (name.isBlank() || surname.isBlank() || phone.isBlank() || email.isBlank() || roleComboBox.getSelectedIndex() == 0
                || ssn.isBlank() || idn.isBlank() || username.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = employeeController.insertEmployee(name, surname, phone, email, (String) roleComboBox.getSelectedItem(),
                ssn, idn, username, password);
        if (result.contains("Insertion failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        employeeController.getEmployeeTable(tableModel);
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
        roleController.getRoleName(roleBoxModel);
    }
}

