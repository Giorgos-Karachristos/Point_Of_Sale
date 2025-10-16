package view.login;

import controller.login.LoginController;
import controller.pos.POSController;
import controller.restaurant.RestaurantController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JPanel {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final LoginController loginController;

    public LoginView(POSController posController) {
        this.loginController = new LoginController(posController);
        this.setOpaque(false);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(450, 600));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc;

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        welcomeLabel.setText("Welcome to");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.anchor = java.awt.GridBagConstraints.PAGE_END;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(welcomeLabel, gbc);

        JLabel nameLabel = new JLabel();
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        RestaurantController restaurantController = new RestaurantController();
        nameLabel.setText(restaurantController.getRestaurantName());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.anchor = java.awt.GridBagConstraints.PAGE_START;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(nameLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(passwordField, gbc);

        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(new Color(57, 153, 24));
        signInButton.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            loginController.handleLogin(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            usernameField.setText("");
            passwordField.setText("");
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(signInButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(this, "Do you want to close this app?", "Exit", JOptionPane.YES_NO_OPTION);
            if (a == 0) System.exit(0);
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int cornerRadius = 50;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(roundedRectangle);
        g2d.dispose();
    }
}