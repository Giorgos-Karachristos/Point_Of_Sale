package model.register;

import entities.ReceiptItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterModel implements RegisterDAO {

    private static final Logger LOGGER = Logger.getLogger(RegisterModel.class.getName());
    private static final String ENDPOINT = "cashier/register/";

    @Override
    public String[] getOrder(int order_ID) {
        String[] details = {"", "", ""};
        JSONArray jSONArray = fetchRegisterData("getOrder?orderID=" + order_ID);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    details[0] = jSONObject.getString("NAME");
                    details[1] = jSONObject.getString("ORDER_DATE").substring(0, 11);
                    details[2] = jSONObject.getString("ORDER_DATE").substring(11);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing employee data for table", e);
                }
            }
        }
        return details;
    }

    @Override
    public List<ReceiptItem> getItemsList(String orderID) {
        List<ReceiptItem> receiptItemList = new ArrayList<>();
        JSONArray items = fetchRegisterData("getItemsList?orderID=" + orderID);
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    receiptItemList.add(new ReceiptItem(item.getString("NAME"), item.getInt("QUANTITY"), item.getString("TAX"), item.getString("TOTAL_PRICE")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for list", e);
                }
            }
        }
        return receiptItemList;
    }

    private JSONArray fetchRegisterData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchRegisterData", e);
        }
        return null;
    }

    @Override
    public String completeOrder(int orderID, double subtotal, double discount, double tip, double total) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ORDER_ID", orderID);
        jsonInput.put("SUBTOTAL", subtotal);
        jsonInput.put("DISCOUNT", discount);
        jsonInput.put("TIP", tip);
        jsonInput.put("TOTAL", total);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "completeOrder", "POST", jsonInput);
    }

    @Override
    public String cancelOrder(int orderID) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ORDER_ID", orderID);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "cancelOrder", "POST", jsonInput);
    }
}
