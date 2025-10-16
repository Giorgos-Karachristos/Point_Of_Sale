package model.inventory;

import entities.InventoryItem;
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

public class InventoryModel implements InventoryDAO {

    private static final Logger LOGGER = Logger.getLogger(InventoryModel.class.getName());
    private static final String ENDPOINT = "manager/inventory";

    @Override
    public void getInventoryTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray inventories = fetchInventoryData("");
        if (inventories != null) {
            for (int i = 0; i < inventories.length(); i++) {
                try {
                    JSONObject inventory = inventories.getJSONObject(i);
                    Object[] rowData = new Object[8];
                    rowData[0] = inventory.getString("ITEM_ID");
                    rowData[1] = inventory.getString("ITEM_CODE");
                    rowData[2] = inventory.getString("NAME");
                    rowData[3] = inventory.getString("LOCATION");
                    rowData[4] = inventory.getString("QUANTITY");
                    rowData[5] = inventory.getString("MIN_QUANTITY");
                    rowData[6] = inventory.getString("MAX_QUANTITY");
                    rowData[7] = inventory.getString("LAST_UPDATED");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing inventory data for table", e);
                }
            }
        }
    }

    //UpdateInventory
    //DeleteInventory
    //InsertTransaction
    @Override
    public List<InventoryItem> getInventoryList(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<InventoryItem> rowData = new ArrayList<>();
        boxModel.addElement("");
        JSONArray inventories = fetchInventoryData("");
        if (inventories != null) {
            for (int i = 0; i < inventories.length(); i++) {
                try {
                    JSONObject inventory = inventories.getJSONObject(i);
                    boxModel.addElement(inventory.getString("ITEM_ID"));
                    rowData.add(new InventoryItem(inventory.getString("ITEM_ID"), inventory.getString("ITEM_CODE"), inventory.getString("NAME"), inventory.getString("LOCATION"), inventory.getString("QUANTITY"), inventory.getString("MIN_QUANTITY"), inventory.getString("MAX_QUANTITY"), inventory.getString("LAST_UPDATED").substring(0, 10)));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing inventory data for list", e);
                }
            }
        }
        return rowData;
    }

    //InsertInventory
    @Override
    public List<Item> getInventoryComboBox(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<Item> rowData = new ArrayList<>();
        boxModel.addElement("");
        JSONArray inventories = fetchInventoryData("/getInventoryComboBox");
        if (inventories != null) {
            for (int i = 0; i < inventories.length(); i++) {
                try {
                    JSONObject inventory = inventories.getJSONObject(i);
                    boxModel.addElement(inventory.getString("ITEM_ID"));
                    rowData.add(new Item(inventory.getString("ITEM_ID"), inventory.getString("ITEM_CODE"), inventory.getString("NAME")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing inventory data for ComboBox", e);
                }
            }
        }
        return rowData;
    }

    private JSONArray fetchInventoryData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchInventoryData", e);
        }
        return null;
    }

    @Override
    public String insertInventory(int id, String location, int quantity, int minQuantity, int maxQuantity) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);
        jsonInput.put("LOCATION", location);
        jsonInput.put("QUANTITY", quantity);
        jsonInput.put("MIN_QUANTITY", minQuantity);
        jsonInput.put("MAX_QUANTITY", maxQuantity);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String updateInventory(int id, String location, int quantity, int minQuantity, int maxQuantity) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);
        jsonInput.put("LOCATION", location);
        jsonInput.put("QUANTITY", quantity);
        jsonInput.put("MIN_QUANTITY", minQuantity);
        jsonInput.put("MAX_QUANTITY", maxQuantity);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String deleteInventory(int id) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("ITEM_ID", id);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}
