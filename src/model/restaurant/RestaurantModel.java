package model.restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestaurantModel implements RestaurantDAO {

    private static final Logger LOGGER = Logger.getLogger(RestaurantModel.class.getName());
    private static final String ENDPOINT = "manager/restaurant";

    @Override
    public String[] getRestaurant() {
        String[] restaurant = {"", "", "", ""};
        JSONArray restaurantInfo = fetchRestaurantData();
        if (restaurantInfo != null) {
            for (int i = 0; i < restaurantInfo.length(); i++) {
                try {
                    JSONObject restaurantObject = restaurantInfo.getJSONObject(i);
                    restaurant[0] = restaurantObject.getString("NAME");
                    restaurant[1] = restaurantObject.getString("ADDRESS");
                    restaurant[2] = restaurantObject.getString("PHONE");
                    restaurant[3] = restaurantObject.getString("TABLES");
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing restaurantObject data for table", e);
                }
            }
        }
        return restaurant;
    }

    @Override
    public String updateRestaurant(String name, String address, String phone,int tables) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);
        jsonInput.put("ADDRESS", address);
        jsonInput.put("PHONE", phone);
        jsonInput.put("TABLES", tables);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String getRestaurantName() {
        String result = null;
        JSONArray restaurantInfo = fetchRestaurantData();
        if (restaurantInfo != null) {
            for (int i = 0; i < restaurantInfo.length(); i++) {
                try {
                    JSONObject restaurantObject = restaurantInfo.getJSONObject(i);
                    result = restaurantObject.getString("NAME");
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing restaurantObject data for list", e);
                }
            }
        }
        return result;
    }

    private JSONArray fetchRestaurantData() {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchRestaurantData", e);
        }
        return null;
    }
}

