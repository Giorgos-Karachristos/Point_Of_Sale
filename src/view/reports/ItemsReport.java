package view.reports;

import controller.reports.ReportsController;
import util.CallPython;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ItemsReport extends JPanel {

    private final JLabel inventoryGraphLabel;
    private final JLabel magicQuadrantGraphLabel;

    public ItemsReport() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(250, 188, 63));

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refreshButton.setText("Refresh");
        topPanel.add(refreshButton);

        this.add(topPanel, BorderLayout.PAGE_START);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(250, 188, 63));
        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc;

        JLabel inventoryLabel = new JLabel();
        inventoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        inventoryLabel.setText("Inventory Graph:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(inventoryLabel, new GridBagConstraints());

        inventoryGraphLabel = new JLabel();
        inventoryGraphLabel.setName("inventoryGraphLabel");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        jPanel.add(inventoryGraphLabel, gbc);

        JLabel magicQuadrantLabel = new JLabel();
        magicQuadrantLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        magicQuadrantLabel.setText("Magic Quadrant:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPanel.add(magicQuadrantLabel, gbc);

        magicQuadrantGraphLabel = new JLabel();
        magicQuadrantGraphLabel.setName("magicQuadrant");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        jPanel.add(magicQuadrantGraphLabel, gbc);

        JTable jTable = new JTable();
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTable.setRowHeight(40);
        jTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"No.", "Item"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jTable);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        jPanel.add(jScrollPane1, gbc);

        JScrollPane jScrollPane2 = new JScrollPane();
        jScrollPane2.setViewportView(jPanel);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        this.add(jScrollPane2, BorderLayout.CENTER);

        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        refreshButton.addActionListener((ActionEvent evt) -> refreshButtonActionPerformed(tableModel));
        loadGraphs(tableModel);
    }

    private void refreshButtonActionPerformed(DefaultTableModel tableModel) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        loadGraphs(tableModel);
        this.revalidate();
        this.repaint();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void loadGraphs(DefaultTableModel tableModel) {
        ReportsController reportsController = new ReportsController();
        CallPython cp = new CallPython();

        String[] inventoryData = reportsController.getInventoryGraph();
        cp.callPythonBarGraph(inventoryData[0], inventoryData[1], "Item Name", "Quantity", "Inventory Graph", inventoryGraphLabel);

        String[] itemsData = reportsController.getItemsGraph(tableModel);
        cp.callPythonGraph(itemsData[0], itemsData[1], itemsData[2], magicQuadrantGraphLabel);
    }
}
