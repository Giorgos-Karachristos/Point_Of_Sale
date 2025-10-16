package view.register;

import controller.restaurant.RestaurantController;

import javax.swing.*;
import java.awt.*;

public class Receipt extends JPanel {

    private final JLabel receiptIDLabel;
    private final JLabel cashierValueLabel;
    private final JLabel dateValueLabel;
    private final JLabel timeValueLabel;

    private ItemsPanel itemsPanel;
    private SummaryPanel summaryPanel;

    public Receipt(RegisterView registerView) {

        GridBagConstraints gridBagConstraints;
        this.setLayout(new GridBagLayout());

        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(new GridBagLayout());

        RestaurantController restaurantController = new RestaurantController();
        String[] res = restaurantController.getRestaurant();

        JLabel restaurantNameLabel = new JLabel();
        restaurantNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        restaurantNameLabel.setText(res[0]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(restaurantNameLabel, gridBagConstraints);

        JLabel addressLabel = new JLabel();
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addressLabel.setText(res[1]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(addressLabel, gridBagConstraints);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneLabel.setText("Phone:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(phoneLabel, gridBagConstraints);

        JLabel phoneValueLabel = new JLabel();
        phoneValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneValueLabel.setText(res[2]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(phoneValueLabel, gridBagConstraints);

        JLabel receiptLabel = new JLabel();
        receiptLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        receiptLabel.setText("Receipt #:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(receiptLabel, gridBagConstraints);

        receiptIDLabel = new JLabel();
        receiptIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(receiptIDLabel, gridBagConstraints);

        JLabel cashierLabel = new JLabel();
        cashierLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cashierLabel.setText("Cashier:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(cashierLabel, gridBagConstraints);

        cashierValueLabel = new JLabel();
        cashierValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(cashierValueLabel, gridBagConstraints);

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dateLabel.setText("Date:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(dateLabel, gridBagConstraints);

        dateValueLabel = new JLabel();
        dateValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(dateValueLabel, gridBagConstraints);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setText("Time:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(timeLabel, gridBagConstraints);

        timeValueLabel = new JLabel();
        timeValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(timeValueLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(infoPanel, gridBagConstraints);

        addItemsSummaryPanels("Receipt", registerView);
    }

    void updateInfoValues(String receiptID, String cashierID, String date, String time, RegisterView registerView) {
        receiptIDLabel.setText(receiptID);
        cashierValueLabel.setText(cashierID);
        dateValueLabel.setText(date);
        timeValueLabel.setText(time);
        addItemsSummaryPanels(receiptID, registerView);
    }

    private void addItemsSummaryPanels(String receiptID, RegisterView registerView) {
        if (!"Receipt".equals(receiptID)) {
            this.remove(itemsPanel);
            this.remove(summaryPanel);
        }
        GridBagConstraints gridBagConstraints;

        summaryPanel = new SummaryPanel();
        itemsPanel = new ItemsPanel(receiptID, registerView, summaryPanel);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(itemsPanel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(summaryPanel, gridBagConstraints);

        this.repaint();
    }

    void updateSummaryValues(String subtotal, String tip, String discount, String total) {
        summaryPanel.updateSummaryValues(subtotal, tip, discount, total);
    }
}

