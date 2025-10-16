package model.menu;

import entities.Category;
import entities.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuModel implements MenuDAO {
    private static final Logger LOGGER = Logger.getLogger(MenuModel.class.getName());
    private static final String ENDPOINT = "cashier/menu/";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryArrayList = new ArrayList<>();
        JSONArray categories = fetchMenuData("getCategoryList");
        if (categories != null) {
            for (int i = 0; i < categories.length(); i++) {
                try {
                    JSONObject category = categories.getJSONObject(i);
                    categoryArrayList.add(new Category(category.getString("NAME"), category.getString("COLOR"), category.getString("TAX")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing category data for list", e);
                }
            }
        }
        return categoryArrayList;
    }

    @Override
    public List<Item> getItemsList(String category) {
        List<Item> itemArrayList = new ArrayList<>();
        String encodedCategory = URLEncoder.encode(category, StandardCharsets.UTF_8);
        JSONArray items = fetchMenuData("getItemsList?category=" + encodedCategory);
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    itemArrayList.add(new Item(item.getString("NAME"), item.getString("COLOR"), item.getString("PRICE"), String.valueOf(item.getInt("ITEM_ID")), item.isNull("DESCRIPTION") ? "" : item.getString("DESCRIPTION")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for list", e);
                }
            }
        }
        return itemArrayList;
    }

    private JSONArray fetchMenuData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchMenuData", e);
        }
        return null;
    }

    @Override
    public String insertOrder(String username, String subtotal, String type) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", username);
        jsonInput.put("SUBTOTAL", subtotal);
        jsonInput.put("TYPE", type);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "insertOrder", "POST", jsonInput);
    }

    @Override
    public String insertKitchen(int id, double quantity, double total) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);
        jsonInput.put("QUANTITY", quantity);
        jsonInput.put("TOTAL_PRICE", total);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "insertKitchen", "POST", jsonInput);
    }
}
