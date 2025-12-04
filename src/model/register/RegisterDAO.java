package model.register;

import entities.ReceiptItem;

import java.util.List;

public interface RegisterDAO {
    String[] getOrder(int order_ID);

    List<ReceiptItem> getItemsList(String orderID);

    String completeOrder(int orderID, double subtotal, double discount, double tip, double total);

    String cancelOrder(int orderID);
}
