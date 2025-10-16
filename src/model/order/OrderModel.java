package model.order;

import entities.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderModel implements OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderModel.class.getName());
    private static final String ENDPOINT = "cashier/order/";

    @Override
    public List<Order> getActiveOrdersList() {
        List<Order> orderArrayList = new ArrayList<>();
        JSONArray orders = fetchOrderData("getActiveOrdersList");
        if (orders != null) {
            for (int i = 0; i < orders.length(); i++) {
                try {
                    JSONObject order = orders.getJSONObject(i);
                    orderArrayList.add(new Order(order.getString("ORDER_ID"), order.getString("SUBTOTAL"), order.getString("STATUS"), order.isNull("TYPE") ? "" : order.getString("TYPE"), order.getString("ORDER_DATE")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing order data for list", e);
                }
            }
        }
        return orderArrayList;
    }

    @Override
    public void getKitchenList(DefaultTableModel table, String orderID) {
        Object[] rowData = new Object[3];
        JSONArray items = fetchOrderData("getKitchenList?orderID=" + orderID);
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    rowData[0] = item.getString("NAME");
                    rowData[1] = item.getInt("QUANTITY");
                    rowData[2] = item.getString("STATUS");
                    table.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for table", e);
                }
            }
        }
    }

    private JSONArray fetchOrderData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchOrderData", e);
        }
        return null;
    }

    @Override
    public String voidKitchen(int orderID, String itemName) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ORDER_ID", orderID);
        jsonInput.put("NAME", itemName);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "voidKitchen", "POST", jsonInput);
    }

    @Override
    public String cancelOrder(int orderID) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ORDER_ID", orderID);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "cancelOrder", "POST", jsonInput);
    }
}
