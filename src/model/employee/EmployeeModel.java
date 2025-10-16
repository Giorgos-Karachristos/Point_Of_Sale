package model.employee;

import entities.Employee;
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

public class EmployeeModel implements EmployeeDAO {

    private static final Logger LOGGER = Logger.getLogger(EmployeeModel.class.getName());
    private static final String ENDPOINT = "manager/employee";

    @Override
    public void getEmployeeTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray employees = fetchEmployeeData();
        if (employees != null) {
            for (int i = 0; i < employees.length(); i++) {
                try {
                    JSONObject employee = employees.getJSONObject(i);
                    Object[] rowData = new Object[11];
                    rowData[0] = employee.getString("EMPLOYEE_ID");
                    rowData[1] = employee.getString("NAME");
                    rowData[2] = employee.getString("SURNAME");
                    rowData[3] = employee.getString("PHONE");
                    rowData[4] = employee.getString("EMAIL");
                    rowData[5] = employee.isNull("ROLE") ? "" : employee.getString("ROLE");
                    rowData[6] = employee.getString("SOCIAL_SECURITY_NUMBER");
                    rowData[7] = employee.getString("IDENTIFICATION_NUMBER");
                    rowData[8] = employee.getString("HIRE_DATE").substring(0, 10);
                    rowData[9] = employee.getString("USERNAME");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing employee data for table", e);
                }
            }
        }
    }

    //DeleteEmployee
    //UpdateEmployee
    @Override
    public List<Employee> getEmployeeList(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<Employee> employeeList = new ArrayList<>();
        boxModel.addElement("");
        JSONArray employees = fetchEmployeeData();
        if (employees != null) {
            for (int i = 0; i < employees.length(); i++) {
                try {
                    JSONObject employee = employees.getJSONObject(i);
                    boxModel.addElement(employee.getString("EMPLOYEE_ID"));
                    employeeList.add(new Employee(employee.getString("EMPLOYEE_ID"), employee.getString("NAME"), employee.getString("SURNAME"), employee.getString("PHONE"), employee.getString("EMAIL"), employee.isNull("ROLE") ? "" : employee.getString("ROLE"), employee.getString("SOCIAL_SECURITY_NUMBER"), employee.getString("IDENTIFICATION_NUMBER"), employee.getString("HIRE_DATE").substring(0, 10), employee.getString("USERNAME")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing employee data for list", e);
                }
            }
        }
        return employeeList;
    }

    private JSONArray fetchEmployeeData() {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchEmployeeData", e);
        }
        return null;
    }

    @Override
    public String insertEmployee(String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("NAME", name);
        jsonInput.put("SURNAME", surname);
        jsonInput.put("PHONE", phone);
        jsonInput.put("EMAIL", email);
        jsonInput.put("ROLE", role);
        jsonInput.put("SOCIAL_SECURITY_NUMBER", ssn);
        jsonInput.put("IDENTIFICATION_NUMBER", idn);
        jsonInput.put("USERNAME", username);
        jsonInput.put("PASSWORD", password);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String updateEmployee(int id, String name, String surname, String phone, String email, String role, String ssn, String idn, String username, String password) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("EMPLOYEE_ID", id);
        jsonInput.put("NAME", name);
        jsonInput.put("SURNAME", surname);
        jsonInput.put("PHONE", phone);
        jsonInput.put("EMAIL", email);
        jsonInput.put("ROLE", role);
        jsonInput.put("SOCIAL_SECURITY_NUMBER", ssn);
        jsonInput.put("IDENTIFICATION_NUMBER", idn);
        jsonInput.put("USERNAME", username);
        jsonInput.put("PASSWORD", password);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "PUT", jsonInput);
    }

    @Override
    public String deleteEmployee(int id) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("EMPLOYEE_ID", id);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}

