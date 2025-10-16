package model.kitchen;

import entities.KitchenItem;
import entities.Order;

import java.util.List;

public interface KitchenDAO {
    List<Order> getOrdersList();

    List<KitchenItem> getCookList(String orderID);

    String markAsServed(String orderID);

    String updateStatus(String orderID, String item);
}