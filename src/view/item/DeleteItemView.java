package view.item;

import controller.item.ItemController;
import entities.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class DeleteItemView extends JDialog {

    private final JComboBox<String> itemComboBox;
    private final JLabel sqlCodeLabel;
    private final JLabel sqlNameLabel;
    private final JTextArea descriptionTextArea;
    private final JLabel sqlPriceLabel;
    private final JLabel sqlCostLabel;
    private final JLabel sqlColorLabel;
    private final JLabel sqlDateLabel;
    private final JLabel sqlCategoryLabel;
    private final JLabel sqlIsActiveLabel;
    private final DefaultComboBoxModel<String> itemBoxModel;
    private final ItemController itemController;

    private List<Item> itemList;

    public DeleteItemView(DefaultTableModel tableModel) {
        itemController = new ItemController();
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Delete Item");
        this.setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[]{0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        this.setLayout(layout);

        JLabel itemLabel = new JLabel();
        itemLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemLabel.setText("Item ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(20, 20, 5, 5);
        this.add(itemLabel, gbc);

        itemComboBox = new JComboBox<>();
        itemComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        itemBoxModel = (DefaultComboBoxModel<String>) itemComboBox.getModel();
        populateComboBox();
        itemComboBox.addActionListener(e -> itemComboBoxActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        this.add(itemComboBox, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener((ActionEvent evt) -> deleteButtonActionPerformed(tableModel));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 5, 5, 20);
        this.add(deleteButton, gbc);

        JLabel codeLabel = new JLabel();
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        codeLabel.setText("Code:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(codeLabel, gbc);

        sqlCodeLabel = new JLabel();
        sqlCodeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlCodeLabel, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(nameLabel, gbc);

        sqlNameLabel = new JLabel();
        sqlNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlNameLabel, gbc);

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionLabel.setText("Description");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(descriptionLabel, gbc);

        JScrollPane jScrollPane1 = new JScrollPane();
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(4);
        jScrollPane1.setViewportView(descriptionTextArea);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 50;
        gbc.insets = new Insets(0, 20, 0, 20);
        this.add(jScrollPane1, gbc);

        JLabel priceLabel = new JLabel();
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setText("Price:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(priceLabel, gbc);

        sqlPriceLabel = new JLabel();
        sqlPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlPriceLabel, gbc);

        JLabel costLabel = new JLabel();
        costLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        costLabel.setText("Cost:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(costLabel, gbc);

        sqlCostLabel = new JLabel();
        sqlCostLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlCostLabel, gbc);

        JLabel colorLabel = new JLabel();
        colorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        colorLabel.setText("Color:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 5, 5);
        this.add(colorLabel, gbc);

        sqlColorLabel = new JLabel();
        sqlColorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlColorLabel.setText("1234567");
        sqlColorLabel.setForeground(this.getBackground());
        sqlColorLabel.setBackground(this.getBackground());
        sqlColorLabel.setOpaque(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(sqlColorLabel, gbc);

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dateLabel.setText("Create at:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(dateLabel, gbc);

        sqlDateLabel = new JLabel();
        sqlDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 5, 5, 20);
        this.add(sqlDateLabel, gbc);

        JLabel categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryLabel.setText("Category:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 20, 20, 5);
        this.add(categoryLabel, gbc);

        sqlCategoryLabel = new JLabel();
        sqlCategoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(sqlCategoryLabel, gbc);

        JLabel isActiveLabel = new JLabel();
        isActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        isActiveLabel.setText("Is active:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 20, 5);
        this.add(isActiveLabel, gbc);

        sqlIsActiveLabel = new JLabel();
        sqlIsActiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 12;
        gbc.insets = new Insets(5, 5, 20, 20);
        this.add(sqlIsActiveLabel, gbc);

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
        descriptionTextArea.setText("");
        sqlPriceLabel.setText("");
        sqlCostLabel.setText("");
        sqlColorLabel.setForeground(this.getBackground());
        sqlColorLabel.setBackground(this.getBackground());
        sqlDateLabel.setText("");
        sqlCategoryLabel.setText("");
        sqlIsActiveLabel.setText("");
    }

    private void itemComboBoxActionPerformed() {
        for (Item item : itemList) {
            if (item.getItemId().equals(itemComboBox.getSelectedItem())) {
                sqlCodeLabel.setText(item.getItemCode());
                sqlNameLabel.setText(item.getName());
                descriptionTextArea.setText(item.getDescription());
                sqlPriceLabel.setText(item.getPrice());
                sqlCostLabel.setText(item.getCost());
                sqlCategoryLabel.setText(item.getCategory());
                sqlDateLabel.setText(item.getCreatedAt());
                sqlColorLabel.setForeground(Color.decode(item.getColor()));
                sqlColorLabel.setBackground(Color.decode(item.getColor()));
                sqlIsActiveLabel.setText(item.getIsActive());
                //repaint
                this.pack();
                break;
            }
        }
    }

    private void deleteButtonActionPerformed(DefaultTableModel tableModel) {
        if (itemComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = itemController.deleteItem(Integer.parseInt((String) itemComboBox.getSelectedItem()));
        if (result.contains("Delete failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        itemController.getItemTable(tableModel);
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
        itemList = itemController.getItemList(itemBoxModel);
    }
}
