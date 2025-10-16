package view.manager;

import util.CustomImageIcon;
import view.reports.ReportsView;
import view.restaurant.RestaurantView;

import javax.swing.*;
import java.awt.*;

public class ManagerPane extends JTabbedPane {

    public ManagerPane() {
        this.setTabPlacement(JTabbedPane.LEFT);
        this.putClientProperty("JTabbedPane.tabAlignment", "leading");
        this.putClientProperty("JTabbedPane.showTabSeparators", true);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.setBackground(new Color(250, 188, 63));

        ImageIcon imageIcon = CustomImageIcon.getScaledImageIcon("resources/Employees.png", 50, 50);
        this.addTab("Employees", imageIcon, new SubTabbedPane("Employee", "Role"));

        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Items.png", 50, 50);
        this.addTab("Items", imageIcon, new SubTabbedPane("Item", "Category"));

        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Inventory.png", 50, 50);
        this.addTab("Inventory", imageIcon, new SubTabbedPane("Inventory", "Transactions"));

        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Restaurant.png", 50, 50);
        this.addTab("Restaurant", imageIcon, new RestaurantView());

        imageIcon = CustomImageIcon.getScaledImageIcon("resources/Reports.png", 50, 50);
        this.addTab("Reports", imageIcon, new ReportsView());
    }
}
