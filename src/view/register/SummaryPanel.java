package view.register;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

    private final JLabel subtotalValueLabel;
    private final JLabel tipValueLabel;
    private final JLabel discountValueLabel;
    private final JLabel totalValueLabel;

    public SummaryPanel() {
        GridBagConstraints gbc;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(255, 255, 255));

        JLabel subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subtotalLabel.setText("Subtotal");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(subtotalLabel, gbc);

        subtotalValueLabel = new JLabel();
        subtotalValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtotalValueLabel.setText("0.0 $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(subtotalValueLabel, gbc);

        JLabel tipLabel = new JLabel();
        tipLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tipLabel.setText("Tip");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(tipLabel, gbc);

        tipValueLabel = new JLabel();
        tipValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tipValueLabel.setText("0.0 $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(tipValueLabel, gbc);

        JLabel discountLabel = new JLabel();
        discountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        discountLabel.setText("Discount");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(discountLabel, gbc);

        discountValueLabel = new JLabel();
        discountValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        discountValueLabel.setText("0.0 $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(discountValueLabel, gbc);

        JLabel totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setText("Total");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(totalLabel, gbc);

        totalValueLabel = new JLabel();
        totalValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalValueLabel.setText("0.0 $");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(totalValueLabel, gbc);

        JLabel thanksLabel = new JLabel();
        thanksLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        thanksLabel.setText("Thank you for dining with us!");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 10, 15, 10);
        this.add(thanksLabel, gbc);
    }

    void updateSummaryValues(String subtotal, String tip, String discount, String total) {
        subtotalValueLabel.setText(subtotal + " $");
        tipValueLabel.setText(tip + " $");
        discountValueLabel.setText(discount + " $");
        totalValueLabel.setText(total + " $");
        this.repaint();
    }

    void updateSubtotalValueLabel(String subtotal) {
        subtotalValueLabel.setText(subtotal + " $");
        this.repaint();
    }
}

