package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CallPython {

    private static final Logger LOGGER = Logger.getLogger(CallPython.class.getName());

    public void callPythonLineGraph(String xData, String yData, String xTitle, String yTitle, String title, JLabel lineGraphLabel) {
        callPythonGraph("C:\\Users\\USER\\IdeaProjects\\PointOfSale\\src\\python\\generate_line_graph.py", xData, yData, xTitle, yTitle, title, lineGraphLabel);
    }

    public void callPythonBarGraph(String xData, String yData, String xTitle, String yTitle, String title, JLabel barGraphLabel) {
        callPythonGraph("C:\\Users\\USER\\IdeaProjects\\PointOfSale\\src\\python\\generate_bar_graph.py", xData, yData, xTitle, yTitle, title, barGraphLabel);
    }

    public void callPythonGraph(String names, String xData, String yData, JLabel lineGraphLabel) {
        try {
            // Pass dynamic data as arguments to Python script
            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\USER\\AppData\\Local\\Programs\\Python\\Python312\\python.exe", "C:\\Users\\USER\\IdeaProjects\\PointOfSale\\src\\python\\generate_magic_quadrant.py", names, xData, yData);

            pb.redirectErrorStream(true);  // Redirect error stream to standard output
            Process process = pb.start();

            // Capture the output from the process (Python script)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print Python script output
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

            // Load and refresh the graph image
            refreshGraphImage(lineGraphLabel);

        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.WARNING, "InterruptedException", e);
        }
    }

    private void callPythonGraph(String script, String x, String y, String x_title, String y_title, String title, JLabel jLabel) {
        try {
            // Pass dynamic data as arguments to Python script
            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\USER\\AppData\\Local\\Programs\\Python\\Python312\\python.exe", script, x, y, x_title, y_title, title, jLabel.getName());

            pb.redirectErrorStream(true);  // Redirect error stream to standard output
            Process process = pb.start();

            // Capture the output from the process (Python script)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print Python script output
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

            // Load and refresh the graph image
            refreshGraphImage(jLabel);

        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.WARNING, "InterruptedException", e);
        }
    }

    private void refreshGraphImage(JLabel graphLabel) throws IOException {
        String name = graphLabel.getName();
        BufferedImage bufferedImage = ImageIO.read(new File("Graphs/" + name + ".png"));
        ImageIcon graphIcon = new ImageIcon(bufferedImage);  // Create a new ImageIcon

        graphLabel.setIcon(graphIcon);
        graphLabel.revalidate();  // Revalidate the label to update the UI
    }
}
