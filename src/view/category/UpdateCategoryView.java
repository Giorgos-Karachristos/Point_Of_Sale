package view.category;

import controller.category.CategoryController;
import entities.Category;
import util.CustomColorChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UpdateCategoryView extends JDialog {

    private final JComboBox<String>nameComboBox;
    private final JTextField taxTextField;
    private final JButton colorButton;
    private final ButtonGroup buttonGroup1;
    private final JRadioButton noRadioButton;
    private final JRadioButton yesRadioButton;
    private final DefaultComboBoxModel<String> categoryBoxModel;
    private final CategoryController categoryController;

    private List<Category> categoryList;

    public UpdateCategoryView(DefaultTableModel tableModel) {
        categoryController = new CategoryController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Category");
        this.setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 5, 5);
        this.add(nameLabel, gbc);

        nameComboBox = new JComboBox<>();
        nameComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryBoxModel = (DefaultComboBoxModel<String>) nameComboBox.getModel();
        populateComboBox();
        nameComboBox.addActionListener(e -> nameComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 140;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        getContentPane().add(nameComboBox, gbc);

        JLabel taxLabel = new JLabel();
        taxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taxLabel.setText("Tax:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(taxLabel, gbc);

        taxTextField = new JTextField();
        taxTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(taxTextField, gbc);

        colorButton = new JButton();
        colorButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        colorButton.setText("Color");
        colorButton.addActionListener(evt1 -> colorButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(colorButton, gbc);

        JLabel isActiveLabel = new JLabel();
        isActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        isActiveLabel.setText("Is active:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(isActiveLabel, gbc);

        buttonGroup1 = new ButtonGroup();
        yesRadioButton = new JRadioButton();
        buttonGroup1.add(yesRadioButton);
        yesRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        yesRadioButton.setText("Yes");
        yesRadioButton.setActionCommand("Yes");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(yesRadioButton, gbc);

        noRadioButton = new JRadioButton();
        buttonGroup1.add(noRadioButton);
        noRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        noRadioButton.setText("No");
        noRadioButton.setActionCommand("No");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(noRadioButton, gbc);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 9;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 20, 5);
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

    private void colorButtonActionPerformed() {
        CustomColorChooser chooser = new CustomColorChooser(colorButton);
        chooser.setModal(true);
        chooser.setVisible(true);
    }

    private void formWindowClosing() {
        nameComboBox.setSelectedIndex(0);
        taxTextField.setText("");
        colorButton.setBackground(Color.WHITE);
        buttonGroup1.clearSelection();
    }

    private void nameComboBoxActionPerformed() {
        for (Category category : categoryList) {
            if (category.getName().equals(nameComboBox.getSelectedItem())) {
                taxTextField.setText(category.getTax());
                colorButton.setBackground(Color.decode(category.getColor()));
                if (category.getIsActive().equals("Yes")) {
                    yesRadioButton.setSelected(true);
                } else {
                    noRadioButton.setSelected(true);
                }
                break;
            }
        }
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        if (nameComboBox.getSelectedIndex() == 0 || !noRadioButton.isSelected() && !yesRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a category to edit", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double tax;
        String taxText = taxTextField.getText().isBlank() ? "0" : taxTextField.getText();
        try {
            tax = Double.parseDouble(taxText);
            if (tax < 0) {
                JOptionPane.showMessageDialog(this, "Tax cannot be negative", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Tax must be number", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Color color = colorButton.getBackground();
        String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

        String result = categoryController.updateCategory((String) nameComboBox.getSelectedItem(),
                tax, hexColor, buttonGroup1.getSelection().getActionCommand());
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        categoryController.getCategoryTable(tableModel);
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
        categoryList = categoryController.getCategoryList(categoryBoxModel);
    }
}
