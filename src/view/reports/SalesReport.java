package view.reports;

import controller.reports.ReportsController;
import util.CallPython;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SalesReport extends JPanel {

    private final ReportsController reportsController;
    private final JLabel grossSalesGraphLabel;
    private final JLabel refundsGraphLabel;
    private final JLabel discountsGraphLabel;
    private final JLabel netSalesGraphLabel;
    private final JLabel grossProfitGraphLabel;

    public SalesReport() {
        reportsController = new ReportsController();
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(250, 188, 63));

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener((ActionEvent evt) -> refreshButtonActionPerformed());
        topPanel.add(refreshButton);

        this.add(topPanel, BorderLayout.PAGE_START);

        String[] string = reportsController.getSalesLabels();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(250, 188, 63));
        jPanel.setLayout(new GridBagLayout());

        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(250, 188, 63));
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel grossSalesLabel = new JLabel();
        grossSalesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        grossSalesLabel.setText("Gross Sales:");
        jPanel1.add(grossSalesLabel);

        JLabel sqlGrossSalesLabel = new JLabel();
        sqlGrossSalesLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sqlGrossSalesLabel.setText(string[0]);
        jPanel1.add(sqlGrossSalesLabel);

        JLabel refundsLabel = new JLabel();
        refundsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refundsLabel.setText("Refunds:");
        jPanel1.add(refundsLabel);

        String[] str = reportsController.getRefundsLabel();

        JLabel sqlRefundsLabel = new JLabel();
        sqlRefundsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sqlRefundsLabel.setText(str[0]);
        jPanel1.add(sqlRefundsLabel);

        JLabel discountsLabel = new JLabel();
        discountsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        discountsLabel.setText("Discounts:");
        jPanel1.add(discountsLabel);

        JLabel sqlDiscountsLabel = new JLabel();
        sqlDiscountsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sqlDiscountsLabel.setText(string[1]);
        jPanel1.add(sqlDiscountsLabel);

        JLabel netSalesLabel = new JLabel();
        netSalesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        netSalesLabel.setText("Net Sales:");
        jPanel1.add(netSalesLabel);

        JLabel sqlNetSalesLabel = new JLabel();
        sqlNetSalesLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sqlNetSalesLabel.setText(string[2]);
        jPanel1.add(sqlNetSalesLabel);

        JLabel grossProfitLabel = new JLabel();
        grossProfitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        grossProfitLabel.setText("Gross Profit:");
        jPanel1.add(grossProfitLabel);

        JLabel sqlGrossProfitLabel = new JLabel();
        sqlGrossProfitLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        sqlGrossProfitLabel.setText(string[3]);
        jPanel1.add(sqlGrossProfitLabel);

        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jPanel.add(jPanel1, gbc);

        JLabel grossSalesLabel1 = new JLabel();
        grossSalesLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        grossSalesLabel1.setText("Gross Sales:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(grossSalesLabel1, gbc);

        grossSalesGraphLabel = new JLabel();
        grossSalesGraphLabel.setName("grossSalesGraph");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        jPanel.add(grossSalesGraphLabel, gbc);

        JLabel refundsLabel1 = new JLabel();
        refundsLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refundsLabel1.setText("Refunds:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(refundsLabel1, gbc);

        refundsGraphLabel = new JLabel();
        refundsGraphLabel.setName("refundsGraph");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        jPanel.add(refundsGraphLabel, gbc);

        JLabel discountsLabel1 = new JLabel();
        discountsLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        discountsLabel1.setText("Discounts:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(discountsLabel1, gbc);

        discountsGraphLabel = new JLabel();
        discountsGraphLabel.setName("discountsGraph");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        jPanel.add(discountsGraphLabel, gbc);

        JLabel netSalesLabel1 = new JLabel();
        netSalesLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        netSalesLabel1.setText("Net Sales:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(netSalesLabel1, gbc);

        netSalesGraphLabel = new JLabel();
        netSalesGraphLabel.setName("netSalesGraph");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        jPanel.add(netSalesGraphLabel, gbc);

        JLabel grossProfitLabel1 = new JLabel();
        grossProfitLabel1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        grossProfitLabel1.setText("Gross Profit:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(grossProfitLabel1, gbc);

        grossProfitGraphLabel = new JLabel();
        grossProfitGraphLabel.setName("grossProfitGraph");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        jPanel.add(grossProfitGraphLabel, gbc);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane.setViewportView(jPanel);

        this.add(jScrollPane, BorderLayout.CENTER);

        loadGraphs();
    }

    private void refreshButtonActionPerformed() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        loadGraphs();
        this.revalidate();
        this.repaint();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void loadGraphs() {
        CallPython cp = new CallPython();

        String[] data = reportsController.getGrossSalesGraph();
        cp.callPythonLineGraph(data[0], data[1], "Date", "Subtotal", "Gross Sales", grossSalesGraphLabel);

        data = reportsController.getRefundsGraph();
        cp.callPythonLineGraph(data[0], data[1], "Date", "Total", "Refunds", refundsGraphLabel);

        data = reportsController.getDiscountsGraph();
        cp.callPythonLineGraph(data[0], data[1], "Date", "Discount", "Discounts", discountsGraphLabel);

        data = reportsController.getNetSalesGraph();
        cp.callPythonLineGraph(data[0], data[1], "Date", "Net Sales", "Net Sales", netSalesGraphLabel);

        data = reportsController.getGrossProfitGraph();
        cp.callPythonLineGraph(data[0], data[1], "Date", "Gross Profit", "Gross Profit", grossProfitGraphLabel);
    }
}
