package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportPanelToImage {
    public static void exportPanelToImage(JPanel panel, String fileName) throws IOException {
        // Get the current working directory
        String currentDir = System.getProperty("user.dir");
        // Set the directory path relative to the current working directory
        String directoryPath = currentDir + "/Receipts";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a BufferedImage with the size of the JPanel
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Paint the JPanel onto the BufferedImage
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();

        // Save the BufferedImage as an image file (e.g., PNG)
        File outputFile = new File(directoryPath + "/" + fileName);
        ImageIO.write(image, "png", outputFile);
    }
}
