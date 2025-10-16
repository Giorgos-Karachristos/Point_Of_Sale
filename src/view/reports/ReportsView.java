package view.reports;

import util.CustomImageIcon;
import view.CustomFrame;

import javax.swing.*;
import java.awt.*;

public class ReportsView extends JTabbedPane {

    public ReportsView() {
        this.setBackground(new Color(250, 188, 63));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.putClientProperty("JTabbedPane.tabAreaAlignment", "center");

        SalesReport sales = new SalesReport();
        ImageIcon imageIcon = CustomImageIcon.getScaledImageIcon("resources/SalesReport.png", 50, 50);
        this.addTab("Sales", imageIcon, sales);

        ItemsReport items = new ItemsReport();
        imageIcon = CustomImageIcon.getScaledImageIcon("resources/ItemsReport.png", 50, 50);
        this.addTab("Items", imageIcon, items);
    }
}