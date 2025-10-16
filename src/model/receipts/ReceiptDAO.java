package model.receipts;

import entities.KitchenItemCategory;
import entities.Order;

import java.util.List;

public interface ReceiptDAO {
     List<Order> getFinalizedOrdersList();

     List<KitchenItemCategory> getActiveItemsList(String orderID);

     String refundOrder(int orderID);
}
