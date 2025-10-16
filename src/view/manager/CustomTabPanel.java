package view.manager;

import controller.manager.CustomTabController;
import util.CustomTableCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomTabPanel extends JPanel {
    private final DefaultTableModel tableModel;
    private final CustomTabController controller;

    public CustomTabPanel(String tabName,JTabbedPane jtp) {
        this.controller = new CustomTabController(tabName);
        this.setLayout(new BorderLayout());

        // Top buttons panel
        JPanel northPanel = new JPanel(); //Panel with 3 buttons in the center
        northPanel.setPreferredSize(new Dimension(75, 75));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 25));

        JButton addButton = new JButton();
        addButton.setBackground(new Color(107, 203, 119));
        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        addButton.setText("Add " + tabName);
        northPanel.add(addButton);

        JButton updateButton = new JButton();
        if (!tabName.equals("Transactions")) {
            updateButton.setBackground(new Color(255, 217, 61));
            updateButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            updateButton.setText("Edit " + tabName);
            northPanel.add(updateButton);
        }

        JButton deleteButton = new JButton();
        deleteButton.setBackground(new Color(238, 78, 78));
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        deleteButton.setText("Delete " + tabName);
        northPanel.add(deleteButton);

        this.add(northPanel, BorderLayout.NORTH);

        //Empty panel
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(50, 50));
        this.add(westPanel, BorderLayout.WEST);

        // Table
        tableModel = new DefaultTableModel(new Object[][]{}, controller.getColumnNames()) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable jTable = new JTable(tableModel);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jTable.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane, BorderLayout.CENTER);

        //Empty panel
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(50, 50));
        this.add(eastPanel, BorderLayout.EAST);

        // Load initial data
        controller.loadTableData(tableModel);

        // Event listeners
        addButton.addActionListener(e -> controller.handleAddAction(tableModel));
        updateButton.addActionListener(e -> controller.handleUpdateAction(tableModel));
        deleteButton.addActionListener(e -> controller.handleDeleteAction(tableModel));

        switch (tabName) {
            case "Item":
                jTable.getColumnModel().getColumn(8).setCellRenderer(new CustomTableCellRenderer());
                break;
            case "Category":
                jTable.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());
                break;
        }
        jtp.addChangeListener(e -> refreshTable());
    }

    public void refreshTable() {
        controller.loadTableData(tableModel);
    }
}