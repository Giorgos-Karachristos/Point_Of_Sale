package view.manager;

import util.CustomImageIcon;

import javax.swing.*;
import java.awt.*;

public class SubTabbedPane extends JTabbedPane {

    public SubTabbedPane(String tab1, String tab2) {
        this.setBackground(new Color(250, 188, 63));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.putClientProperty("JTabbedPane.tabAreaAlignment", "center");

        addCustomTab(tab1);
        addCustomTab(tab2);
    }

    private void addCustomTab(String tabName) {
        ImageIcon imageIcon = CustomImageIcon.getScaledImageIcon("resources/" + tabName + ".png", 50, 50);

        CustomTabPanel customPanel = new CustomTabPanel(tabName, this);
        this.addTab(tabName, imageIcon, customPanel);
    }
}