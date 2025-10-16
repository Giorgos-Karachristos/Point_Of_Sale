package view.order;

import controller.order.OrderController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderPanel extends JPanel {

    public OrderPanel(String orderName, String orderSubtotal, String orderStatus,String orderType, String orderDateTime) {
        GridBagConstraints gbc;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());

        JLabel orderIDLabel = new JLabel();
        orderIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderIDLabel.setText("Order ID:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        topPanel.add(orderIDLabel, gbc);

        JLabel sqlOrderIDLabel = new JLabel();
        sqlOrderIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlOrderIDLabel.setText(orderName);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        topPanel.add(sqlOrderIDLabel, gbc);

        JLabel subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtotalLabel.setText("Subtotal:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        topPanel.add(subtotalLabel, gbc);

        JLabel sqlSubtotalLabel = new JLabel();
        sqlSubtotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlSubtotalLabel.setText(orderSubtotal);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        topPanel.add(sqlSubtotalLabel, gbc);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        statusLabel.setText("Status:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        topPanel.add(statusLabel, gbc);

        JLabel sqlStatusLabel = new JLabel();
        sqlStatusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlStatusLabel.setText(orderStatus);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        topPanel.add(sqlStatusLabel, gbc);

        JLabel typeLabel = new JLabel();
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        typeLabel.setText("Type:");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        topPanel.add(typeLabel, gbc);

        JLabel sqlTypeLabel = new JLabel();
        sqlTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlTypeLabel.setText(orderType);
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        topPanel.add(sqlTypeLabel, gbc);

        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dateTimeLabel.setText("Date-Time:");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        topPanel.add(dateTimeLabel, gbc);

        JLabel sqlDateTimeLabel = new JLabel();
        sqlDateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlDateTimeLabel.setText(orderDateTime);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        topPanel.add(sqlDateTimeLabel, gbc);

        this.add(topPanel, BorderLayout.PAGE_START);

        JTable jTable = new JTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTable.setRowHeight(40);

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Item Name", "Quantity", "Status"}
        ) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        jTable.setModel(tableModel);

        OrderController orderController = new OrderController();
        orderController.getKitchenList(tableModel, orderName);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        this.add(jScrollPane, BorderLayout.CENTER);
    }
}

