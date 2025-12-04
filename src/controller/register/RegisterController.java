package controller.register;

import entities.ReceiptItem;
import model.register.RegisterDAO;
import model.register.RegisterModel;

import java.util.List;

public class RegisterController {
    private final RegisterDAO registerDAO;

    public RegisterController() {
        this.registerDAO = new RegisterModel();
    }

    public String[] getOrder(int order_ID) {
        return registerDAO.getOrder(order_ID);
    }

    public List<ReceiptItem> getItemsList(String orderID) {
        return registerDAO.getItemsList(orderID);
    }

    public String completeOrder(int orderID, double subtotal, double discount, double tip, double total) {
        return registerDAO.completeOrder(orderID, subtotal, discount, tip, total);
    }

    public String cancelOrder(int orderID) {
        return registerDAO.cancelOrder(orderID);
    }
}
