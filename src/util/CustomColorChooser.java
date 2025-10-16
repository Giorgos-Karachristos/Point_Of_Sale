package util;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomColorChooser extends JDialog {

    public CustomColorChooser(JButton colorButton) {

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Color Chooser");
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc;

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.setColor(colorButton.getBackground());

        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel panel : panels) {
            if (!"HSV".equals(panel.getDisplayName())) {
                colorChooser.removeChooserPanel(panel);
            }
        }

        removeComponents(colorChooser, "javax.swing.JSpinner");
        removeComponents(colorChooser, "javax.swing.JRadioButton");
        removeComponents(colorChooser, "javax.swing.JLabel");
        removeComponents(colorChooser, "javax.swing.JSlider");

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(colorChooser, gbc);

        JPanel jPanel1 = new JPanel();
        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener((ActionEvent evt) -> {
            Color newColor = colorChooser.getColor();
            colorButton.setBackground(newColor);
            this.dispose();
        });
        jPanel1.add(okButton);

        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.addActionListener((ActionEvent evt) -> this.dispose());
        jPanel1.add(cancelButton);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(jPanel1, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    // Method to recursively remove components with the specified class name
    public static void removeComponents(Container container, String className) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component.getClass().getName().equals(className)) {
                container.remove(component);
            } else if (component instanceof Container) {
                removeComponents((Container) component, className);
            }
        }
        // Revalidate and repaint the container to reflect the changes
        container.revalidate();
        container.repaint();
    }
}

