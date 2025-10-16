package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomImageIcon {

    private static final Logger LOGGER = Logger.getLogger(CustomImageIcon.class.getName());

    public static ImageIcon getScaledImageIcon(String imagePath, int width, int height) {
        URL location = CustomImageIcon.class.getClassLoader().getResource(imagePath);
        if (location != null) {
            ImageIcon icon = new ImageIcon(location);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            LOGGER.log(Level.WARNING, "Image not found: {0}", imagePath);
            BufferedImage blankImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            return new ImageIcon(blankImage);
        }
    }
}
