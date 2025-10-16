package controller.kitchen;

import entities.KitchenItem;
import entities.Order;
import model.kitchen.KitchenDAO;
import model.kitchen.KitchenModel;

import java.util.List;

public class KitchenController {

    private final KitchenDAO model;

    public KitchenController() {
        this.model = new KitchenModel();
    }

    public List<Order> getOrdersList() {
        return model.getOrdersList();
    }

    public List<KitchenItem> getCookList(String orderID) {
        return model.getCookList(orderID);
    }

    public String markAsServed(String orderID) {
        return model.markAsServed(orderID);
    }

    public String updateStatus(String orderID, String item) {
        return model.updateStatus(orderID, item);
    }
}
