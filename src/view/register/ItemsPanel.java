package view.register;

import controller.register.RegisterController;
import entities.ReceiptItem;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ItemsPanel extends JPanel {

    public ItemsPanel(String receiptID, RegisterView registerView, SummaryPanel summaryPanel) {
        RegisterController registerController = new RegisterController();
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(255, 255, 255));

        JLabel itemLabel = new JLabel();
        itemLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        itemLabel.setText("Item");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        this.add(itemLabel, gbc);

        JLabel qtyLabel = new JLabel();
        qtyLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        qtyLabel.setText("Qty");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        this.add(qtyLabel, gbc);

        JLabel taxLabel = new JLabel();
        taxLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        taxLabel.setText("Tax");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        this.add(taxLabel, gbc);

        JLabel totalPriceLabel = new JLabel();
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPriceLabel.setText("Total Price");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 10, 10);
        this.add(totalPriceLabel, gbc);

        int gridy = 1;
        if (!"Receipt".equals(receiptID) && !"".equals(receiptID)) {
            List<ReceiptItem> receiptItemList = registerController.getItemsList(receiptID);
            double totalPrice = 0;
            for (ReceiptItem receiptItem : receiptItemList) {
                JLabel itemValueLabel = new JLabel();
                itemValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                itemValueLabel.setText(receiptItem.getTotalPrice());
                gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = gridy;
                gbc.insets = new Insets(5, 10, 5, 5);
                this.add(itemValueLabel, gbc);

                JLabel qtyValueLabel = new JLabel();
                qtyValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                qtyValueLabel.setText(String.valueOf(receiptItem.getQuantity()));
                gbc = new GridBagConstraints();
                gbc.gridx = 1;
                gbc.gridy = gridy;
                gbc.insets = new Insets(5, 5, 5, 5);
                this.add(qtyValueLabel, gbc);

                JLabel taxValueLabel = new JLabel();
                taxValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                taxValueLabel.setText(receiptItem.getTax() + " %");
                gbc = new GridBagConstraints();
                gbc.gridx = 2;
                gbc.gridy = gridy;
                gbc.insets = new Insets(5, 5, 5, 5);
                this.add(taxValueLabel, gbc);

                JLabel totalPriceValueLabel = new JLabel();
                totalPriceValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                totalPriceValueLabel.setText(receiptItem.getTotalPrice());
                gbc = new GridBagConstraints();
                gbc.gridx = 3;
                gbc.gridy = gridy;
                gbc.insets = new Insets(5, 5, 5, 5);
                this.add(totalPriceValueLabel, gbc);

                totalPrice = totalPrice + Double.parseDouble(receiptItem.getTotalPrice());
                gridy++;
                this.repaint();
            }
            BigDecimal bd = new BigDecimal(totalPrice);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double roundedValue = bd.doubleValue();

            registerView.setSubtotalTextField(String.valueOf(roundedValue));
            summaryPanel.updateSubtotalValueLabel(String.valueOf(roundedValue));
        }
    }
}
