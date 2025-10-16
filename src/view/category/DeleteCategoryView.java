package view.category;

import controller.category.CategoryController;
import entities.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DeleteCategoryView extends JDialog {

    private final JComboBox<String>nameComboBox;
    private final JLabel sqlTaxLabel;
    private final JLabel sqlColorLabel;
    private final JLabel sqlIsActiveLabel;
    private final JTextArea jTextArea;
    private final DefaultComboBoxModel<String> categoryBoxModel;
    private final CategoryController categoryController;

    private List<Category> categoryList;

    public DeleteCategoryView(DefaultTableModel tableModel) {
        categoryController = new CategoryController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Delete Category");
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
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
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        getContentPane().add(nameComboBox, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(deleteButton, gbc);

        JLabel taxLabel = new JLabel();
        taxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taxLabel.setText("Tax:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(taxLabel, gbc);

        sqlTaxLabel = new JLabel();
        sqlTaxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlTaxLabel, gbc);

        JLabel colorLabel = new JLabel();
        colorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        colorLabel.setText("Color:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(colorLabel, gbc);

        sqlColorLabel = new JLabel();
        sqlColorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlColorLabel.setText("1234567");
        sqlColorLabel.setForeground(this.getBackground());
        sqlColorLabel.setBackground(this.getBackground());
        sqlColorLabel.setOpaque(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlColorLabel, gbc);

        JLabel isActiveLabel = new JLabel();
        isActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        isActiveLabel.setText("Is active:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(isActiveLabel, gbc);

        sqlIsActiveLabel = new JLabel();
        sqlIsActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlIsActiveLabel, gbc);

        jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setOpaque(false);
        jTextArea.setEditable(false);
        jTextArea.setFocusable(false);
        jTextArea.setBorder(null);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 20, 20);
        this.add(jTextArea, gbc);

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
        sqlTaxLabel.setText("");
        sqlColorLabel.setForeground(this.getBackground());
        sqlColorLabel.setBackground(this.getBackground());
        sqlIsActiveLabel.setText("");
        jTextArea.setText("");
    }

    private void nameComboBoxActionPerformed() {
        for (Category category : categoryList) {
            if (category.getName().equals(nameComboBox.getSelectedItem())) {
                sqlTaxLabel.setText(category.getTax());
                sqlColorLabel.setForeground(Color.decode(category.getColor()));
                sqlColorLabel.setBackground(Color.decode(category.getColor()));
                sqlIsActiveLabel.setText(category.getIsActive());
                if (Integer.parseInt(category.getCountItems()) > 0) {
                    jTextArea.setText("Deleting this category will affect " + category.getCountItems() + " items."
                            + "\nTheir categories will be set to empty.");
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
            JOptionPane.showMessageDialog(this, "Please select a category to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = categoryController.deleteCategory((String) nameComboBox.getSelectedItem());
        if (result.contains("Delete failed: ")) {
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
