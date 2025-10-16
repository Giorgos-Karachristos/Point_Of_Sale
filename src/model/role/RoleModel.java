package model.role;

import entities.Role;
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

public class RoleModel implements RoleDAO {

    private static final Logger LOGGER = Logger.getLogger(RoleModel.class.getName());
    private static final String ENDPOINT = "manager/role";

    @Override
    public void getRoleTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray roles = fetchRoleData();
        if (roles != null) {
            for (int i = 0; i < roles.length(); i++) {
                try {
                    JSONObject role = roles.getJSONObject(i);
                    Object[] rowData = new Object[3];
                    rowData[0] = role.getString("NAME");
                    rowData[1] = role.getString("POS_ACCESS");
                    rowData[2] = role.getString("COUNT_EMPLOYEES");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing role data for table", e);
                }
            }
        }
    }

    //DeleteRole
    //UpdateRole
    @Override
    public List<Role> getRoleList(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<Role> roleArrayList = new ArrayList<>();
        boxModel.addElement("");
        JSONArray roles = fetchRoleData();
        if (roles != null) {
            for (int i = 0; i < roles.length(); i++) {
                try {
                    JSONObject role = roles.getJSONObject(i);
                    boxModel.addElement(role.getString("NAME"));
                    roleArrayList.add(new Role(role.getString("NAME"), role.getString("POS_ACCESS"), role.getString("COUNT_EMPLOYEES")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing role data for list", e);
                }
            }
        }
        return roleArrayList;
    }

    private JSONArray fetchRoleData() {
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

    //InsertEmployee
    //UpdateEmployee
    @Override
    public void getRoleName(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        boxModel.addElement("");
        JSONArray roles = fetchRoleData();
        if (roles != null) {
            for (int i = 0; i < roles.length(); i++) {
                try {
                    JSONObject role = roles.getJSONObject(i);
                    boxModel.addElement(role.getString("NAME"));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing role data for ComboBox", e);
                }
            }
        }
    }

    @Override
    public void getPOSAccess(DefaultComboBoxModel<String> boxModel) {
        boxModel.addElement("");
        String endpoint = "manager/pos_access";
        try {
            String response = HttpRequestUtil.sendHttpRequest(endpoint, "GET", null);
            if (response != null) {
                JSONArray roles = new JSONArray(response);
                for (int i = 0; i < roles.length(); i++) {
                    try {
                        JSONObject role = roles.getJSONObject(i);
                        boxModel.addElement(role.getString("NAME"));
                    } catch (JSONException e) {
                        LOGGER.log(Level.WARNING, "Error parsing role data for table", e);
                    }
                }
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in getPOSAccess", e);
        }
    }

    @Override
    public String insertRole(String roleName, String access) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", roleName);
        jsonInput.put("POS_ACCESS", access);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String updateRole(String roleName, String access) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", roleName);
        jsonInput.put("POS_ACCESS", access);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String deleteRole(String roleName) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", roleName);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}