package model.order;

import entities.Order;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface OrderDAO {
    List<Order> getActiveOrdersList();

    void getKitchenList(DefaultTableModel table, String orderID);

    String voidKitchen(int orderID, String itemName);

    String cancelOrder(int orderID);
}
