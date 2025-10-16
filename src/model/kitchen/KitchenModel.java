package model.kitchen;

import entities.KitchenItem;
import entities.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KitchenModel implements KitchenDAO {

    private static final Logger LOGGER = Logger.getLogger(KitchenModel.class.getName());
    private static final String ENDPOINT = "kitchen/";

    @Override
    public List<Order> getOrdersList() {
        List<Order> ordersList = new ArrayList<>();
        JSONArray orders = fetchKitchenData("getOrdersList");
        if (orders != null) {
            for (int i = 0; i < orders.length(); i++) {
                try {
                    JSONObject orderJSON = orders.getJSONObject(i);
                    ordersList.add(new Order(orderJSON.getString("ORDER_ID"), orderJSON.getString("STATUS"), orderJSON.getString("ORDER_DATE")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing order data", e);
                }
            }
        }
        return ordersList;
    }

    @Override
    public List<KitchenItem> getCookList(String orderID) {
        List<KitchenItem> cookList = new ArrayList<>();
        JSONArray items = fetchKitchenData("getCookList?orderID=" + orderID);
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    cookList.add(new KitchenItem(item.getString("NAME"), item.getInt("QUANTITY"), item.getInt("ITEM_ID"), item.getString("STATUS")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data", e);
                }
            }
        }
        return cookList;
    }

    private JSONArray fetchKitchenData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchKitchenData", e);
        }
        return null;
    }

    @Override
    public String markAsServed(String orderID) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("orderID", orderID);
        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "markAsServed", "POST", jsonInput);
    }

    @Override
    public String updateStatus(String orderID, String item) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("orderID", orderID);
        jsonInput.put("itemID", item);
        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "updateStatus", "POST", jsonInput);
    }
}
