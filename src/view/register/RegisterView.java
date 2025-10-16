package view.register;

import controller.register.RegisterController;
import util.ExportPanelToImage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterView extends JPanel {

    private static final Logger LOGGER = Logger.getLogger(RegisterView.class.getName());
    private int orderID = -1;
    private final Receipt receipt;
    private final JTextField subtotalTextField;
    private final JTextField tipTextField;
    private final JTextField discountTextField;
    private final JTextField totalTextField;
    private final JTextField receivedTextField;
    private final JLabel changeValueLabel;
    private final RegisterController registerController;

    public RegisterView(JTabbedPane jtp) {
        registerController = new RegisterController();

        GridBagConstraints gbc;

        this.setLayout(new GridBagLayout());

        JLabel orderReceiptLabel = new JLabel();
        orderReceiptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        orderReceiptLabel.setText("Order Receipt");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 0, 0, 200);
        this.add(orderReceiptLabel, gbc);

        receipt = new Receipt(this);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(450, 250));
        jScrollPane.setViewportView(receipt);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 200);
        this.add(jScrollPane, gbc);

        JLabel subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtotalLabel.setText("Subtotal");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(subtotalLabel, gbc);

        subtotalTextField = new JTextField();
        subtotalTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtotalTextField.setHorizontalAlignment(JTextField.CENTER);
        subtotalTextField.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(subtotalTextField, gbc);

        JLabel tipLabel = new JLabel();
        tipLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tipLabel.setText("Tip");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(tipLabel, gbc);

        tipTextField = new JTextField();
        tipTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tipTextField.setHorizontalAlignment(JTextField.CENTER);
        tipTextField.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(tipTextField, gbc);

        JLabel discountLabel = new JLabel();
        discountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        discountLabel.setText("Discount");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(discountLabel, gbc);

        discountTextField = new JTextField();
        discountTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        discountTextField.setHorizontalAlignment(JTextField.CENTER);
        discountTextField.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(discountTextField, gbc);

        JLabel totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setText("Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(totalLabel, gbc);

        totalTextField = new JTextField();
        totalTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalTextField.setHorizontalAlignment(JTextField.CENTER);
        totalTextField.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(totalTextField, gbc);

        subtotalTextField.getDocument().addDocumentListener(totalCalculatorListener());
        tipTextField.getDocument().addDocumentListener(totalCalculatorListener());
        discountTextField.getDocument().addDocumentListener(totalCalculatorListener());

        JLabel receivedAmountLabel = new JLabel();
        receivedAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        receivedAmountLabel.setText("Received amount");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(receivedAmountLabel, gbc);

        receivedTextField = new JTextField();
        receivedTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        receivedTextField.setHorizontalAlignment(JTextField.CENTER);
        receivedTextField.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 150;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(receivedTextField, gbc);

        JLabel changeLabel = new JLabel();
        changeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        changeLabel.setText("Change");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(changeLabel, gbc);

        changeValueLabel = new JLabel();
        changeValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        changeValueLabel.setText("0.0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(changeValueLabel, gbc);

        receivedTextField.getDocument().addDocumentListener(changeCalculatorListener());

        JButton saveReceiptButton = new JButton();
        saveReceiptButton.setBackground(new Color(63, 169, 245));
        saveReceiptButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        saveReceiptButton.setText("Save Receipt");
        saveReceiptButton.addActionListener((ActionEvent evt) -> saveReceiptButtonActionPerformed());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(saveReceiptButton, gbc);

        JButton payOrderButton = new JButton();
        payOrderButton.setBackground(new Color(107, 203, 119));
        payOrderButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        payOrderButton.setText("Pay Order");
        payOrderButton.addActionListener((ActionEvent evt) -> payOrderButtonActionPerformed(orderID, jtp));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(payOrderButton, gbc);

        JButton cancelOrderButton = new JButton();
        cancelOrderButton.setBackground(new Color(238, 78, 78));
        cancelOrderButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cancelOrderButton.setText("Cancel Order");
        cancelOrderButton.addActionListener((ActionEvent evt) -> cancelOrderButtonActionPerformed(jtp));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(15, 15, 15, 100);
        this.add(cancelOrderButton, gbc);

    }

    public void setOrderID(int order_ID) {
        orderID = order_ID;
        if (order_ID == -1) {
            receipt.updateInfoValues("", "", "", "", this);
            setTextFieldText();
        } else {
            String[] details = registerController.getOrder(order_ID);
            receipt.updateInfoValues(String.valueOf(order_ID), details[0], details[1], details[2], this);
        }
    }

    private void setTextFieldText() {
        subtotalTextField.setText("0.0");
        tipTextField.setText("0.0");
        discountTextField.setText("0.0");
        totalTextField.setText("0.0");
        receivedTextField.setText("0.0");
        changeValueLabel.setText("0.0");
    }

    private DocumentListener totalCalculatorListener() {
        return new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override

            public void removeUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotal();
            }

            public void calculateTotal() {
                double subtotal = 0;
                double tip = 0;
                double discount = 0;
                String subtotalText = subtotalTextField.getText().isBlank() ? "0" : subtotalTextField.getText();
                String discountText = discountTextField.getText().isBlank() ? "0" : discountTextField.getText();
                String tipText = tipTextField.getText().isBlank() ? "0" : tipTextField.getText();
                if (isValid(subtotalText, "Subtotal") && isValid(discountText, "Discount") && isValid(tipText, "Tip")) {
                    subtotal = Double.parseDouble(subtotalText);
                    tip = Double.parseDouble(tipText);
                    discount = Double.parseDouble(discountText);
                }
                double total = subtotal + tip - discount;

                BigDecimal bd = new BigDecimal(total);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                double roundedValue = bd.doubleValue();

                totalTextField.setText(String.valueOf(roundedValue));
                receipt.updateSummaryValues(subtotalTextField.getText(), tipTextField.getText(), discountTextField.getText(), totalTextField.getText());
            }
        };
    }

    private DocumentListener changeCalculatorListener() {
        return new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override

            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }

            public void calculateChange() {
                double total = 0;
                double received = 0;
                String totalText = totalTextField.getText().isBlank() ? "0" : totalTextField.getText();
                String receivedText = receivedTextField.getText().isBlank() ? "0" : receivedTextField.getText();
                if (isValid(totalText, "Total") && isValid(receivedText, "Received")) {
                    total = Double.parseDouble(totalText);
                    received = Double.parseDouble(receivedText);
                }
                double change = received - total;

                BigDecimal bd = new BigDecimal(change);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                double roundedValue = bd.doubleValue();

                changeValueLabel.setText(String.valueOf(roundedValue));
            }
        };
    }

    private void saveReceiptButtonActionPerformed() {
        if (orderID == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            ExportPanelToImage.exportPanelToImage(receipt, "receipt" + orderID + ".png");
            JOptionPane.showMessageDialog(this, "Image file exported successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "ExportPanelToImage Exception", ex);
            JOptionPane.showMessageDialog(this, "Failed to export image file.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void payOrderButtonActionPerformed(int orderID, JTabbedPane jtp) {
        if (orderID == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String subtotal = subtotalTextField.getText();
        String discount = discountTextField.getText();
        String tip = tipTextField.getText();
        String total = totalTextField.getText();
        if (isValid(subtotal, "Subtotal") && isValid(discount, "Discount") && isValid(tip, "Tip") && isValid(total, "Total")) {
            String result = registerController.completeOrder(orderID, Double.parseDouble(subtotal), Double.parseDouble(discount), Double.parseDouble(tip), Double.parseDouble(total));
            if (result.contains("Update failed: ")) {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            jtp.setSelectedIndex(0);
        }
    }

    private boolean isValid(String price, String type) {
        if (price.isBlank()) {
            JOptionPane.showMessageDialog(this, type + " must be not empty", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        double doublePrice;
        try {
            doublePrice = Double.parseDouble(price);
            if (doublePrice < 0) {
                JOptionPane.showMessageDialog(this, type + " must be more than zero", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, type + " must be number", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setSubtotalTextField(String text) {
        subtotalTextField.setText(text);
    }

    private void cancelOrderButtonActionPerformed(JTabbedPane jtp) {
        if (orderID == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String result = registerController.cancelOrder(orderID);
        if (result.contains("Update failed: ")) {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        jtp.setSelectedIndex(0);
    }
}

