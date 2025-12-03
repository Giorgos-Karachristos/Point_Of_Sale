package view.order;

import controller.order.OrderController;
import entities.Order;
import view.register.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class OrdersView extends JPanel {

    private final JTabbedPane jTabbedPane;
    private final OrderController orderController;
    private final List<Order> orderList;

    public OrdersView(JTabbedPane jtp, RegisterView registerView) {
        orderController = new OrderController();
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());

        jTabbedPane = new JTabbedPane();
        jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        jTabbedPane.putClientProperty("JTabbedPane.showTabSeparators", true);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane.setBackground(Color.white);
        jTabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTabbedPane.setPreferredSize(new Dimension(500, 100));

        jTabbedPane.removeAll();
        orderList = orderController.getActiveOrdersList();
        updateTabbedPane(orderList);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(100, 0, 0, 100);
        this.add(jTabbedPane, gbc);

        JButton voidButton = new JButton();
        voidButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        voidButton.setBackground(new Color(238, 78, 78));
        voidButton.setText("Void Selected Item");
        voidButton.addActionListener((ActionEvent evt) -> voidButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(100, 0, 15, 100);
        this.add(voidButton, gbc);

        JButton payButton = new JButton();
        payButton.setBackground(new Color(107, 203, 119));
        payButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        payButton.setText("Pay Order");
        payButton.addActionListener((ActionEvent evt) -> payButtonActionPerformed(jtp, registerView));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 0, 15, 100);
        this.add(payButton, gbc);

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener((ActionEvent evt) -> refreshButtonActionPerformed(true));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 0, 15, 100);
        this.add(refreshButton, gbc);

        JButton cancelButton = new JButton();
        cancelButton.setBackground(new Color(238, 78, 78));
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cancelButton.setText("Cancel Order");
        cancelButton.addActionListener((ActionEvent evt) -> cancelButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(15, 0, 0, 100);
        this.add(cancelButton, gbc);
    }

    private void updateTabbedPane(List<Order> orderList) {
        OrderPanel op;
        for (Order orders : orderList) {
            String orderID = orders.getOrderID();
            String orderSubtotal = orders.getSubtotal() + " $";
            String orderStatus = orders.getStatus();
            String typeStatus = orders.getType();
            String orderDateTime = orders.getOrderDate();

            op = new OrderPanel(orderID, orderSubtotal, orderStatus, typeStatus, orderDateTime);
            jTabbedPane.addTab("Order " + orderID + " " + orderStatus, op);
        }
    }

    private void voidButtonActionPerformed() {
        int selectedTabIndex = jTabbedPane.getSelectedIndex();
        if (selectedTabIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] words = jTabbedPane.getTitleAt(selectedTabIndex).split(" ");
        int orderID = Integer.parseInt(words[1]);

        JPanel selectedPanel = (JPanel) jTabbedPane.getComponentAt(selectedTabIndex);
        JScrollPane scrollPane = (JScrollPane) selectedPanel.getComponent(1);
        JTable jTable1 = (JTable) scrollPane.getViewport().getView();

        int selectedRowIndex = jTable1.getSelectedRow();

        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(this, "No item selected in the order.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String itemName = jTable1.getValueAt(selectedRowIndex, 0).toString();

        String result = orderController.voidKitchen(orderID, itemName);
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        refreshButtonActionPerformed(true);
        jTabbedPane.setSelectedIndex(selectedTabIndex);
    }

    private void payButtonActionPerformed(JTabbedPane jtp, RegisterView r) {
        int selectedTabIndex = jTabbedPane.getSelectedIndex();

        if (selectedTabIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] words = jTabbedPane.getTitleAt(selectedTabIndex).split(" ");
        int orderID = Integer.parseInt(words[1]);

        jtp.setSelectedIndex(2);
        r.setOrderID(orderID);
    }

    public void refreshButtonActionPerformed(boolean force) {
        int currentSize = orderList.size();
        List<Order> newOrderList = orderController.getActiveOrdersList();

        if (newOrderList.size() != currentSize || force) {
            jTabbedPane.removeAll();
            updateTabbedPane(newOrderList);
        }
    }

    private void cancelButtonActionPerformed() {
        int selectedTabIndex = jTabbedPane.getSelectedIndex();

        if (selectedTabIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] words = jTabbedPane.getTitleAt(selectedTabIndex).split(" ");
        int orderID = Integer.parseInt(words[1]);

        String result = orderController.cancelOrder(orderID);
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        refreshButtonActionPerformed(true);
    }
}

