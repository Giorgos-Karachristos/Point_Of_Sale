package model.waiter;

import org.json.JSONObject;
import util.HttpRequestUtil;

public class WaiterModel implements WaiterDAO {
    private static final String ENDPOINT = "waiter/";

    @Override
    public String getWaiterToken(String name, String surname) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);
        jsonInput.put("SURNAME", surname);
        return HttpRequestUtil.sendHttpRequest(ENDPOINT + "insertWaiterToken", "POST", jsonInput);
    }

}
