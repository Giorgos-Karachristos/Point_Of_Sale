package view.waiter;

import com.google.zxing.WriterException;
import controller.waiter.WaiterController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WaiterView extends JPanel {

    private final WaiterController controller;
    private final JLabel qrCodeLabel;
    private final String name;
    private final String surname;

    public WaiterView(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.controller = new WaiterController();

        GridBagConstraints gbc;
        setLayout(new GridBagLayout());
        setBackground(new Color(250, 188, 63));

        JLabel textLabel = new JLabel();
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        textLabel.setText("Scan the QR code with your mobile app:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(textLabel, gbc);

        qrCodeLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(qrCodeLabel, gbc);

        JButton refreshButton = new JButton();
        refreshButton.setBackground(new Color(63, 169, 245));
        refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        refreshButton.setText("Refresh");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        this.add(refreshButton, gbc);

        refreshButton.addActionListener(evt -> onRefreshClicked());

        createQRCode();
    }

    private void onRefreshClicked() {
        createQRCode();
    }

    private void createQRCode() {
        try {
            String qrText = controller.fetchWaiterToken(name, surname);
            qrCodeLabel.setIcon(new ImageIcon(controller.generateQRCodeImage(qrText, 500, 500)));
            qrCodeLabel.revalidate();
        } catch (WriterException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error generating QR code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}