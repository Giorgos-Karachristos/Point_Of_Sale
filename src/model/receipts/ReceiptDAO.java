package model.receipts;

import entities.ReceiptItem;
import entities.Order;

import java.util.List;

public interface ReceiptDAO {
     List<Order> getFinalizedOrdersList();

     List<ReceiptItem> getActiveItemsList(String orderID);

     String refundOrder(int orderID);
}
