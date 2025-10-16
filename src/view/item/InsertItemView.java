package view.item;

import controller.category.CategoryController;
import controller.item.ItemController;
import util.CustomColorChooser;
import util.CustomDocumentFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InsertItemView  extends JDialog {

    private static final int CHAR_LIMIT = 500;

    private final JTextField codeTextField;
    private final JTextField nameTextField;
    private final JTextArea descriptionTextArea;
    private final JLabel countLabel;
    private final JTextField priceTextField;
    private final JTextField costTextField;
    private final JButton colorButton;
    private final JComboBox<String>categoryComboBox;
    private final ButtonGroup buttonGroup1;
    private final JRadioButton noRadioButton;
    private final JRadioButton yesRadioButton;
    private final DefaultComboBoxModel<String> categoryBoxModel;
    private final ItemController itemController;
    private final CategoryController categoryController;

    public InsertItemView(DefaultTableModel tableModel) {
        itemController = new ItemController();
        categoryController = new CategoryController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Add Item");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        codeLabel.setText("Code:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 5, 5);
        this.add(codeLabel, gbc);

        codeTextField = new JTextField();
        codeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(codeTextField, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(nameTextField, gbc);

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionLabel.setText("Description");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(descriptionLabel, gbc);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(4);
        descriptionTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                descriptionTextAreaKeyTyped();
            }
        });
        CustomDocumentFilter df = new CustomDocumentFilter();
        df.setDocumentFilter(descriptionTextArea, CHAR_LIMIT);

        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(descriptionTextArea);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 50;
        gbc.insets = new Insets(0, 20, 0, 20);
        this.add(jScrollPane1, gbc);

        countLabel = new JLabel();
        countLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        countLabel.setText("Character Count: " + CHAR_LIMIT);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(countLabel, gbc);

        JLabel priceLabel = new JLabel();
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setText("Price:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(priceLabel, gbc);

        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(priceTextField, gbc);

        JLabel costLabel = new JLabel();
        costLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        costLabel.setText("Cost:");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(costLabel, gbc);

        costTextField = new JTextField();
        costTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 10, 5, 20);
        this.add(costTextField, gbc);

        colorButton = new JButton();
        colorButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        colorButton.setText("Color");
        colorButton.addActionListener(evt1 -> colorButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(colorButton, gbc);

        JLabel categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryLabel.setText("Category:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(categoryLabel, gbc);

        categoryComboBox = new JComboBox<>();
        categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryBoxModel = (DefaultComboBoxModel<String>) categoryComboBox.getModel();
        populateComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(categoryComboBox, gbc);

        JLabel isActiveLabel = new JLabel();
        isActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        isActiveLabel.setText("Is active:");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(isActiveLabel, gbc);

        buttonGroup1 = new ButtonGroup();
        yesRadioButton = new JRadioButton();
        buttonGroup1.add(yesRadioButton);
        yesRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        yesRadioButton.setText("Yes");
        yesRadioButton.setActionCommand("Yes");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(yesRadioButton, gbc);

        noRadioButton = new JRadioButton();
        buttonGroup1.add(noRadioButton);
        noRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        noRadioButton.setText("No");
        noRadioButton.setActionCommand("No");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(noRadioButton, gbc);

        JButton submitButton = new JButton();
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> submitButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
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

    private void descriptionTextAreaKeyTyped() {
        int charCount = descriptionTextArea.getDocument().getLength();
        countLabel.setText("Character Count: " + (CHAR_LIMIT - charCount));
    }

    private void colorButtonActionPerformed() {
        CustomColorChooser chooser = new CustomColorChooser(colorButton);
        chooser.setModal(true);
        chooser.setVisible(true);
    }

    private void formWindowClosing() {
        codeTextField.setText("");
        nameTextField.setText("");
        descriptionTextArea.setText("");
        countLabel.setText("Character Count: " + CHAR_LIMIT);
        priceTextField.setText("");
        costTextField.setText("");
        colorButton.setBackground(Color.WHITE);
        categoryComboBox.setSelectedIndex(0);
        buttonGroup1.clearSelection();
    }

    private void submitButtonActionPerformed(DefaultTableModel tableModel) {
        String code = codeTextField.getText().trim();
        String name = nameTextField.getText().trim();
        String costText = costTextField.getText().trim();
        String priceText = priceTextField.getText().isBlank() ? "0" : priceTextField.getText().trim();

        if (code.isBlank() || name.isBlank() || costText.isBlank() || categoryComboBox.getSelectedIndex() == 0
                || !noRadioButton.isSelected() && !yesRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "All the fields are required", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!isValid(costText, "Cost") || !isValid(priceText, "Price")) {
            return;
        }
        double cost = Double.parseDouble(costText);
        double price = Double.parseDouble(priceText);

        Color color = colorButton.getBackground();
        String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

        String result = itemController.insertItem(code, name, descriptionTextArea.getText().trim(),
                price, cost, (String) categoryComboBox.getSelectedItem(),
                hexColor, buttonGroup1.getSelection().getActionCommand());
        if (result.contains("Insertion failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        itemController.getItemTable(tableModel);
        formWindowClosing();
    }

    private boolean isValid(String price, String type) {
        try {
            double number = Double.parseDouble(price);
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
        categoryController.getCategoryName(categoryBoxModel);
    }
}
