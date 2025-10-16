package model.category;

import entities.Category;
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

public class CategoryModel implements CategoryDAO {

    private static final Logger LOGGER = Logger.getLogger(CategoryModel.class.getName());
    private static final String ENDPOINT = "manager/category";

    @Override
    public void getCategoryTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray categories = fetchCategoryData();
        if (categories != null) {
            for (int i = 0; i < categories.length(); i++) {
                try {
                    JSONObject category = categories.getJSONObject(i);
                    Object[] rowData = new Object[5];
                    rowData[0] = category.getString("NAME");
                    rowData[1] = category.getString("TAX");
                    rowData[2] = category.getString("COLOR");
                    rowData[3] = category.getString("IS_ACTIVE");
                    rowData[4] = category.getString("COUNT_ITEMS");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing category data for table", e);
                }
            }
        }
    }

    //DeleteCategory
    //UpdateCategory
    @Override
    public List<Category> getCategoryList(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<Category> categoryArrayList = new ArrayList<>();
        boxModel.addElement("");
        JSONArray categories = fetchCategoryData();
        if (categories != null) {
            for (int i = 0; i < categories.length(); i++) {
                try {
                    JSONObject category = categories.getJSONObject(i);
                    boxModel.addElement(category.getString("NAME"));
                    categoryArrayList.add(new Category(category.getString("NAME"), category.getString("TAX"), category.getString("COLOR"), category.getString("IS_ACTIVE"), category.getString("COUNT_ITEMS")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing category data for list", e);
                }
            }
        }
        return categoryArrayList;
    }

    //InsertItem
    //UpdateItemView
    @Override
    public void getCategoryName(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        boxModel.addElement("");
        JSONArray categories = fetchCategoryData();
        if (categories != null) {
            for (int i = 0; i < categories.length(); i++) {
                try {
                    JSONObject category = categories.getJSONObject(i);
                    boxModel.addElement(category.getString("NAME"));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing category data for ComboBox", e);
                }
            }
        }
    }

    private JSONArray fetchCategoryData() {
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
    public String insertCategory(String name, double tax, String color, String isActive) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);
        jsonInput.put("TAX", tax);
        jsonInput.put("COLOR", color);
        jsonInput.put("IS_ACTIVE", isActive);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String updateCategory(String name, double tax, String color, String isActive) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);
        jsonInput.put("TAX", tax);
        jsonInput.put("COLOR", color);
        jsonInput.put("IS_ACTIVE", isActive);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String deleteCategory(String name) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}
