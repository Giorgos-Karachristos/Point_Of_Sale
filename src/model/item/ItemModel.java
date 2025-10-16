package model.item;

import entities.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemModel implements ItemDAO {

    private static final Logger LOGGER = Logger.getLogger(ItemModel.class.getName());
    private static final String ENDPOINT = "manager/item";

    @Override
    public void getItemTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray items = fetchItemData();
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    Object[] rowData = new Object[10];
                    rowData[0] = item.getString("ITEM_ID");
                    rowData[1] = item.getString("ITEM_CODE");
                    rowData[2] = item.getString("NAME");
                    rowData[3] = item.isNull("DESCRIPTION") ? "" : item.getString("DESCRIPTION");
                    rowData[4] = item.getString("PRICE");
                    rowData[5] = item.getString("COST");
                    rowData[6] = item.isNull("CATEGORY") ? "" : item.getString("CATEGORY");
                    rowData[7] = item.getString("CREATED_AT").substring(0, 10);
                    rowData[8] = item.getString("COLOR");
                    rowData[9] = item.getString("IS_ACTIVE");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for table", e);
                }
            }
        }
    }

    //DeleteItem
    //UpdateItemView
    @Override
    public List<Item> getItemList(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<Item> itemArrayList = new ArrayList<>();
        boxModel.addElement("");
        JSONArray items = fetchItemData();
        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    boxModel.addElement(item.getString("ITEM_ID"));
                    itemArrayList.add(new Item(item.getString("ITEM_ID"), item.getString("ITEM_CODE"), item.getString("NAME"), item.isNull("DESCRIPTION") ? "" : item.getString("DESCRIPTION"), item.getString("PRICE"), item.getString("COST"), item.isNull("CATEGORY") ? "" : item.getString("CATEGORY"), item.getString("CREATED_AT").substring(0, 10), item.getString("COLOR"), item.getString("IS_ACTIVE")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing item data for list", e);
                }
            }
        }
        return itemArrayList;
    }

    private JSONArray fetchItemData() {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchRoleData", e);
        }
        return null;
    }

    @Override
    public String insertItem(String code, String name, String description, double price, double cost, String category, String color, String isActive) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_CODE", code);
        jsonInput.put("NAME", name);
        jsonInput.put("DESCRIPTION", description);
        jsonInput.put("PRICE", price);
        jsonInput.put("COST", cost);
        jsonInput.put("CATEGORY", category);
        jsonInput.put("COLOR", color);
        jsonInput.put("IS_ACTIVE", isActive);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String updateItem(int id, String code, String name, String description, double price, double cost, String category, String color, String isActive) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);
        jsonInput.put("ITEM_CODE", code);
        jsonInput.put("NAME", name);
        jsonInput.put("DESCRIPTION", description);
        jsonInput.put("PRICE", price);
        jsonInput.put("COST", cost);
        jsonInput.put("CATEGORY", category);
        jsonInput.put("COLOR", color);
        jsonInput.put("IS_ACTIVE", isActive);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String deleteItem(int id) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}
