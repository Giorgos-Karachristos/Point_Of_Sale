import controller.pos.POSController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PointOfSale {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(POSController::new);
    }
}
