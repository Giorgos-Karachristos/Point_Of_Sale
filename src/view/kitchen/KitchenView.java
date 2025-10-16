package view.kitchen;

import controller.kitchen.KitchenController;
import entities.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class KitchenView extends JPanel {

    private final KitchenController kitchenController;
    private JScrollPane jScrollPane;
    private List<Order> ordersData;

    public KitchenView() {
        kitchenController = new KitchenController();
        this.setLayout(new BorderLayout());

        JPanel kitchenTopPanel = new JPanel();
        kitchenTopPanel.setBackground(new Color(250, 188, 63));

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener((ActionEvent evt) -> refreshButtonActionPerformed());
        kitchenTopPanel.add(refreshButton);

        this.add(kitchenTopPanel, BorderLayout.PAGE_START);

        updateTabbedPane();
    }

    private void updateTabbedPane() {
        int spacing = 10;

        if (jScrollPane != null) {
            this.remove(jScrollPane);
        }

        JPanel kitchenCenterPanel = new JPanel();
        kitchenCenterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, spacing, spacing));

        KitchenPanel kp = null;
        ordersData = kitchenController.getOrdersList();
        for (Order order : ordersData) {
            String orderID = order.getOrderID();
            String orderStatus = order.getStatus();
            String orderDateTime = order.getOrderDate();

            kp = new KitchenPanel(orderID, orderStatus, orderDateTime);
            kitchenCenterPanel.add(kp);
        }
        if (kp != null) {
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

            int innerPanelWidth = kp.getPreferredSize().width;

            int panelsPerRow = screenWidth / (innerPanelWidth + spacing);

            int numInnerPanels = ordersData.size() / 3;

            int numRows = (int) Math.ceil((double) numInnerPanels / panelsPerRow);

            int outerPanelWidth = screenWidth - 100;

            int innerPanelHeight = kp.getPreferredSize().height;
            int outerPanelHeight = (innerPanelHeight + (int) Math.ceil(spacing * 1.5)) * numRows;

            kitchenCenterPanel.setPreferredSize(new Dimension(outerPanelWidth, outerPanelHeight));
        }

        jScrollPane = new JScrollPane(kitchenCenterPanel);

        this.add(jScrollPane, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void refreshButtonActionPerformed() {
        ordersData.removeAll(ordersData);
        updateTabbedPane();
        jScrollPane.revalidate();
        jScrollPane.repaint();
    }
}
