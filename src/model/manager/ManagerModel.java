package model.manager;

import org.json.JSONObject;
import util.HttpRequestUtil;

public class ManagerModel implements ManagerDAO{

    public String[] getAttributeNames(String table) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("table", table);

        String[] columnNames = HttpRequestUtil.sendHttpRequest("manager/atributes", "POST", jsonInput).split(" ");
        for (int i = 0; i < columnNames.length; i++) {
            String s = columnNames[i].replace("_", " ");
            String res = s.charAt(0) + s.substring(1).toLowerCase();
            columnNames[i] = res;
        }
        return columnNames;
    }
}

