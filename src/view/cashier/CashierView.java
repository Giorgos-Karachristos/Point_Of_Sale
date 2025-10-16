package view.cashier;

import util.CustomImageIcon;
import view.menu.MenuView;
import view.order.OrdersView;
import view.receipts.ReceiptsView;
import view.register.RegisterView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class CashierView extends JTabbedPane {

    public CashierView(String name, String surname) {
        this.setTabPlacement(JTabbedPane.TOP);
        this.putClientProperty("JTabbedPane.tabAlignment", "leading");
        this.putClientProperty("JTabbedPane.tabAreaAlignment", "center");
        this.putClientProperty("JTabbedPane.showTabSeparators", true);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.setBackground(new Color(250, 188, 63));

        UIManager.put("TabbedPane.selectedBackground", null);
        UIManager.put("TabbedPane.focusColor", null);

        MenuView menuView = new MenuView(name, surname, this);
        ImageIcon imageIcon = CustomImageIcon.getScaledImageIcon("resources/Menu.png", 50, 50);
        this.addTab("Menu", imageIcon, menuView);

        RegisterView registerView = new RegisterView(this);
        OrdersView ordersView = new OrdersView(this, registerView);
        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Orders.png", 50, 50);
        this.addTab("Orders", imageIcon, ordersView);

        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Register.png", 50, 50);
        this.addTab("Register", imageIcon, registerView);

        ReceiptsView receiptsView = new ReceiptsView();
        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Receipts.png", 50, 50);
        this.addTab("Receipts", imageIcon, receiptsView);

        this.addChangeListener((ChangeEvent e) -> {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (this.getSelectedIndex() == 1) {
                ordersView.refreshButtonActionPerformed(false);
            }
            if (this.getSelectedIndex() != 2) {
                registerView.setOrderID(-1);
            }
            if (this.getSelectedIndex() == 3) {
                receiptsView.refreshButtonActionPerformed(false);
            }
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        });
    }
}

