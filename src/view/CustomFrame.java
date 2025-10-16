package view;

import javax.swing.*;
import java.awt.*;

public class CustomFrame extends JFrame {

    public CustomFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setResizable(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.getContentPane().setBackground(new Color(250, 188, 63));

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/AppIcon.png"));
        this.setIconImage(icon.getImage());
    }
}