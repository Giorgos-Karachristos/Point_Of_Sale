package model.register;

import entities.KitchenItemCategory;

import java.util.List;

public interface RegisterDAO {
    String[] getOrder(int order_ID);

    List<KitchenItemCategory> getItemsList(String orderID);

    String completeOrder(int orderID, double subtotal, double discount, double tip, double total);

    String cancelOrder(int orderID);
}
