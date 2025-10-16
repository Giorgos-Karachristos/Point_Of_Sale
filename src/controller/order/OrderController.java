package controller.order;

import entities.Order;
import model.order.OrderDAO;
import model.order.OrderModel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderController {
    private final OrderDAO model;

    public OrderController() {
        this.model = new OrderModel();
    }

    public List<Order> getActiveOrdersList() {
        return model.getActiveOrdersList();
    }

    public void getKitchenList(DefaultTableModel table, String orderID) {
        model.getKitchenList(table, orderID);
    }

    public String voidKitchen(int orderID, String itemName) {
        return model.voidKitchen(orderID, itemName);
    }

    public String cancelOrder(int orderID) {
        return model.cancelOrder(orderID);
    }
}
