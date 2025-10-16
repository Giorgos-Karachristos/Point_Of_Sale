package model.receipts;

import entities.KitchenItemCategory;
import entities.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptModel implements ReceiptDAO {

    private static final Logger LOGGER = Logger.getLogger(ReceiptModel.class.getName());
    private static final String ENDPOINT = "cashier/receipt/";

    @Override
    public List<Order> getFinalizedOrdersList() {
        List<Order> orderArrayList = new ArrayList<>();
        JSONArray orders = fetchReceiptData("getFinalizedOrdersList");
        if (orders != null) {
            for (int i = 0; i < orders.length(); i++) {
                try {
                    JSONObject order = orders.getJSONObject(i);
                    orderArrayList.add(new Order(order.getString("ORDER_ID"), order.getString("NAME"), order.getString("SUBTOTAL"), order.getString("DISCOUNT"), order.getString("TIP"), order.getString("TOTAL"), order.getString("ORDER_DATE"), order.getString("STATUS")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing order data for list", e);
                }
            }
        }
        return orderArrayList;
    }

    @Override
    public List<KitchenItemCategory> getActiveItemsList(String orderID) {
        List<KitchenItemCategory> kitchenItemCategoryList = new ArrayList<>();
        JSONArray items = fetchReceiptData("getActiveItemsList?orderID=" + orderID);
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    kitchenItemCategoryList.add(new KitchenItemCategory(item.getString("NAME"), item.getInt("QUANTITY"), item.getString("TAX"), item.getString("TOTAL_PRICE")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for list", e);
                }
            }
        }
        return kitchenItemCategoryList;
    }

    private JSONArray fetchReceiptData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchReceiptData", e);
        }
        return null;
    }

    @Override
    public String refundOrder(int orderID) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ORDER_ID", orderID);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "refundOrder", "POST", jsonInput);
    }
}

