package view.menu;

import controller.menu.MenuController;
import entities.Category;
import entities.Item;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuView extends JPanel {
    private static final int LONG_CLICK_DELAY = 500;

    private final DefaultTableModel tableModel;
    private final JTable jTable;
    private final JLabel numberSubtotalLabel;
    private final Map<String, Double> taxMap = new HashMap<>();
    private final Map<String, Integer> idMap = new HashMap<>();

    private final MenuController menuController;
    private Timer longClickTimer;
    private boolean longClickTriggered = false;

    public MenuView(String name, String surname, JTabbedPane jp) {
        menuController = new MenuController();
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());

        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        jTabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTabbedPane.setPreferredSize(new Dimension(500, 100));

        List<Category> categoryList = menuController.getCategoryList();
        int tab = 0;
        for (Category category : categoryList) {
            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            categoryPanel.setBackground(Color.WHITE);

            String categoryName = category.getName();
            String categoryColor = category.getColor();
            Double categoryTax = Double.valueOf(category.getTax());

            List<Item> itemData = menuController.getItemsList(categoryName);
            for (Item item : itemData) {
                String itemName = item.getName();
                String itemColor = item.getColor();
                double itemPrice = Double.parseDouble(item.getPrice());
                int itemID = Integer.parseInt(item.getItemId());
                String itemDescription = item.getDescription();

                taxMap.put(itemName, categoryTax);
                idMap.put(itemName, itemID);

                double priceTax = itemPrice + itemPrice * taxMap.get(itemName) / 100;
                BigDecimal bd = new BigDecimal(priceTax);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                double roundedValue = bd.doubleValue();

                JButton itemButton = new JButton(itemName) {
                    @Override
                    public Dimension getPreferredSize() {
                        Dimension preferredSize = super.getPreferredSize();
                        preferredSize.height = 60;
                        return preferredSize;
                    }
                };
                itemButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                itemButton.setBackground(Color.decode(itemColor));
                itemButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        longClickTriggered = false;
                        if (itemDescription != null) {
                            longClickTimer = new Timer(LONG_CLICK_DELAY, (ActionEvent evt) -> {
                                longClickTriggered = true;

                                DescriptionDialog DescriptionDialog = new DescriptionDialog(itemName, itemDescription, String.valueOf(roundedValue));
                                DescriptionDialog.setModal(true);
                                DescriptionDialog.setVisible(true);
                            });
                            longClickTimer.setRepeats(false); // Ensure it only fires once
                            longClickTimer.start();
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (longClickTimer != null) {
                            longClickTimer.stop();
                        }
                        if (!longClickTriggered) {
                            itemButtonActionPerformed(itemName, itemPrice);
                        }
                    }
                });
                categoryPanel.add(itemButton);
            }

            jTabbedPane.addTab(categoryName, categoryPanel);
            jTabbedPane.setBackgroundAt(tab++, Color.decode(categoryColor));
        }

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(100, 0, 0, 0);
        this.add(jTabbedPane, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setBackground(new Color(238, 78, 78));
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(evt1 -> deleteButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(100, 10, 0, 10);
        this.add(deleteButton, gbc);

        JButton deleteAllButton = new JButton();
        deleteAllButton.setBackground(new Color(238, 78, 78));
        deleteAllButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deleteAllButton.setText("Delete All");
        deleteAllButton.addActionListener((ActionEvent evt) -> deleteAllButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        this.add(deleteAllButton, gbc);

        jTable = new JTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTable.setRowHeight(40);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Item Name", "Quantity", "Price (incl. tax)", "Total Price (incl. tax)"}) {
            final boolean[] canEdit = new boolean[]{false, true, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tableModel.addTableModelListener(this::tableChanged);

        jTable.setModel(tableModel);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(100, 0, 0, 0);
        this.add(jScrollPane, gbc);

        JLabel subtotalAmountLabel = new JLabel();
        subtotalAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtotalAmountLabel.setText("Subtotal (incl. tax):");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(100, 50, 15, 15);
        this.add(subtotalAmountLabel, gbc);

        numberSubtotalLabel = new JLabel();
        numberSubtotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        numberSubtotalLabel.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 15, 15, 50);
        this.add(numberSubtotalLabel, gbc);

        JButton kitchenButton = new JButton();
        kitchenButton.addActionListener((ActionEvent evt) -> kitchenButtonActionPerformed(name, surname, jp));
        kitchenButton.setBackground(new Color(107, 203, 119));
        kitchenButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        kitchenButton.setText("Sent to kitchen");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 25;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(0, 50, 0, 50);
        this.add(kitchenButton, gbc);

    }

    private static final int ITEM_NAME_COL = 0;
    private static final int QUANTITY_COL = 1;
    private static final int PRICE_COL = 2;
    private static final int TOTAL_PRICE_COL = 3;

    private void itemButtonActionPerformed(String itemName, Double itemPrice) {
        int row;
        int rowCount = tableModel.getRowCount();
        // Check if the item already exists in the table and increase its quantity by 1 if found
        for (row = 0; row < rowCount; row++) {
            if (tableModel.getValueAt(row, ITEM_NAME_COL).equals(itemName)) {
                int result = 0;
                Object quantity = tableModel.getValueAt(row, QUANTITY_COL);
                if (quantity.getClass() == String.class) {
                    result = Integer.parseInt((String) quantity);
                } else if (quantity.getClass() == Integer.class) {
                    result = (int) quantity;
                }
                tableModel.setValueAt((result + 1), row, QUANTITY_COL);
                break;
            }
        }
        // If the item doesn't exist in the table, add it as a new row
        if (row == rowCount) {
            double priceTax = itemPrice + itemPrice * taxMap.get(itemName) / 100;

            BigDecimal bd = new BigDecimal(priceTax);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double roundedValue = bd.doubleValue();

            tableModel.addRow(new Object[]{itemName, null, roundedValue, roundedValue});
            tableModel.setValueAt(1, tableModel.getRowCount() - 1, QUANTITY_COL);
        }
    }

    private void deleteButtonActionPerformed() {
        int row = jTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        tableModel.removeRow(row);
        updateSubtotal();
    }

    private void deleteAllButtonActionPerformed() {
        tableModel.setRowCount(0);
        numberSubtotalLabel.setText("0.0");
    }

    private void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (column == QUANTITY_COL) {
            double price = Double.parseDouble(tableModel.getValueAt(row, PRICE_COL).toString());
            Object itemName = tableModel.getValueAt(row, ITEM_NAME_COL);
            Object quantityValue = tableModel.getValueAt(row, QUANTITY_COL);
            if (quantityValue.getClass() == String.class) {
                if (quantityValue.toString().isBlank()) {
                    JOptionPane.showMessageDialog(this, "Quantity must be not empty for the item " + itemName, "Validation Error", JOptionPane.WARNING_MESSAGE);
                    tableModel.setValueAt(0, row, QUANTITY_COL);
                    return;
                }
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityValue.toString());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be more than zero for the item " + itemName, "Validation Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(this, "Quantity must be number for the item " + itemName, "Validation Error", JOptionPane.WARNING_MESSAGE);
                    tableModel.setValueAt(0, row, QUANTITY_COL);
                    return;
                }
                price *= quantity;
            } else if (quantityValue.getClass() == Integer.class) {
                price *= (int) quantityValue;
            }

            BigDecimal bd = new BigDecimal(price);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double roundedValue = bd.doubleValue();

            tableModel.setValueAt(roundedValue, row, TOTAL_PRICE_COL);
        }
        if (column == TOTAL_PRICE_COL) {
            updateSubtotal();
        }
    }

    private void updateSubtotal() {
        double subtotal = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            subtotal += (Double) tableModel.getValueAt(i, TOTAL_PRICE_COL);
        }
        BigDecimal bd = new BigDecimal(subtotal);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        numberSubtotalLabel.setText(Double.toString(roundedValue));
    }

    private void kitchenButtonActionPerformed(String name, String surname, JTabbedPane jtp) {
        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Please add items into the order", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String result = menuController.insertOrder(name + " " + surname, numberSubtotalLabel.getText(), "Takeaway");
        if (result.contains("Insertion failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int failed = 0;
        for (int row = 0; row < rowCount; row++) {
            String itemName = tableModel.getValueAt(row, ITEM_NAME_COL).toString();
            int quantity = Integer.parseInt(tableModel.getValueAt(row, QUANTITY_COL).toString());
            double total = Double.parseDouble(tableModel.getValueAt(row, TOTAL_PRICE_COL).toString());

            result = menuController.insertKitchen(idMap.get(itemName), quantity, total);
            if (result.contains("Insertion failed: ")) {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                failed++;
            }
        }
        JOptionPane.showMessageDialog(this, rowCount - failed + " items added successfully.\n" + failed + " items failed.", "Order Results", JOptionPane.INFORMATION_MESSAGE);
        jtp.setSelectedIndex(1);
        deleteAllButtonActionPerformed();
    }
}
