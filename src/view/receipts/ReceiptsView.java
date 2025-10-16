package view.receipts;

import controller.receipts.ReceiptController;
import entities.Order;
import util.ExportPanelToImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptsView extends JPanel {

    private static final Logger LOGGER = Logger.getLogger(ReceiptsView.class.getName());
    private final JTabbedPane jTabbedPane;
    private final ReceiptController receiptController;
    private final List<Order> orderList;

    public ReceiptsView() {
        receiptController = new ReceiptController();
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());

        jTabbedPane = new JTabbedPane();
        jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        jTabbedPane.putClientProperty("JTabbedPane.showTabSeparators", true);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane.setBackground(Color.white);
        jTabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTabbedPane.setPreferredSize(new Dimension(600, 100));

        jTabbedPane.removeAll();
        orderList = receiptController.getFinalizedOrdersList();
        updateTabbedPane(orderList);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(100, 0, 0, 100);
        this.add(jTabbedPane, gbc);

        JButton refundButton = new JButton();
        refundButton.setBackground(new Color(238, 78, 78));
        refundButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        refundButton.setText("Refund Order");
        refundButton.addActionListener((ActionEvent evt) -> refundButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(100, 0, 15, 100);
        this.add(refundButton, gbc);

        JButton saveReceiptButton = new JButton();
        saveReceiptButton.setBackground(new Color(63, 169, 245));
        saveReceiptButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        saveReceiptButton.setText("Save Receipt");
        saveReceiptButton.addActionListener((ActionEvent evt) -> saveReceiptButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(15, 0, 15, 100);
        this.add(saveReceiptButton, gbc);

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener((ActionEvent evt) -> refreshButtonActionPerformed(true));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(15, 0, 15, 100);
        this.add(refreshButton, gbc);
    }

    private void updateTabbedPane(List<Order> orderList) {
        ReceiptPanel rp;
        for (Order orders : orderList) {
            String orderID = orders.getOrderID();
            String orderName = orders.getName();
            String orderSubtotal = orders.getSubtotal();
            String orderDiscount = orders.getDiscount();
            String orderTip = orders.getTip();
            String orderTotal = orders.getTotal();
            String date = orders.getOrderDate().substring(0, 11);
            String time = orders.getOrderDate().substring(11);
            String status = orders.getStatus();

            rp = new ReceiptPanel(orderID, orderName, orderSubtotal, orderDiscount, orderTip, orderTotal, date, time);

            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setPreferredSize(new Dimension(450, 250));
            jScrollPane.setViewportView(rp);

            if ("Refunt".equals(status)) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new OverlayLayout(jPanel));

                JLabel refundLabel = new JLabel();
                refundLabel.setFont(new Font("Segoe UI", Font.PLAIN, 70));
                refundLabel.setForeground(new java.awt.Color(204, 0, 51));
                refundLabel.setText("Refund");
                refundLabel.setAlignmentX(0.5F);
                refundLabel.setOpaque(false);
                jPanel.add(refundLabel);

                jPanel.add(jScrollPane);

                jTabbedPane.addTab("Order " + orderID + " Refund", jPanel);
            } else {
                jTabbedPane.addTab("Order " + orderID, jScrollPane);
            }
        }
    }

    private void refundButtonActionPerformed() {
        int selectedTabIndex = jTabbedPane.getSelectedIndex();

        if (selectedTabIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] words = jTabbedPane.getTitleAt(selectedTabIndex).split(" ");
        int orderID = Integer.parseInt(words[1]);

        String result = receiptController.refundOrder(orderID);
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        refreshButtonActionPerformed(true);
        jTabbedPane.setSelectedIndex(selectedTabIndex);
    }

    private void saveReceiptButtonActionPerformed() {
        int selectedTabIndex = jTabbedPane.getSelectedIndex();
        if (selectedTabIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] words = jTabbedPane.getTitleAt(selectedTabIndex).split(" ");

        JPanel selectedPanel;
        try{
            JScrollPane scrollPane = (JScrollPane) jTabbedPane.getComponentAt(selectedTabIndex);
            selectedPanel = (JPanel) scrollPane.getViewport().getView();
        } catch (Exception e){
            selectedPanel = (JPanel) jTabbedPane.getComponentAt(selectedTabIndex);
        }


        try {
            ExportPanelToImage.exportPanelToImage(selectedPanel, "receipt" + words[1] + ".png");
            JOptionPane.showMessageDialog(this, "Image file exported successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "ExportPanelToImage Exception", ex);
            JOptionPane.showMessageDialog(this, "Failed to export image file.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void refreshButtonActionPerformed(boolean force) {
        int currentSize = orderList.size();
        List<Order> newOrderList = receiptController.getFinalizedOrdersList();

        if (newOrderList.size() != currentSize || force) {
            jTabbedPane.removeAll();
            updateTabbedPane(newOrderList);
        }
    }
}

