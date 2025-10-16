package view.receipts;

import controller.receipts.ReceiptController;
import controller.restaurant.RestaurantController;
import entities.KitchenItemCategory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReceiptPanel extends JPanel {

    public ReceiptPanel(String orderID, String orderName, String orderSubtotal, String orderDiscount, String orderTip, String orderTotal, String date, String time) {
        ReceiptController receiptController = new ReceiptController();
        GridBagConstraints gbc;
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
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(restaurantNameLabel, gbc);

        JLabel addressLabel = new JLabel();
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addressLabel.setText(res[1]);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        infoPanel.add(addressLabel, gbc);

        JLabel phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneLabel.setText("Phone:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(phoneLabel, gbc);

        JLabel phoneValueLabel = new JLabel();
        phoneValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneValueLabel.setText(res[2]);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(phoneValueLabel, gbc);

        JLabel receiptLabel = new JLabel();
        receiptLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        receiptLabel.setText("Receipt #:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(receiptLabel, gbc);

        JLabel receiptIDLabel = new JLabel();
        receiptIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        receiptIDLabel.setText(orderID);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(receiptIDLabel, gbc);

        JLabel cashierLabel = new JLabel();
        cashierLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cashierLabel.setText("Cashier:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(cashierLabel, gbc);

        JLabel cashierValueLabel = new JLabel();
        cashierValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cashierValueLabel.setText(orderName);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 10, 10);
        infoPanel.add(cashierValueLabel, gbc);

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dateLabel.setText("Date:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(dateLabel, gbc);

        JLabel dateValueLabel = new JLabel();
        dateValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dateValueLabel.setText(date);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(dateValueLabel, gbc);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setText("Time:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(timeLabel, gbc);

        JLabel timeValueLabel = new JLabel();
        timeValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        timeValueLabel.setText(time);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 15, 10);
        infoPanel.add(timeValueLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(infoPanel, gbc);
        //--------------------------------------------------------------------------------

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridBagLayout());
        itemsPanel.setBackground(new Color(255, 255, 255));

        JLabel itemLabel = new JLabel();
        itemLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        itemLabel.setText("Item");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        itemsPanel.add(itemLabel, gbc);

        JLabel qtyLabel = new JLabel();
        qtyLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        qtyLabel.setText("Qty");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        itemsPanel.add(qtyLabel, gbc);

        JLabel taxLabel = new JLabel();
        taxLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        taxLabel.setText("Tax");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        itemsPanel.add(taxLabel, gbc);

        JLabel totalPriceLabel = new JLabel();
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPriceLabel.setText("Total Price");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        itemsPanel.add(totalPriceLabel, gbc);

        int gridy = 1;
        List<KitchenItemCategory> kitchenItemCategoryList = receiptController.getActiveItemsList(orderID);
        for (KitchenItemCategory kitchenItemCategory : kitchenItemCategoryList) {

            JLabel itemValueLabel = new JLabel();
            itemValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            itemValueLabel.setText(kitchenItemCategory.getTotalPrice());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.insets = new Insets(5, 10, 5, 5);
            itemsPanel.add(itemValueLabel, gbc);

            JLabel qtyValueLabel = new JLabel();
            qtyValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            qtyValueLabel.setText(String.valueOf(kitchenItemCategory.getQuantity()));
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = gridy;
            gbc.insets = new Insets(5, 5, 5, 5);
            itemsPanel.add(qtyValueLabel, gbc);

            JLabel taxValueLabel = new JLabel();
            taxValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            taxValueLabel.setText(kitchenItemCategory.getTax() + " %");
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = gridy;
            gbc.insets = new Insets(5, 5, 5, 5);
            itemsPanel.add(taxValueLabel, gbc);

            JLabel totalPriceValueLabel = new JLabel();
            totalPriceValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            totalPriceValueLabel.setText(kitchenItemCategory.getTotalPrice());
            gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = gridy;
            gbc.insets = new Insets(5, 5, 5, 5);
            itemsPanel.add(totalPriceValueLabel, gbc);

            gridy++;
            itemsPanel.repaint();
        }
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(itemsPanel, gbc);

        //-----------------------------------------------------------------------------
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridBagLayout());
        summaryPanel.setBackground(new Color(255, 255, 255));

        JLabel subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtotalLabel.setText("Subtotal");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(subtotalLabel, gbc);

        JLabel subtotalValueLabel = new JLabel();
        subtotalValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtotalValueLabel.setText(orderSubtotal + " $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(subtotalValueLabel, gbc);

        JLabel tipLabel = new JLabel();
        tipLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tipLabel.setText("Tip");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(tipLabel, gbc);

        JLabel tipValueLabel = new JLabel();
        tipValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tipValueLabel.setText(orderTip + " $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(tipValueLabel, gbc);

        JLabel discountLabel = new JLabel();
        discountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        discountLabel.setText("Discount");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(discountLabel, gbc);

        JLabel discountValueLabel = new JLabel();
        discountValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        discountValueLabel.setText(orderDiscount + " $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(discountValueLabel, gbc);

        JLabel totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setText("Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(totalLabel, gbc);

        JLabel totalValueLabel = new JLabel();
        totalValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalValueLabel.setText(orderTotal + " $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(totalValueLabel, gbc);

        JLabel thanksLabel = new JLabel();
        thanksLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        thanksLabel.setText("Thank you for dining with us!");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 10, 15, 10);
        summaryPanel.add(thanksLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(summaryPanel, gbc);
    }
}

