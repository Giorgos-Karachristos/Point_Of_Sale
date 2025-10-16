package view.pos;

import com.formdev.flatlaf.FlatLightLaf;
import controller.pos.POSController;
import entities.Employee;
import view.cashier.CashierView;
import view.CustomFrame;
import view.HeaderView;
import view.kitchen.KitchenView;
import view.login.LoginView;
import view.manager.ManagerPane;
import view.waiter.WaiterView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class POSView {

    private static final Logger LOGGER = Logger.getLogger(POSView.class.getName());
    private final CustomFrame frame;
    private final POSController controller;

    public POSView(POSController controller) {
        this.controller = controller;
        frame = new CustomFrame();

        // Apply FlatLaf look and feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.log(Level.WARNING, "UnsupportedLookAndFeelException", e);
        }

        frame.setVisible(true);
    }

    public void showLoginView() {
        frame.getContentPane().removeAll();
        LoginView loginView = new LoginView(controller);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(loginView, gbc);
        refresh();
    }

    public void showMainView(Employee employee) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        HeaderView headerView = new HeaderView(controller, employee.getName(), employee.getSurname());
        frame.add(headerView, BorderLayout.NORTH);

        UIManager.put("TabbedPane.contentAreaColor", Color.BLACK);
        UIManager.put("TabbedPane.selectedBackground", new Color(223, 151, 0));
        UIManager.put("TabbedPane.focusColor", new Color(223, 151, 0));

        switch (employee.getRole()) {
            case "Manager":
                frame.add(new ManagerPane(), BorderLayout.CENTER);
                break;
            case "Cashier":
                frame.add(new CashierView(employee.getName(), employee.getSurname()), BorderLayout.CENTER);
                break;
            case "Kitchen":
                frame.add(new KitchenView(), BorderLayout.CENTER);
                break;
            case "Waiter":
                frame.add(new WaiterView(employee.getName(), employee.getSurname()), BorderLayout.CENTER);
                break;
        }

        refresh();
    }

    private void refresh() {
        frame.revalidate();
        frame.repaint();
    }
}