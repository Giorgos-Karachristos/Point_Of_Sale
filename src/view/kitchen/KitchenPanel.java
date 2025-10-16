package view.kitchen;

import controller.kitchen.KitchenController;
import entities.KitchenItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitchenPanel extends JPanel {

    private final KitchenController kitchenController;
    private final Map<String, ArrayList<JCheckBox>> map = new HashMap<>();

    public KitchenPanel(String orderID, String orderStatus, String orderDateTime) {
        kitchenController = new KitchenController();
        GridBagConstraints gbc;

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        JPanel orderTopPanel = new JPanel();
        orderTopPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        orderTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel orderIDLabel = new JLabel();
        orderIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderIDLabel.setText("Order ID:");
        orderTopPanel.add(orderIDLabel);

        JLabel sqlOrderIDLabel = new JLabel();
        sqlOrderIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlOrderIDLabel.setText(orderID);
        orderTopPanel.add(sqlOrderIDLabel);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        statusLabel.setText("Status:");
        orderTopPanel.add(statusLabel);

        JLabel sqlStatusLabel = new JLabel();
        sqlStatusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlStatusLabel.setText(orderStatus);
        orderTopPanel.add(sqlStatusLabel);

        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dateTimeLabel.setText("Date-Time:");
        orderTopPanel.add(dateTimeLabel);

        JLabel sqlDateTimeLabel = new JLabel();
        sqlDateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sqlDateTimeLabel.setText(orderDateTime);
        orderTopPanel.add(sqlDateTimeLabel);

        this.add(orderTopPanel, BorderLayout.PAGE_START);

        List<KitchenItem> kitchenItemList = kitchenController.getCookList(orderID);
        JPanel orderCenterPanel = new JPanel();
        orderCenterPanel.setLayout(new GridBagLayout());

        ArrayList<JCheckBox> boxes = new ArrayList<>();
        int gridy = 0;
        for (KitchenItem kitchenItem : kitchenItemList) {
            JLabel nameLabel = new JLabel();
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nameLabel.setText(kitchenItem.getName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            orderCenterPanel.add(nameLabel, gbc);

            JLabel quantityLabel = new JLabel();
            quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            quantityLabel.setText(String.valueOf(kitchenItem.getQuantity()));
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = gridy;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            orderCenterPanel.add(quantityLabel, gbc);

            JCheckBox jCheckBox1 = new JCheckBox();
            jCheckBox1.setName(String.valueOf(kitchenItem.getItemID()));
            if ("Completed".equals(kitchenItem.getStatus())) {
                jCheckBox1.setSelected(true);
            }
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = gridy;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            orderCenterPanel.add(jCheckBox1, gbc);
            if (!"Completed".equals(kitchenItem.getStatus())) {
                boxes.add(jCheckBox1);
            }
            gridy++;
        }
        map.put(orderID, boxes);

        JScrollPane jScrollPane = new JScrollPane(orderCenterPanel);
        this.add(jScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout());

        JButton servedButton = new JButton();
        servedButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        servedButton.setText("Mark as Served");
        servedButton.addActionListener((ActionEvent evt) -> servedButtonActionPerformed(orderID));
        bottomPanel.add(servedButton);

        JButton updateStatusButton = new JButton();
        updateStatusButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        updateStatusButton.addActionListener((ActionEvent evt) -> updateStatusButtonActionPerformed(orderID, map.get(orderID)));
        updateStatusButton.setText("Update Items Status");
        bottomPanel.add(updateStatusButton);

        this.add(bottomPanel, BorderLayout.PAGE_END);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        preferredSize.height = 500;
        return preferredSize;
    }

    private void servedButtonActionPerformed(String orderID) {
        String result = kitchenController.markAsServed(orderID);
        if (result.contains("Delete failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateStatusButtonActionPerformed(String orderID, ArrayList<JCheckBox> jCheckBoxArrayList) {
        int succed = 0;
        int failed = 0;
        String result;
        for (int i = jCheckBoxArrayList.size() - 1; i >= 0; i--) {
            if (jCheckBoxArrayList.get(i).isSelected()) {
                result = kitchenController.updateStatus(orderID, jCheckBoxArrayList.get(i).getName());
                if (result.contains("Insertion failed: ")) {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                    failed++;
                } else {
                    succed++;
                }
                jCheckBoxArrayList.remove(i);
            }
        }
        if (succed == 0 && failed == 0) {
            JOptionPane.showMessageDialog(this, "Please select an item to update.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, succed + " items updated successfully.\n"
                    + failed + " items failed to update.", "Transaction Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
