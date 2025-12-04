package controller.receipts;

import entities.ReceiptItem;
import entities.Order;
import model.receipts.ReceiptDAO;
import model.receipts.ReceiptModel;

import java.util.List;

public class ReceiptController {
    private final ReceiptDAO receiptDAO;

    public ReceiptController() {
        this.receiptDAO = new ReceiptModel();
    }

    public List<Order> getFinalizedOrdersList() {
        return receiptDAO.getFinalizedOrdersList();
    }

    public List<ReceiptItem> getActiveItemsList(String orderID) {
        return receiptDAO.getActiveItemsList(orderID);
    }

    public String refundOrder(int orderID) {
        return receiptDAO.refundOrder(orderID);
    }
}
