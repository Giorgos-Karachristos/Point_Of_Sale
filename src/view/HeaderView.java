package view;

import controller.pos.POSController;
import util.CustomImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderView extends JPanel {

    public HeaderView(POSController controller, String name, String surname) {
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int panel1Height = (int) (screenHeight * 0.055);
        this.setPreferredSize(new Dimension(getSize().width, panel1Height));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));

        JLabel usernameLabel = new JLabel(name + " " + surname);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        this.add(usernameLabel);

        JMenuItem signOutMenuItem = new JMenuItem("Sign Out");
        signOutMenuItem.addActionListener(e -> controller.onSignOut());

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(signOutMenuItem);

        ImageIcon imageIcon = CustomImageIcon.getScaledImageIcon("resources/User.png", 50, 50);
        JLabel pictureLabel = new JLabel(imageIcon);
        pictureLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(pictureLabel, e.getX(), e.getY());
            }
        });
        this.add(pictureLabel);
    }
}

