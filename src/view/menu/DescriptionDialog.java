package view.menu;

import javax.swing.*;
import java.awt.*;

public class DescriptionDialog extends JDialog {

    public DescriptionDialog(String itemName, String des, String price) {
        GridBagConstraints gbc;

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle(itemName + " Description");
        this.setLayout(new GridBagLayout());

        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jLabel.setText("Price: " + price + "$");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(jLabel, gbc);

        JTextArea descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setFont(new Font("Segoe UI", Font.BOLD, 20));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setText(des);

        JScrollPane jScrollPane1 = new JScrollPane(descriptionTextArea);
        jScrollPane1.setPreferredSize(new Dimension(400, 200));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.add(jScrollPane1, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
    }
}

